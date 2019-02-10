package com.mygdx.game.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;


public class Enemy {
    public static int x;
    public static int y;
    public static int enemyType;
    //texture for our enemies
    private Sprite sprite;
    private Texture blueship;
    private Texture yellowship;
    private Texture redship;
    private boolean R = true;
    private boolean L = false;
    private boolean dead = false;
    private Rectangle rect;
    private int speed = 1;
    //initialization code


    public Enemy(String type, int x, int y) {
        blueship = new Texture("Assets/Enemies/2.png");
        redship = new Texture("Assets/Enemies/0.png");
        yellowship = new Texture("Assets/Enemies/1.png");

        if (type.equals("yellow")) {
            sprite = new Sprite(yellowship);
        } else if (type.equals("red")) {
            sprite = new Sprite(redship);
        } else if (type.equals("blue")) {
            sprite = new Sprite(blueship);
        }
        sprite.setX(50 + x * 100);
        sprite.setY(Main.HEIGHT - 200 - (75 * y));
        rect = new Rectangle((int) sprite.getX(), (int) sprite.getY(), (int) sprite.getWidth(), (int) sprite.getHeight());
    }


    public void render(SpriteBatch batch) {
        rect = new Rectangle((int) sprite.getX(), (int) sprite.getY(), (int) sprite.getWidth(), (int) sprite.getHeight());
        sprite.draw(batch);
    }

    public void update(SpriteBatch batch) {
        if (this.isDead()) {
            sprite.setAlpha(0);
        }
        sprite.setX(sprite.getX() + speed);
        this.render(batch);
    }

    public boolean isCollidingWith(Bullet bullet) {
        return bullet.getRect().intersects(this.getRect()) && !this.isDead();
    }

    public boolean isCollidingWith(SpiritBomb spiritBomb) {
        return spiritBomb.getRect().intersects(this.getRect()) && !this.isDead();
    }

    public Rectangle getRect() {
        return rect;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public void inverseSpeed(){
        speed *= -1;
    }

    public void setX(int x){
        sprite.setX(x);
    }
}

