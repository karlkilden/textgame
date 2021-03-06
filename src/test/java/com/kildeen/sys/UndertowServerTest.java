package com.kildeen.sys;

import io.undertow.Undertow;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


class UnderTowServerTest {

    /**
     * Undertow.java has a lot of final unmockable classes and unreachable state. So we will just do a basic sanity test.
     * Undertow.java has a handy Enum with the enum Value HTTP (but does not use it to set the actualy listenerType, so we have to compare ignoring case
     */
    @Test
    @Disabled
    void startServer() {
        Undertow.Builder builder = Undertow.builder();
        UndertowServer UnderTowServer = new UndertowServer();
       Undertow tow =  UnderTowServer.startServer(builder,null);

        Assertions.assertThat(tow.getListenerInfo().get(0).getProtcol()).isEqualToIgnoringCase(Undertow.ListenerType.HTTP.toString());
    }
}