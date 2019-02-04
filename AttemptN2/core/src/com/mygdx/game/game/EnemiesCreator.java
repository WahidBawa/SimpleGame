package com.mygdx.game.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class EnemiesCreator {
    private int[][] enemit = {{3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, {2, 2, 2, 2, 2, 2, 2, 2, 2, 2}, {2, 2, 2, 2, 2, 2, 2, 2, 2, 2}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};
    public static ArrayList<Point> location = new ArrayList<Point>();
    //texture for our enemies
    private Texture redship_sprite;
    private Texture yellowship_sprite;
    private Texture blueship_sprite;
    private Sprite blueship;
    private Sprite yellowship;
    private Sprite redship;
    private static final int DEAD = 0;
    private static final int RED = 3;
    private static final int YELLOW = 2;
    private static final int BLUE = 1;

    //initialization code

    public EnemiesCreator() {
        redship_sprite = new Texture("Assets/Enemies/0.png");
        yellowship_sprite = new Texture("Assets/Enemies/1.png");
        blueship_sprite = new Texture("Assets/Enemies/2.png");
        redship = new Sprite(redship_sprite);
        yellowship = new Sprite(yellowship_sprite);
        blueship = new Sprite(blueship_sprite);
        location = getinitLocation();
    }

    private ArrayList getinitLocation() {
        int wid = (int) redship.getWidth();
        int hei = (int) redship.getHeight();
        ArrayList<Point> one = new ArrayList<Point>();
        int x = 50;
        int y = 900;
        for (int i = 1; i < 6; i++) {
            for (int j = 1; j < 11; j++) {
                Point point = new Point((x + j * (wid + 10)), (y - i * (hei + 20)));
                one.add(point);
            }
        }
        return one;
    }

    private void render(SpriteBatch batch, int type) {
        if (type == RED) {
            redship.draw(batch);
        } else if (type == YELLOW) {
            yellowship.draw(batch);
        } else if (type == BLUE) {
            blueship.draw(batch);
        }
    }

    public void update(SpriteBatch batch) {
        Timer timer = new Timer();
        TimerTask one = new TimerTask() {
            @Override
            public void run() {
                moveSideway();
                cancel();

            }
        };
        timer.schedule(one, Long.valueOf(1 * 1000), Long.valueOf(1));
        int counter = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                int enemyType = enemit[i][j];
                Point point = location.get(counter);
                counter += 1;
                if (enemyType == RED) {
                    redship.setX(point.x);
                    redship.setY(point.y);
                    this.render(batch, enemyType);
                } else if (enemyType == YELLOW) {
                    yellowship.setX(point.x);
                    yellowship.setY(point.y);
                    this.render(batch, enemyType);
                } else if (enemyType == BLUE) {
                    blueship.setX(point.x);
                    blueship.setY(point.y);
                    this.render(batch, enemyType);
                }

            }
        }
    }

    private boolean R = true;
    private boolean L = false;

    public void moveSideway() {
        if (location.get(location.size() - 1).getX() < 1000 && location.get(location.size() - 1).getX() > 50) {
            for (int i = 0; i < location.size() - 1; i++) {
                int x = (int) location.get(i).getX();
                int y = (int) location.get(i).getY();
                if (R) {
                    location.set(i, new Point(x + 5, y));
                } else if (L) {
                    location.set(i, new Point(x - 5, y));

                }
            }
        } else {
            moveDown();
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

        for (int i = 0; i < location.size() - 1; i++) {
            int x = (int) location.get(i).getX();
            int y = (int) location.get(i).getY();
            location.set(i, new Point(x, y - 10));
        }
    }
}