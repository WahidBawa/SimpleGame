package com.mygdx.game.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class PowerUp {
    private int x, y, type, fallSpeed;
    private final static int SPIRITBOMB = 0;
    private final static int INVINCIBLE = 1;
    private Random rand = new Random();
    private Texture powerup_sprite;
    private Sprite powerup;

    public PowerUp(int type) {
        Texture[] powerups = {new Texture("Assets/spiritBomb.png"), new Texture("Assets/invincible.png")};
        powerup_sprite = powerups[rand.nextInt(powerups.length)];
        powerup = new Sprite(powerup_sprite);
        fallSpeed = rand.nextInt(5) + 1;
        x = rand.nextInt(Main.WIDTH - powerup_sprite.getWidth());
        y = Main.HEIGHT;
        powerup.setX((float) x);
        powerup.setY((float) y);
    }

//    private void powerupON(){//upon collecting the powerup this is called
//        if(powerType==mirror){
//            mirror_sprite=new Texture("Assets/Mirror/0.png");
//        }
//    }

    public void update(SpriteBatch batch) {
        powerup.setY(powerup.getY() - fallSpeed);
        this.render(batch);
    }

    public void render(SpriteBatch batch) {
        powerup.draw(batch);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
