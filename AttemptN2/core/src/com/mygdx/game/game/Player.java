package com.mygdx.game.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;
import java.util.ArrayList;

public class Player {
    private float x, y;
    private Texture player_sprite;
    private Sprite player;
    private boolean shooting = false;
    private final static int SPIRITBOMB = 0;
    private final static int INVINCIBLE = 1;
    public boolean isAlive = true;
    private final static int MIRROR = 2;
    private boolean using_spiritbomb = false;
    private SpiritBomb spiritbomb;
    private ArrayList<Integer> powerupID = new ArrayList<Integer>();
    private int points = 0;
    private int lives = 3;
    private boolean invincible = false;
    private Sprite barrier = new Sprite(new Texture("Assets/barriers.png"));
    boolean musicPlaying = false;
    Rectangle rect;

    public Player(float x, float y) {
        player_sprite = new Texture("Assets/0.png");
        player = new Sprite(player_sprite);
        rect = new Rectangle((int) player.getX(), (int) player.getY(), (int) player.getWidth(), (int) player.getHeight());
        this.x = x;
        this.y = y;
        player.setX(x);
        player.setY(y);
    }

    //updates character's position
    public void render(SpriteBatch batch) {
        if (invincible) {
            barrier.setX(player.getX() - 35);
            barrier.setY(player.getY() - 40);
            barrier.draw(batch);
        }
        player.draw(batch);
    }

    public void update(SpriteBatch batch) {
        player.setX(x);
        player.setY(y);
        rect = new Rectangle((int) player.getX(), (int) player.getY(), (int) player.getWidth(), (int) player.getHeight());
//        System.out.println(rect.toString());
        if (using_spiritbomb) {
            spiritbomb.update(batch);
            if (spiritbomb.getRect().y > Main.HEIGHT) {
                using_spiritbomb = false;
            }
            for (int i = 0; i < Main.enemies.size(); i++) {
                for (int n = 0; n < Main.enemies.get(i).size(); n++) {
                    if (Main.enemies.get(i).get(n).isCollidingWith(spiritbomb)) {
                        this.addPoints(Main.enemies.get(i).get(n).getPointValue());
                        Main.enemies.get(i).get(n).setDead(true, this);
                    }
                }
            }
        }
        render(batch);
    }

    public void usePowerup() {
        if (powerupID.size() > 0) {
            if (powerupID.get(0) == SPIRITBOMB) {
                using_spiritbomb = true;
                spiritbomb = new SpiritBomb(player.getX(), player.getY(), player.getWidth());
            }else if (powerupID.get(0) == INVINCIBLE){
                invincible = true;
            }
            powerupID.remove(0);
            Main.hud.removePowerup();
        }
    }

    public void getPowerup(PowerUp powerup) {
        int type = powerup.getType();
        if (powerupID.size() == 0) {
            if (type == INVINCIBLE) {
                Main.hud.addPowerup(new Texture("Assets/invincible.png"));
                powerupID.add(INVINCIBLE);
            } else if (type == SPIRITBOMB) {
                Main.hud.addPowerup(new Texture("Assets/spiritbomb.png"));
                powerupID.add(SPIRITBOMB);
            } else if (type == MIRROR) {
                Main.hud.addPowerup(new Texture("Assets/Mirror.png"));
                powerupID.add(MIRROR);
            }
        }
    }

    public void goLeft() {
        if (player.getX() > 0) x -= 8;
    }

    public void goRight() {
        if (player.getX() + player.getWidth() < Main.WIDTH) x += 8;
    }

    public Bullet shootBullet() {
        shooting = true;
        return new Bullet(player.getX(), player.getY(), player.getWidth(), 0);
    }

    public boolean isShooting() {
        return shooting;
    }

    public void setShooting(boolean shooting) {
        this.shooting = shooting;
    }

    public boolean isCollidingWith(PowerUp powerup) {
        return powerup.getRect().intersects(this.getRect());
    }

    public boolean isCollidingWith(Bullet bullet) {
        return bullet.getRect().intersects(this.getRect()) && bullet.getType() == 1;
    }

    public Rectangle getRect() {
        return rect;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public int getPoints() {
        return points;
    }

    public int getLives() {
        return lives;
    }

    public void takeAwayLife() {
        if (!invincible) lives -= 1;
        else invincible = false;
    }

}

