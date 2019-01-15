package com.mygdx.game.game;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.world.GameMap;
import com.mygdx.game.world.TiledGameMap;

import java.util.ArrayList;

public class Main<player> extends ApplicationAdapter {
    public static SpriteBatch batch;
    Texture img;
    OrthographicCamera cam1;

    GameMap gameMap;

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    private ShapeRenderer shapeRenderer;

    private int counter = 0;
    private int animation_speed = 2;
    private int pos = 0;

    public final static int RIGHT = 1;
    public final static int LEFT = -1;
    public final static int UP = 2;
    public final static int DOWN = -2;
    public final static int STANDING = 0;
    int direction = STANDING;

    int x = 0;
    int y = 0;

    public static ArrayList<Player> players = new ArrayList<Player>();
    public static ArrayList<Object> walls = new ArrayList<Object>();
    public static ArrayList<Object> ladders = new ArrayList<Object>();

    Player player;

    public static AllClassRenderer acr = new AllClassRenderer(players, walls, ladders);

    @Override
    public void create() {
        batch = new SpriteBatch();
        Gdx.graphics.setWindowedMode(WIDTH, HEIGHT);
        cam1 = new OrthographicCamera();
        cam1.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam1.translate(-185, -145);
        cam1.zoom = 0.32000038f;
        cam1.update();

//        player = new Player();

        gameMap = new TiledGameMap();

        batch = new SpriteBatch();
        player = new Player("Yoh");
        shapeRenderer = new ShapeRenderer();

        System.out.println();

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameMap.render(cam1);
        batch.begin();

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) direction = RIGHT;
        else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) direction = LEFT;
        else if (Gdx.input.isKeyPressed(Input.Keys.UP)) direction = UP;
        else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) direction = DOWN;
        else direction = STANDING;

//        if (Gdx.input.isKeyPressed(Input.Keys.A)){
//            cam1.zoom += 0.02;
//        }
//        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
//            cam1.zoom -= 0.02;
//        }


        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            x += 1;
        }else if (Gdx.input.isKeyPressed(Input.Keys.A)){
            x -= 1;
        }else if (Gdx.input.isKeyPressed(Input.Keys.W)){
            y += 1;
        }else if (Gdx.input.isKeyPressed(Input.Keys.S)){
            y -= 1;
        }else{
            y = 0;
            x = 0;
        }
        cam1.translate(x, y);
        player.update(direction);
        cam1.update();

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
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
