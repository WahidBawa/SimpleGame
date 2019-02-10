package com.mygdx.game.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.badlogic.gdx.Gdx.graphics;

public class IntroEnd extends ApplicationAdapter{
    Music music;
    public static SpriteBatch batch;
    Texture background ;
    BitmapFont font;
    Texture player;
    Texture bullet;
    Texture enemy;
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 1024;

    @Override
    public void create() {
        player = new Texture("Assets/0.png");
        bullet = new Texture("Assets/1.png");
        font = new BitmapFont(Gdx.files.internal("Assets/one/impact.fnt"));
        font.getData();
        background=new Texture("Assets/start.png");
        batch = new SpriteBatch();
        music = Gdx.audio.newMusic(Gdx.files.internal("Assets/1.mp3"));
        graphics.setWindowedMode(WIDTH, HEIGHT);
    }

    @Override
    public void render() {
        music.play();
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background,0, 0);
        font.draw(batch, " SPACE LEGEND", 50, 300);
        batch.draw(player,275,0);
        batch.draw(bullet,275,380);
        batch.draw(enemy,275,400);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}