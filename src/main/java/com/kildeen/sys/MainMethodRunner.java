package com.kildeen.sys;

import com.kildeen.Story;

public class MainMethodRunner {
    public static void main(final String[] args) {
        RunTextGame runTextGame = new RunTextGame();
        runTextGame.run(args, new UndertowServer(), new StateReader(new FileReader()), new Story());
    }
}

