package com.mygdx.game.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import java.util.Random;

import static com.badlogic.gdx.Gdx.graphics;

public class Main extends ApplicationAdapter {
    public static SpriteBatch batch;
    Texture bg;
    ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    ArrayList<PowerUp> powerups = new ArrayList<PowerUp>();

    public static final int WIDTH = 1024;
    public static final int HEIGHT = 1024;

    public static ShapeRenderer shapeRenderer;
    EnemiesCreator enemy;
    Player player;
    Random powerupDrop = new Random();

    public static HUD hud;

    @Override
    public void create() {
        graphics.setWindowedMode(WIDTH, HEIGHT);
        bg = new Texture("Assets/jpgs/space-1.jpg");
        batch = new SpriteBatch();
        player = new Player(0, 50);
        shapeRenderer = new ShapeRenderer();
        enemy = new EnemiesCreator();
        hud = new HUD();
    }


    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) player.goLeft();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) player.goRight();
        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)){
            player.usePowerup();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && !player.isShooting() && bullets.size() == 0) {
            bullets.add(player.shootBullet());
        }
//        System.out.println(System.currentTimeMillis());

        batch.begin();
        batch.draw(bg, 0, 0);


        player.update(batch);

        int isDrop = powerupDrop.nextInt(1000);
        if (isDrop < 2 && powerups.size() == 0) powerups.add(new PowerUp());
        for (int i = 0; i < powerups.size(); i++) {
            powerups.get(i).update(batch);
            if (powerups.get(i).getRect().y + powerups.get(i).getRect().height < 0) {
                powerups.remove(i);
            }else if (player.isCollidingWith(powerups.get(i))){
                player.getPowerup(powerups.get(i));
                powerups.remove(i);
            }
        }

        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).update(batch);
            if (bullets.get(i).getY() > HEIGHT) {
                bullets.remove(i);
                player.setShooting(false);
            }
        }

        enemy.update(batch);
        hud.update(batch);
        batch.end();
        System.out.println(Gdx.input.getX() + " " + Gdx.input.getY());
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        // insert any shape you want to create
        shapeRenderer.end();

    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
