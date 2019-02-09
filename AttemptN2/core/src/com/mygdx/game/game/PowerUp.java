package com.mygdx.game.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;
import java.util.Random;

public class PowerUp {
    private int x, y, type, fallSpeed;
    private Random rand = new Random();
    private Texture powerup_sprite;
    private Sprite powerup;

    Rectangle rect;

    public PowerUp() {
        Texture[] powerups = {new Texture("Assets/spiritBomb.png"), new Texture("Assets/invincible.png"), new Texture("Assets/Mirror.png")};
        type = rand.nextInt(powerups.length);
        powerup_sprite = powerups[type];
        powerup = new Sprite(powerup_sprite);
        fallSpeed = rand.nextInt(5) + 1;
        x = rand.nextInt(Main.WIDTH - powerup_sprite.getWidth());
        y = Main.HEIGHT;
        powerup.setX(x);
        powerup.setY(y);
        rect = new Rectangle((int) powerup.getX(), (int) powerup.getY(), (int) powerup.getWidth(), (int) powerup.getHeight());
    }

    public void render(SpriteBatch batch) {
        powerup.draw(batch);
}

    public void update(SpriteBatch batch) {
        powerup.setY(powerup.getY() - fallSpeed);
        rect = new Rectangle((int) powerup.getX(), (int) powerup.getY(), (int) powerup.getWidth(), (int) powerup.getHeight());
        this.render(batch);
    }

    public Rectangle getRect() {
        return rect;
    }

    public int getType() {
        return type;
    }

}
