package com.kildeen.sys;

import io.undertow.Undertow;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class RunTextGameTest {

    @Test
    void should_start_server() {
        RunTextGame rtg = new RunTextGame();
        UndertowServer srv = Mockito.mock(UndertowServer.class);
        rtg.run(null, srv);
        Mockito.verify(srv).startServer(Mockito.any(Undertow.Builder.class));
    }
}