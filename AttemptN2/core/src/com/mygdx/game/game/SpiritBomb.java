package com.mygdx.game.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;

public class SpiritBomb{
    private float x, y, width;
    Texture spiritbomb_sprite;
    Sprite spiritbomb;

    Rectangle rect;

    public SpiritBomb(float x, float y, float width) {
        this.x = x;
        this.y = y;
        this.width = width;
        spiritbomb_sprite = new Texture("Assets/spiritbomb_attack.png");
        spiritbomb = new Sprite(spiritbomb_sprite);
        rect = new Rectangle((int) spiritbomb.getX(), (int) spiritbomb.getY(), (int) spiritbomb.getWidth(), (int) spiritbomb.getHeight());
    }

    public void render(SpriteBatch batch) {
        spiritbomb.setX(x + width / 2 - spiritbomb.getWidth() / 2);
        rect = new Rectangle((int) spiritbomb.getX(), (int) spiritbomb.getY(), (int) spiritbomb.getWidth(), (int) spiritbomb.getHeight());
        spiritbomb.draw(batch);
    }

    public void update(SpriteBatch batch) {
        y += 12;
        spiritbomb.setY(y);
        this.render(batch);
    }

    public float getX() {
        return this.getX();
    }

    public float getY() {
        return this.getY();
    }

    public Rectangle getRect() {
        return rect;
    }
}
