package com.mygdx.game.game;
import java.util.*;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Timer;

import static com.badlogic.gdx.Gdx.graphics;

public class Main extends ApplicationAdapter {
    public static SpriteBatch batch;
    Texture bg;

    Bullet bullet;

    public static final int WIDTH = 1024;
    public static final int HEIGHT = 1024;

    public static ShapeRenderer shapeRenderer;
    EnemiesCreator enemy;
    Player player;
    @Override
    public void create() {
        graphics.setWindowedMode(WIDTH, HEIGHT);
        bg = new Texture("Assets/jpgs/space-1.jpg");
        batch = new SpriteBatch();
        player = new Player(0, 50);
        shapeRenderer = new ShapeRenderer();
        enemy= new EnemiesCreator();
    }


    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) player.goLeft();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) player.goRight();
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && !player.isShooting()) {
            bullet = player.shootBullet();
//            System.out.println(bullet.getX());
        }

        batch.begin();
        batch.draw(bg, 0, 0);
        if (player.isShooting()) {
            bullet.update(batch);
            if (bullet.getY() > HEIGHT) {
                player.setShooting(false);
            }
        }
        player.update(batch);

        // insert here what ever you want to draw that is not a shape
        enemy.update(batch);
        batch.end();
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
