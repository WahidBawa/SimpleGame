package com.mygdx.game.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;

public class Bullet {
    public int type;
    private static final int PLAYER = 0;
    private static final int ENEMY = 1;
    private float x, y, width;
    private int speed = 12;
    Texture bullet_sprite;
    Sprite bullet;
    Rectangle rect;

    public Bullet(float x, float y, float width, int type) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.type = type;
        if (type == PLAYER) {
            bullet_sprite = new Texture("Assets/1.png");
        } else if (type == ENEMY) {
            bullet_sprite = new Texture("Assets/1flipped.png");
        }
        speed = (type == 0 ? 12 : -12);
        bullet = new Sprite(bullet_sprite);
        rect = new Rectangle((int) bullet.getX(), (int) bullet.getY(), (int) bullet.getWidth(), (int) bullet.getHeight());
    }

    public void render(SpriteBatch batch) {
        bullet.setX(x + width / 2 - bullet.getWidth() / 2);
        rect = new Rectangle((int) bullet.getX(), (int) bullet.getY(), (int) bullet.getWidth(), (int) bullet.getHeight());
        bullet.draw(batch);
    }

    public void update(SpriteBatch batch) {
        if (this.type == PLAYER) {
            y += speed;
        } else if (this.type == ENEMY) {
            y += speed;
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

    public int getType() {
        return type;
    }

    public void reflect(){
        speed *= -1;
        bullet_sprite = new Texture("Assets/1.png");
        bullet = new Sprite(bullet_sprite);
        type = 0;
        Main.enemybullets.remove(Main.enemybullets.indexOf(this));
        Main.bullets.add(this);
    }


}
