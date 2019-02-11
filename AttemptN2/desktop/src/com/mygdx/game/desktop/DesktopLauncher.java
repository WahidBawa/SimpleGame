package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.game.IntroEnd;
import com.mygdx.game.game.Main;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Super Space Adventure";
        config.foregroundFPS = 60;
//        new LwjglApplication(new IntroEnd(), config);
        new LwjglApplication(new Main(), config);
    }
}
