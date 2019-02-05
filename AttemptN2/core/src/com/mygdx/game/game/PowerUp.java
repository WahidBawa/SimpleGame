package com.mygdx.game.game;

import com.badlogic.gdx.graphics.Texture;

import java.util.*;
public class PowerUp {
    private int x, y, type, fallSpeed;
    private Random rand = new Random();
    private Texture spiritBomb_sprite;
    public PowerUp(int type){
        spiritBomb_sprite = new Texture("Assets/spiritBomb.png");
        x = rand.nextInt(Main.WIDTH - spiritBomb_sprite.getWidth() );
        y = 0;
    }

    public void render(){

    }

    public void update(){

    }
}
