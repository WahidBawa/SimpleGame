package com.mygdx.game.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;

public class Mirror {
    private Sprite mirror = new Sprite(new Texture("Assets/Mirror/0.png"));
    private Rectangle rect;
    private int timesHit = 0;
    private boolean isBroken = false;

    public Mirror() {
        rect = new Rectangle((int) mirror.getX(), (int) mirror.getY(), (int) mirror.getWidth(), (int) mirror.getHeight());
    }

    public void render(SpriteBatch batch, int num) {
        mirror.setX(200 + (mirror.getWidth() + 100) * num);
        mirror.setY(125);
        rect = new Rectangle((int) mirror.getX(), (int) mirror.getY(), (int) mirror.getWidth(), (int) mirror.getHeight());
        mirror.draw(batch);

    }

    public void update(SpriteBatch batch, int num) {
        if (!isBroken) this.render(batch, num);
    }

    public Rectangle getRect() {
        return rect;
    }

    public boolean isCollidesWith(Bullet bullet){
        return this.getRect().intersects(bullet.getRect()) && bullet.getType() == 1 && !isBroken;
    }

    public void hit(){
        timesHit += 1;
        if (timesHit >= 3){
            isBroken = true;
        }
    }


}
