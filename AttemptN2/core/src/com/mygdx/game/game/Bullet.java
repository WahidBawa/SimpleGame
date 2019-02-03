package com.mygdx.game.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.Rectangle;

public class Bullet {
    private float x, y;
    Texture bullet_sprite;
    Sprite bullet;
    public Bullet(float x, float y){
        this.x = x;
        this.y = y;
        bullet_sprite = new Texture("Assets/1.png");
        bullet = new Sprite(bullet_sprite);

    }

    public void render(SpriteBatch batch){
        bullet.setX(x);
        bullet.draw(batch);
    }

    public void update(SpriteBatch batch){
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
}
