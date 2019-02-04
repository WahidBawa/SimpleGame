package com.mygdx.game.game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player {
    private float x, y;
    private Texture player_sprite;
    private Sprite player;
    private boolean shooting = false;
    public Player(float x, float y) {
        player_sprite = new Texture("Assets/0.png");
        player = new Sprite(player_sprite);
        this.x = x;
        this.y = y;
        player.setX(x);
        player.setY(y);
    }

    //updates character's position
    public void render(SpriteBatch batch) {
        player.draw(batch);
    }

    public void update(SpriteBatch batch) {
        player.setX(x);
        player.setY(y);
        this.render(batch);
    }

    public void goLeft(){
        if (player.getX() > 0) x -= 8;
    }
    public void goRight(){
        if (player.getX() + player.getWidth() < Main.WIDTH) x += 8;
    }

    public Bullet shootBullet(){
        shooting = true;
        System.out.println(player.getX());
        return new Bullet(player.getX(), player.getY());
    }

    public boolean isShooting() {
        return shooting;
    }

    public void setShooting(boolean shooting) {
        this.shooting = shooting;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

}
