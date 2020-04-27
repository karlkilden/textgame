package com.kildeen.sys;

import com.kildeen.Story;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.handlers.DisableCacheHandler;
import io.undertow.server.handlers.resource.ClassPathResourceManager;
import io.undertow.server.handlers.resource.PathResourceManager;
import io.undertow.server.handlers.resource.ResourceManager;
import io.undertow.websockets.WebSocketConnectionCallback;
import io.undertow.websockets.core.AbstractReceiveListener;
import io.undertow.websockets.core.BufferedTextMessage;
import io.undertow.websockets.core.WebSocketChannel;
import io.undertow.websockets.core.WebSockets;
import io.undertow.websockets.spi.WebSocketHttpExchange;

import java.nio.file.Paths;

import static io.undertow.Handlers.*;

public class UndertowServer {

    public Undertow startServer(Undertow.Builder undertowBuilder, Story story) {
        ClassPathResourceManager resourceManager = new ClassPathResourceManager(UndertowServer.class.getClassLoader(), UndertowServer.class.getPackage());
//        ResourceManager resourceManager2 = new PathResourceManager(Paths.get("src/main/resources/com/kildeen/sys/css"));
        ResourceManager cssManager = new ClassPathResourceManager(UndertowServer.class.getClassLoader(), "css");
        ResourceManager jsManager = new ClassPathResourceManager(UndertowServer.class.getClassLoader(), "js");
        DisableCacheHandler.Builder disableCacheBuilder = new DisableCacheHandler.Builder();
        HttpHandler cssHandler = disableCacheBuilder.build(null).wrap(path().addPrefixPath("/css", resource(cssManager)));
        HttpHandler jsHandler = disableCacheBuilder.build(null).wrap(path().addPrefixPath("/js", resource(jsManager)));

        Undertow server = undertowBuilder
                .addHttpListener(8080, "localhost")
                .setHandler(cssHandler)
                .setHandler(jsHandler)

                .setHandler(path()
                        .addPrefixPath("/myapp", websocket(new WebSocketConnectionCallback() {

                            @Override
                            public void onConnect(WebSocketHttpExchange exchange, WebSocketChannel channel) {
                                for (WebSocketChannel session : channel.getPeerConnections()) {
                                    WebSockets.sendText(story.getWebWrappingPlotEvents().get(0).toString(), session, null);
                                }
                                channel.getReceiveSetter().set(new AbstractReceiveListener() {

                                    @Override
                                    protected void onFullTextMessage(WebSocketChannel channel, BufferedTextMessage message) {
                                        final String messageData = message.getData();

                                        for (WebSocketChannel session : channel.getPeerConnections()) {
                                            WebSockets.sendText(story.getPlotEvent(messageData).toString(), session, null);
                                        }
                                    }
                                });
                                channel.resumeReceives();
                            }

                        }))
                        .addPrefixPath("/", resource(resourceManager)
                                .addWelcomeFiles("index.html")))

                .build();
        server.start();
        return server;
    }
}
