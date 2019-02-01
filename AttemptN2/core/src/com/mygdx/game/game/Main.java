package com.mygdx.game.game;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.world.TiledGameMap;

import java.util.ArrayList;

public class Main extends ApplicationAdapter {
    public static SpriteBatch batch;
    Texture img;
    public static OrthographicCamera cam;


    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    public static ShapeRenderer shapeRenderer;

    TiledGameMap gameMap;

    public static int xShift = 0;
    public static int yShift = 0;

    public static ArrayList<Player> players = new ArrayList<Player>();
    public static ArrayList<Walls> walls = new ArrayList<Walls>();
    public static ArrayList<Object> ladders = new ArrayList<Object>();

    Player player;

    int count = 0;
    boolean jump = false;


    @Override
    public void create() {
        Gdx.graphics.setWindowedMode(WIDTH, HEIGHT);
        img = new Texture("Assets/badlogic.jpg");
        batch = new SpriteBatch();
        cam = new OrthographicCamera();
        cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//        xShift = -230;
//        yShift = 570;
//        cam.translate(xShift, yShift);
//        cam.rotate(100);

        gameMap = new TiledGameMap();

        batch = new SpriteBatch();
        player = new Player("Yoh");
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameMap.render(cam);
        batch.begin();

        // insert here what ever you want to draw that is not a shape

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
