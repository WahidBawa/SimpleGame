package com.mygdx.game.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;


public class Player {
    //stores x loc (start at 240)
    private int x = 240;
    //stores y loc (start at 167)
    private int y = 267;
    //texture for our character
    public Texture player_img = new Texture("Assets/SPRITES/Megaman/Zero/DashL/0.png");

    //speed for our character in pixels/s

    private String name;

    //defining constants for direction
    private static final int LEFT = -1;
    private static final int RIGHT = 1;
    private static final int UP = 2;
    private static final int DOWN = -2;
    private final static int JUMP = 100;
    private static final int STANDING = 0;
    private int direction = LEFT;

    private int height;
    private int width;

    private int counter = 0;
    private int pos = 0;
    private int animation_speed = 2;

    private int speed = 10;

    private boolean jump = false;

    //initialization code
    public Player(String name) {
        this.name = name;
        Main.players.add(this);
        height = player_img.getHeight() * 2;
        width = player_img.getWidth() * 2;
    }

    //updates character's position
    public void render() {
        Main.batch.draw(player_img, x, y, width, height);

    }

    public boolean getJump() {
        return jump;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }

    public void update() {
        if (!jump) y -= speed;

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) this.goRight();
        else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) this.goLeft();
        else if (Gdx.input.isKeyPressed(Input.Keys.UP)) this.goUp();
        else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) this.goDown();
        else this.direction = STANDING;

        counter += 1;
        if (counter > animation_speed) {
            counter = 0;
            pos += 1;
            if (pos >= 3) {
                pos = 0;
            }
        }

    }

    //changes the character direction to right
    public void goRight() {
        direction = RIGHT;
        x += speed;
    }

    //changes character direction to left
    public void goLeft() {
        direction = LEFT;
        x -= speed;
    }

    public void goUp() {
        direction = UP;
        y += speed * 2;
    }

    public void goDown() {
        direction = RIGHT;
        y -= speed;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void jump(){
        direction = JUMP;
        y += 5;
//        for (int i = 0; i < 100; i++){
//            y += 1;
//            this.render();
//
//        }
//        for (int i = 0; i < 100; i++){
//            y -= 1;
//            this.render();
//        }
    }

    //changes character direction to standing
    public void setStanding() {
        direction = STANDING;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void collidesWith(ArrayList<Walls> walls){
        for (Walls i : walls){
            if (i.isCollideWith(this)){
                y += speed;
            }
        }
    }

}

