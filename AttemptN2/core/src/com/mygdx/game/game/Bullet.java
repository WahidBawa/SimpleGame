package com.mygdx.game.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;

public class Bullet {
    public int type;
    private static final int player=0;
    private static final int enemy=1;
    private float x, y, width;
    Texture bullet_sprite;
    Sprite bullet;
    Rectangle rect;

    public Bullet(float x, float y, float width,int t) {
        this.x = x;
        this.y = y;
        this.width = width;
        type=t;
        if(type==player) {
            bullet_sprite = new Texture("Assets/1.png");
        }
        else if(type==enemy){
            bullet_sprite = new Texture("Assets/1flipped.png");
        }
        bullet = new Sprite(bullet_sprite);
        rect = new Rectangle((int) bullet.getX(), (int) bullet.getY(), (int) bullet.getWidth(), (int) bullet.getHeight());
    }

    public void render(SpriteBatch batch) {
        bullet.setX(x + width / 2 - bullet.getWidth() / 2);
        rect = new Rectangle((int) bullet.getX(), (int) bullet.getY(), (int) bullet.getWidth(), (int) bullet.getHeight());
        bullet.draw(batch);
    }

    public void update(SpriteBatch batch) {
        if(type==player) {
            y += 12;
        }
        else if(type==enemy){
            y-=6;
        }
        bullet.setY(y);
        this.render(batch);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Rectangle getRect() {
        return rect;
    }
}
