package com.mygdx.game.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class EnemiesCreator {
    private int[][] enemit = {{3, 3, 3, 3, 3, 3, 3, 3, 3}, {2, 2, 2, 2, 2, 2, 2, 2, 2}, {2, 2, 2, 2, 2, 2, 2, 2, 2}, {1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1}};
    public static ArrayList<Point> location = new ArrayList<Point>();
    //texture for our enemies
    private Sprite redship_sprite;
    private Sprite yellowship_sprite;
    private Sprite blueship_sprite;
    private Texture blueship;
    private Texture yellowship;
    private Texture redship;
    private static final int DEAD = 0;
    private static final int RED = 3;
    private static final int YELLOW = 2;
    private static final int BLUE = 1;

    //initialization code

    public EnemiesCreator() {
        redship = new Texture("Assets/Enemies/0.png");
        yellowship = new Texture("Assets/Enemies/1.png");
        blueship = new Texture("Assets/Enemies/2.png");
        redship_sprite = new Sprite(redship);
        yellowship_sprite = new Sprite(yellowship);
        blueship_sprite = new Sprite(blueship);
        location = getinitLocation();
//        System.out.println("pis");
    }

    private ArrayList getinitLocation() {
        int wid = (int) redship.getWidth();
        int hei = (int) redship.getHeight();
        ArrayList<Point> one = new ArrayList<Point>();
        int x = 50;
        int y = 900;
        for (int i = 1; i < 6; i++) {
            for (int j = 1; j < 9; j++) {
                Point point = new Point((x + j * (wid + 10)), (y - i * (hei + 20)));
                one.add(point);
            }
        }
        return one;
    }

    private void render(SpriteBatch batch, int type) {
        if (type == RED) {
            redship_sprite.draw(batch);
        } else if (type == YELLOW) {
            yellowship_sprite.draw(batch);
        } else if (type == BLUE) {
            blueship_sprite.draw(batch);
        }
    }

    public void update(SpriteBatch batch) {
        Timer timer = new Timer();
        TimerTask one = new TimerTask() {
            @Override
            public void run() {
                moveSideway();
//                System.out.println(location.get(location.size() - 1).getX());
                cancel();

            }
        };
        timer.schedule(one, Long.valueOf(2 * 1000), Long.valueOf(1));
        int counter = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 8; j++) {
                int enemyType = enemit[i][j];
                Point point = location.get(counter);
                counter += 1;
                if (enemyType == RED) {
                    redship_sprite.setX(point.x);
                    redship_sprite.setY(point.y);
                    this.render(batch, enemyType);
                } else if (enemyType == YELLOW) {
                    yellowship_sprite.setX(point.x);
                    yellowship_sprite.setY(point.y);
                    this.render(batch, enemyType);
                } else if (enemyType == BLUE) {
                    blueship_sprite.setX(point.x);
                    blueship_sprite.setY(point.y);
                    this.render(batch, enemyType);
                }

            }
        }
    }

    private boolean R = true;
    private boolean L = false;

    public void moveSideway() {
        if (R) {
            if (location.get(location.size() - 1).getX() < 1000 - redship_sprite.getWidth()) {
                for (int i = 0; i < location.size(); i++) {
                    int x = (int) location.get(i).getX();
                    int y = (int) location.get(i).getY();
                    location.set(i, new Point(x + 1, y));
                }
            } else moveDown();
        } else if (L) {
            if (location.get(0).getX() > 24) {
                for (int i = 0; i < location.size(); i++) {
                    int x = (int) location.get(i).getX();
                    int y = (int) location.get(i).getY();
                    location.set(i, new Point(x - 1, y));

                }
            } else moveDown();
        }
    }

    private void moveDown() {
        if (R) {
            R = false;
            L = true;
        } else if (L) {
            R = true;
            L = false;
        }

        for (int i = 0; i < location.size(); i++) {
            int x = (int) location.get(i).getX();
            int y = (int) location.get(i).getY();
            location.set(i, new Point(x, y - 10));
        }
    }
}
