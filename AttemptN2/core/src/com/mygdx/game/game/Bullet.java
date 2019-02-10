package com.mygdx.game.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;

public class Bullet {
    private float x, y, width;
    Texture bullet_sprite;
    Sprite bullet;
    Rectangle rect;

    public Bullet(float x, float y, float width) {
        this.x = x;
        this.y = y;
        this.width = width;
        bullet_sprite = new Texture("Assets/1.png");
        bullet = new Sprite(bullet_sprite);
        rect = new Rectangle((int) bullet.getX(), (int) bullet.getY(), (int) bullet.getWidth(), (int) bullet.getHeight());
    }

    public void render(SpriteBatch batch) {
        bullet.setX(x + width / 2 - bullet.getWidth() / 2);
        rect = new Rectangle((int) bullet.getX(), (int) bullet.getY(), (int) bullet.getWidth(), (int) bullet.getHeight());
        bullet.draw(batch);
    }

    public void update(SpriteBatch batch) {
        y += 12;
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
