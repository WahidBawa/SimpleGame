package com.mygdx.game.game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class EnemiesCreator {
    Rectangle rect;
    public static int x;
    public static int y;
    public static int enemyType;
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

    public EnemiesCreator(int col, int row) {
        if (row == 5) {
            redship = new Texture("Assets/Enemies/0.png");
            redship_sprite = new Sprite(redship);
            enemyType = RED;
        }
        else if (row >= 3 && row < 5) {
            yellowship = new Texture("Assets/Enemies/1.png");
            yellowship_sprite = new Sprite(yellowship);
            enemyType = YELLOW;

        }
        else {
            enemyType = BLUE;
        }
        blueship = new Texture("Assets/Enemies/2.png");
        blueship_sprite = new Sprite(blueship);
        x = 50 + (int)blueship_sprite.getWidth() + (row * 10);
        y = 600 + (int)blueship_sprite.getHeight() + (col * 10);
    }


    private void render(SpriteBatch batch) {
        if (enemyType == RED) {
            redship_sprite.draw(batch);
        } else if (enemyType == YELLOW) {
            yellowship_sprite.draw(batch);
        } else if (enemyType == BLUE) {
            blueship_sprite.draw(batch);
        }
    }

    public void update(SpriteBatch batch,ArrayList<ArrayList<EnemiesCreator>> en) {
        moveSideway(en);
        if (enemyType == RED) {
            redship_sprite.setX(x);
            redship_sprite.setY(y);
            this.render(batch);
        }
        else if (enemyType == YELLOW) {
            yellowship_sprite.setX(x);
            yellowship_sprite.setY(y);
            this.render(batch);
        }
        else if (enemyType == BLUE) {
            blueship_sprite.setX(x);
            blueship_sprite.setY(y);
            this.render(batch);
        }
        rect = new Rectangle(this.x, this.y, (int) redship_sprite.getWidth(), (int) redship_sprite.getHeight());
    }


    private boolean R = true;
    private boolean L = false;
    public void moveSideway(ArrayList<ArrayList<EnemiesCreator>> en) {
        int size = en.get(0).size() - 1;
        if (en.get(0).get(size).x < 950 && en.get(0).get(0).x > 50) {
            for (int i = 0; i < 5; ) {
                for (int p = 0; p < en.get(i).size(); p++) {
                    if (R) {
                        en.get(i).get(p).x = en.get(i).get(p).x + 1;
                    } else if (L) {
                        en.get(i).get(p).x = en.get(i).get(p).x - 1;
                    }
                }
            }
        } else moveDown(en);
    }

    private void moveDown(ArrayList<ArrayList<EnemiesCreator>> en) {
        if (R) {
            R = false;
            L = true;
        } else if (L) {
            R = true;
            L = false;
        }
        for (int i = 0; i < 5; ) {
            for (int p = 0; p < en.get(i).size(); p++) {
                en.get(i).get(p).y = en.get(i).get(p).y - 10;
            }
        }
    }
}