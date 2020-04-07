package com.kildeen.sys;

import com.kildeen.Story;
import io.undertow.Undertow;
import io.undertow.server.handlers.resource.ClassPathResourceManager;
import io.undertow.websockets.WebSocketConnectionCallback;
import io.undertow.websockets.core.AbstractReceiveListener;
import io.undertow.websockets.core.BufferedTextMessage;
import io.undertow.websockets.core.WebSocketChannel;
import io.undertow.websockets.core.WebSockets;
import io.undertow.websockets.spi.WebSocketHttpExchange;

import static io.undertow.Handlers.*;

public class UndertowServer {

    public Undertow startServer(Undertow.Builder undertowBuilder, Story story) {
        Undertow server = undertowBuilder
                .addHttpListener(8080, "localhost")
                .setHandler(path()
                        .addPrefixPath("/myapp", websocket(new WebSocketConnectionCallback() {

                            @Override
                            public void onConnect(WebSocketHttpExchange exchange, WebSocketChannel channel) {
                                for (WebSocketChannel session : channel.getPeerConnections()) {
                                    WebSockets.sendText(story.getPlotEvents().get(0).toString(), session, null);
                                }
                                channel.getReceiveSetter().set(new AbstractReceiveListener() {

                                    @Override
                                    protected void onFullTextMessage(WebSocketChannel channel, BufferedTextMessage message) {
                                        final String messageData = message.getData();
                                        for (WebSocketChannel session : channel.getPeerConnections()) {
                                            WebSockets.sendText(messageData, session, null);
                                        }
                                    }
                                });
                                channel.resumeReceives();
                            }

                        }))
                        .addPrefixPath("/", resource(new ClassPathResourceManager(UndertowServer.class.getClassLoader(), UndertowServer.class.getPackage()))
                                .addWelcomeFiles("index.html")))
                .build();

        server.start();
        return server;
    }
}
