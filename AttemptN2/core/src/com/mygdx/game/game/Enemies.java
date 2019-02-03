package com.mygdx.game.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;


public class Enemies {
    private int[][] enemit={{3,3,3,3,3,3,3},{2,2,2,2,2,2,2},{2,2,2,2,2,2,2},{1,1,1,1,1,1,1},{1,1,1,1,1,1,1}};
    private static ArrayList<Integer> location=new ArrayList<Integer>();
    //texture for our enemies
    private Texture redship = new Texture("Assets/Enemies/0.png");
    public Texture yellowship = new Texture("Assets/Enemies/1.png");
    public Texture blueship = new Texture("Assets/Enemies/2.png");
    private static final int DEAD=0;
    private static final int RED=3;
    private static final int YELLOW=2;
    private static final int BLUE=1;
    //initialization code

    public Enemies() {
        location=getinitLocation();
        System.out.println(getinitLocation().toString());
    }
    private ArrayList getinitLocation(){
        int wid=redship.getWidth();
        int hei=redship.getHeight();
        ArrayList<Point> one=new ArrayList<Point>();
        int x=100;
        int y=900;
        for(int i=1;i<6;i++) {
            for (int j = 1; j < 8; j++) {
                Point point =Point((x+j*(wid+5)),(y+i*(hei+5)));
                one.add(point);
            }
        }
        return one;
    }

//
//
//    //updates character's position
//    private void render() {
//        if (L) {
//            for (int i = 0; i < 17; i++) {
//                player_img = new Texture("Assets/SPRITES/Megaman/Zero/Walk/" + i + ".png");
////                if(time>g_SpikeGuardBreakpoint) {
//                Main.batch.draw(player_img, x, y, -width, height);
//                System.out.println("Left");
//            }
//        }
//        public void update () {
//            if (!jump) {
//                this.goDown();
//            }
//
//            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) this.goRight();
//            else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) this.goLeft();
//            else if (Gdx.input.isKeyPressed(Input.Keys.UP)) this.jump();
//
//            counter += 1;
//            if (counter > animation_speed) {
//                counter = 0;
//                pos += 1;
//                if (pos >= 3) {
//                    pos = 0;
//                }
//            }
//
//        }
//    }
}

