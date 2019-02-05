package com.mygdx.game.game;

import com.badlogic.gdx.graphics.Texture;

import java.util.*;
public class PowerUp {
    private int x, y, type, fallSpeed;
    private Random rand = new Random();
    private Texture spiritBomb_sprite;
    private Texture mirror_sprite;
    private boolean activate=false;
    private static final int mirror=6;
    private static final int spiritBomb=1;
    private int powerType;
    public PowerUp(int type){
        if(type==spiritBomb){
            powerType=1;
            spiritBomb_sprite = new Texture("Assets/spiritBomb.png");
        }
        else if(type==mirror){
            powerType=6;
            mirror_sprite=new Texture("Assets/spiritBomb.png"); //change later i could not get the sprite
        }

        x = rand.nextInt(Main.WIDTH - spiritBomb_sprite.getWidth() ); //what are you trying to do?
        y = 0;
    }
    private void powerupON(){//upon collecting the powerup this is called
        if(powerType==mirror){
            mirror_sprite=new Texture("Assets/Mirror/0.png");
        }
    }

    public void render(){
    }

    public void update(){

    }
}
