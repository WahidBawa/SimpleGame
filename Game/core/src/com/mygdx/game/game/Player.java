package com.mygdx.game.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

public class Player {
    //stores x loc (start at 240)
    private int x = 240;
    //stores y loc (start at 167)
    private int y = 167;
    //texture for our character
    public Texture player_img = new Texture("Assets/SPRITES/Zain/0.png");
    //speed for our character in pixels/s

    //defining constants for direction
    static final int LEFT = -1;
    static final int RIGHT = 1;
    static final int STANDING = 0;
    int direction = 0;

    //initialization code
    public Player(){}

    //updates character's position
    public void update(){
        if (direction == RIGHT){

        }
    }

    //changes the character direction to right
    public void goRight(){
        direction = RIGHT;
    }

    //changes character direction to left
    public void goLeft(){
        direction = LEFT;
    }

    //changes character direction to standing
    public void setStanding(){
        direction = STANDING;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getDirection(){
        return direction;
    }
}

