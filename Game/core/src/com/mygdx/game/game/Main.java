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

    Player player;
    ArrayList<Object> allClassRender = new ArrayList<Object>();

    @Override
    public void create() {
        batch = new SpriteBatch();
//        img = new Texture("Assets/SPRITES/Zain/0.png");
        img = new Texture("Assets/badlogic.jpg");
        Gdx.graphics.setWindowedMode(WIDTH, HEIGHT);
        cam1 = new OrthographicCamera();
        cam1.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam1.translate(-185, -145);
        cam1.zoom = 0.32000038f;
        cam1.update();

//        player = new Player();

        gameMap = new TiledGameMap();

        batch = new SpriteBatch();
        player = new Player();
//        for (String i : new String[]{"Up", "Down", "Left", "Right"}) {
//        for (String i : new String[]{"Zain"}) {
//            tmpSprite = new ArrayList<Texture>();
//            for (int n = 0; n < 3; n++) {
////                tmpSprite.add(new Texture("Assets/SPRITES/Pacman/" + i + "/" + n + ".png")); // change this to current sprites
//                tmpSprite.add(new Texture("Assets/SPRITES/" + i + "/" + n + ".png")); // change this to current sprites
//            }
//            pacman.add(tmpSprite);
//        }
//
////        img = pacman.get(RIGHT).get(1);
//        img = pacman.get(0).get(1);
        shapeRenderer = new ShapeRenderer();
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


        if (direction == RIGHT){
            x += 1;
        }else if (direction == LEFT){
            x -= 1;
        }else if (direction == UP){
            y += 1;
        }else if (direction == DOWN){
            y -= 1;
        }else{
            y = 0;
            x = 0;
        }
        cam1.translate(x, 2);
        player.update(direction);
        cam1.update();

        player.render(batch);


        counter += 1;
        if (counter > animation_speed) {
            counter = 0;
            pos += 1;
            if (pos >= 3) {
                pos = 0;
            }
        }

//        img = pacman.get(0).get(pos);

//        x += xShift;
//        y += yShift;


//        batch.draw(img, 0, 0, img.getWidth() * 4, img.getHeight() * 4);
//        batch.draw(img, 0, 0);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }
}
