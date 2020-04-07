package com.kildeen.sys;

import com.kildeen.Story;
import io.undertow.Undertow;


public class RunTextGame {

    public  void run(final String[] args, UndertowServer underTowServer, StateReader stateReader, Story story) {
        story.createStory(stateReader.readAsHjson("src/main/resources/com/kildeen/sys/game.hjson"));
        underTowServer.startServer(Undertow.builder(), story);

    }

}
