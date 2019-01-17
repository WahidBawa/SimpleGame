package com.mygdx.game.desktop;

import com.mygdx.game.game.Main;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "ZayniZain";
        config.foregroundFPS = 60;
//		config.width = 640;
//		config.height = 480;
        new LwjglApplication(new Main(), config);
    }
}
