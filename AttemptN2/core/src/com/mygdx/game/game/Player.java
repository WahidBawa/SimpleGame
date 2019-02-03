package com.mygdx.game.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;


public class Player {
    private int x, y;
    private Texture player_sprite;
    private Sprite player;
    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        player_sprite = new Texture("Assets/0.png");
        player = new Sprite(player_sprite, player_sprite.getWidth() / 2, player_sprite.getHeight() / 2);
        player.setScale(player.getWidth() * 2, player.getHeight() * 2);
    }

    //updates character's position
    public void render(SpriteBatch batch) {
        player.draw(batch);
    }

    public void update() {

    }

}

