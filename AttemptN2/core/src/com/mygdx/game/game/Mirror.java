package com.mygdx.game.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Mirror {
    private int x, y;
    private int hit;
    private Texture[] reflector = {new Texture("Assets/Mirror/0.png"), new Texture("Assets/Mirror/1.png"), new Texture("Assets/Mirror/2.png")};
    private Sprite[] reflector_sprite = {new Sprite(reflector[0]), new Sprite(reflector[1]), new Sprite(reflector[2])};

    public Mirror() {

    }

}
