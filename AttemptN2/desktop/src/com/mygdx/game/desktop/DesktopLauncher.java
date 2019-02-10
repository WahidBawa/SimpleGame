package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.game.IntroEnd;
import com.mygdx.game.game.Main;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "NOTMegaMan";
        config.foregroundFPS = 60;
//		config.width = 640;
//		config.height = 480;

//        new LwjglApplication(new IntroEnd(), config);
        new LwjglApplication(new Main(), config);
    }
}
