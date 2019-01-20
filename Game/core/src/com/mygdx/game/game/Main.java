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

public class Main<player> extends ApplicationAdapter {
    public static SpriteBatch batch;
    Texture img;
    OrthographicCamera cam;


    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    public static ShapeRenderer shapeRenderer;

    private int counter = 0;
    private int animation_speed = 2;
    private int pos = 0;

    TiledGameMap gameMap;
    public final static int RIGHT = 1;
    public final static int LEFT = -1;
    public final static int UP = 2;
    public final static int DOWN = -2;
    public final static int STANDING = 0;
    int direction = STANDING;

    public static int xShift = 0;
    public static int yShift = 0;

    public static ArrayList<Player> players = new ArrayList<Player>();
    public static ArrayList<Walls> walls = new ArrayList<Walls>();
    public static ArrayList<Object> ladders = new ArrayList<Object>();

    Player player;

//    public static AllClassRenderer acr = new AllClassRenderer(players, walls, ladders);

    @Override
    public void create() {
        Gdx.graphics.setWindowedMode(WIDTH, HEIGHT);
        img = new Texture("Assets/badlogic.jpg");
        batch = new SpriteBatch();
        cam = new OrthographicCamera();
        cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//        xShift = -250;
//        yShift = 1270;
        System.out.println(xShift);
        System.out.println(yShift);
        cam.translate(xShift, yShift);
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

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) direction = RIGHT;
        else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) direction = LEFT;
        else if (Gdx.input.isKeyPressed(Input.Keys.UP)) direction = UP;
        else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) direction = DOWN;
        else direction = STANDING;
        System.out.println(cam.zoom);
        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            cam.zoom += 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            cam.zoom -= 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.R)){
            cam.zoom = 1;
        }

        for (Walls i : walls) i.update();

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            xShift = 10;
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            xShift = -10;
        } else if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            yShift = 10;
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            yShift = -10;
        } else {
            yShift = 0;
            xShift = 0;
        }

        cam.translate(xShift, yShift);
        System.out.println(cam.position.x + " " + cam.position.y); // 640, 360 // 390, 1630
        player.update(direction);
        cam.update();

        player.render();

        counter += 1;
        if (counter > animation_speed) {
            counter = 0;
            pos += 1;
            if (pos >= 3) {
                pos = 0;
            }
        }
        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        for (Walls i : walls) shapeRenderer.rect(i.getX(), i.getY(), i.getWidth(), i.getHeight());
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.circle(player.getX(), player.getY(), 1);
        shapeRenderer.end();

    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
