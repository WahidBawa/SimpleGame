package com.mygdx.game.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;


public class Player {
    private int x, y;
    Texture player;
    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        player = new Texture("Assets/0.png");
    }

    //updates character's position
    public void render(SpriteBatch batch) {
//        batch.draw()
    }

    public void update() {

    }

}

