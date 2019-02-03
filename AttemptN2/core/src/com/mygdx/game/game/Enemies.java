//package com.mygdx.game.game;
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Input;
//import com.badlogic.gdx.InputProcessor;
//import com.badlogic.gdx.graphics.Texture;
//
//import java.util.ArrayList;
//
//
//public class Enemies {
//    //stores x loc (start at 240)
//    private int x = 50;
//    //stores y loc (start at 167)
//    private int y = 1000;
//    //texture for our enemies
//    public Texture enemy1_img1 = new Texture("Assets/Enemies/1.png");
//    public Texture enemy1_img2=new Texture("Assets/Enemies/2.png");
//    private Texture enemy2_img1=new Texture("Assets/Enemies/3.png");
//    public Texture enemy2_img2=new Texture("Assets/Enemies/4.png");
//    public Texture enemy3_img1=new Texture("Assets/Enemies/5.png");
//    public Texture enemy3_img2=new Texture("Assets/Enemies/6.png");
//    //speed for our character in pixels/s
//    private String name;
//    private int width, height;
//
//    private int counter = 0;
//    private int pos = 0;
//    private int animation_speed = 2;
//
//    private int speed = 10;
//
//    private boolean jump = false;
//
//    private boolean isTouchingGround = false;
//
//    private boolean L = false;
//    private boolean R = false;
//
//    //initialization code
//    public Enemies(String name) {
//        this.name = name;
//        Main.players.add(this);
//        height = player_img.getHeight();
//        width = player_img.getWidth();
//    }
//
//    //updates character's position
//    public void render() {
//        if(L){
//            for(int i=0;i<17;i++){
//                player_img = new Texture("Assets/SPRITES/Megaman/Zero/Walk/"+i+".png");
////                if(time>g_SpikeGuardBreakpoint) {
//                Main.batch.draw(player_img, x, y, -width, height);
//                System.out.println("Left");
//            }
//        }
//        else if(R) {
//            for (int i = 0; i < 17; i++) {
//                player_img = new Texture("Assets/SPRITES/Megaman/Zero/Walk/" + i + ".png");
////                if(time>g_SpikeGuardBreakpoint) {
//                Main.batch.draw(player_img, x, y, width, height);
//                System.out.println("Right");
//            }
//        }
//    }
//    public void update() {
//        if (!jump){
//            this.goDown();
//        }
//
//        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) this.goRight();
//        else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) this.goLeft();
//        else if(Gdx.input.isKeyPressed(Input.Keys.UP)) this.jump();
//
//        counter += 1;
//        if (counter > animation_speed) {
//            counter = 0;
//            pos += 1;
//            if (pos >= 3) {
//                pos = 0;
//            }
//        }
//
//    }
//
//
