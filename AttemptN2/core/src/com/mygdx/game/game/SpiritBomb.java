package com.mygdx.game.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpiritBomb extends Bullet {
    private float x, y, width;
    Texture bullet_sprite;
    Sprite bullet;
    public SpiritBomb(float x, float y, float width) {
        this.x = x;
        this.y = y;
        this.width = width;
        bullet_sprite = new Texture("Assets/spiritbomb_attack.png");
        bullet = new Sprite(bullet_sprite);
    }

    @Override
    public void render(SpriteBatch batch) {
        super.render(batch);
    }

    @Override
    public void update(SpriteBatch batch) {
        super.update(batch);
    }

    @Override
    public float getX() {
        return super.getX();
    }

    @Override
    public float getY() {
        return super.getY();
    }
}
