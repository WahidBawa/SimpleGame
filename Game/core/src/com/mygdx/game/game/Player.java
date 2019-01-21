package com.mygdx.game.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;


public class Player {
    //stores x loc (start at 240)
    private int x = 340;
    //stores y loc (start at 167)
    private int y = 167;
    //texture for our character
    public Texture player_img = new Texture("Assets/SPRITES/Megaman/Zero/DashL/0.png");

    //speed for our character in pixels/s

    private String name;

    private int width, height;

    private int counter = 0;
    private int pos = 0;
    private int animation_speed = 2;

    private int speed = 10;

    private boolean jump = false;

    private boolean isTouchingGround = false;

    private boolean L = false;
    private boolean R = false;

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
    public void update() {
        if (!jump){
            this.goDown();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) this.goRight();
        else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) this.goLeft();

        counter += 1;
        if (counter > animation_speed) {
            counter = 0;
            pos += 1;
            if (pos >= 3) {
                pos = 0;
            }
        }

    }


    public void setJump(boolean jump) {
        this.jump = jump;
    }

    public void goRight() {
        x += speed;
        R = true;
        L = false;
    }

    public void goLeft() {
        x -= speed;
        L = true;
        R = false;
    }

    public void goDown(){
        y -= speed;
        collidesWithGround(Main.walls);
    }
    public void goUp(){
        y += speed / 2;
        collidesWithGround(Main.walls);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void jump(){
        y += 5;
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
                if (R){
                    x -= speed;
                }else if (L){
                    x += speed;
                }
            }
        }
    }

    public boolean isTouchingGround() {
        return isTouchingGround;
    }

    public void setTouchingGround(boolean touchingGround) {
        isTouchingGround = touchingGround;
    }

    public void collidesWithGround(ArrayList<Walls> walls){
        for (Walls i : walls){
            if (i.isCollideWith(this)){
                goUp();
                this.setTouchingGround(true);
            }
        }
    }

}

