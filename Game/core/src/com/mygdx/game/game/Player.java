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
    public Texture player_img = new Texture("Assets/SPRITES/Megaman/Zero/Walk/0.png");

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
        height = player_img.getHeight();
        width = player_img.getWidth();
    }


    //updates character's position
    public void render() {
//        double ONE_OVER_FPS =(1.0f/60.0f);
//        double g_SpikeGuardBreakpoint = 3.0f * ONE_OVER_FPS;
//        double time=0;
//        time=time * 0.9 +* 0.1;
        if(L){
            for(int i=0;i<17;i++){
                player_img = new Texture("Assets/SPRITES/Megaman/Zero/Walk/"+i+".png");
//                if(time>g_SpikeGuardBreakpoint) {
                    Main.batch.draw(player_img, x, y, -width, height);
                System.out.println("Left");
            }
        }
        else if(R) {
            for (int i = 0; i < 17; i++) {
                player_img = new Texture("Assets/SPRITES/Megaman/Zero/Walk/" + i + ".png");
//                if(time>g_SpikeGuardBreakpoint) {
                    Main.batch.draw(player_img, x, y, width, height);
                System.out.println("Right");
            }
        }


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
        R = true;
        L = false;
        if (this.getX() - this.getWidth() / 2 >= Main.WIDTH / 2){
            System.out.println("MIDDLE");
            Main.xShift += speed;
            Main.cam.translate(Main.xShift, 0);
        }else{
            x += speed;
        }
    }

    public void goLeft() {
        R = false;
        L = true;
        if (this.getX() - this.getWidth() / 2 >= Main.WIDTH / 2){
            System.out.println("MIDDLE");
            Main.xShift -= speed;
            Main.cam.translate(Main.xShift, 0);
        }
        else{
            x -= speed;
        }
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

