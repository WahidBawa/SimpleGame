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
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.world.GameMap;
import com.mygdx.game.world.TileType;
import com.mygdx.game.world.TiledGameMap;

import java.util.ArrayList;

public class Main extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;
    OrthographicCamera cam1;

    GameMap gameMap;

    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;

    private int x = 1;
    private int y = 1;
    private int speed = 5;

    private ShapeRenderer shapeRenderer;

    ArrayList<ArrayList<Texture>> pacman = new ArrayList<ArrayList<Texture>>();

    private final static int UP = 0;
    private final static int DOWN = 1;
    private final static int LEFT = 2;
    private final static int RIGHT = 3;

    private int dir = 0;
    private int pos = 0;

    private int animation_speed = 7;
    private int counter = 0;

    private boolean pressed = false;
    private int xShift = 0;
    private int yShift = 0;

    @Override
    public void create() {
        batch = new SpriteBatch();
        img = new Texture("Assets/badlogic.jpg");
        Gdx.graphics.setWindowedMode(WIDTH, HEIGHT);
        cam1 = new OrthographicCamera();
        cam1.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam1.update();

        gameMap = new TiledGameMap();

        batch = new SpriteBatch();
        ArrayList<Texture> tmpSprite;

//        for (String i : new String[]{"Up", "Down", "Left", "Right"}) {
        for (String i : new String[]{"Zain"}) {
            tmpSprite = new ArrayList<Texture>();
            for (int n = 0; n < 3; n++) {
//                tmpSprite.add(new Texture("Assets/SPRITES/Pacman/" + i + "/" + n + ".png")); // change this to current sprites
                tmpSprite.add(new Texture("Assets/SPRITES/" + i + "/" + n + ".png")); // change this to current sprites
            }
            pacman.add(tmpSprite);
        }

//        img = pacman.get(RIGHT).get(1);
        img = pacman.get(0).get(1);
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        cam1.update();

        gameMap.render(cam1);
        batch.begin();

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            xShift = -speed;
            yShift = 0;
            dir = LEFT;
            pressed = true;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            xShift = speed;
            yShift = 0;
            dir = RIGHT;
            pressed = true;
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            yShift = speed;
            xShift = 0;
            dir = UP;
            pressed = true;
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            yShift = -speed;
            xShift = 0;
            dir = DOWN;
            pressed = true;
        }
        if (x - speed < 0) x = 0;
        if (x + img.getWidth() + speed > WIDTH) x = WIDTH - img.getWidth();

        if (y - speed < 0) y = 0;
        if (y + img.getHeight() + speed > HEIGHT) y = HEIGHT - img.getHeight();

        counter += 1;
        if (counter > animation_speed) {
            counter = 0;
            pos += 1;
            if (pos >= 3) {
                pos = 0;
            }
        }

        img = pacman.get(0).get(pos);

        x += xShift;
        y += yShift;


//        TileType type = gameMap.getTileTypeByLocation(1, 15, 465);
//        if (type != null)
//            System.out.println("YOu clicked on a tile with id " + type.getId() + " " + type.getName() + " " + type.isCollideable() + " " + type.getDamage());
//        else System.out.println("null tile");
        if (Gdx.input.justTouched()) {
            Vector3 pos = cam1.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.circle(pos.x, pos.y, 5);
            shapeRenderer.end();
            TileType type = gameMap.getTileTypeByLocation(2, pos.x, pos.y);
            if (type != null) {
                System.out.println("YOu clicked on a tile with id " + type.getId() + " " + type.getName() + " " + type.isCollideable() + " " + type.getDamage());
            }else{
                System.out.println("shit");
            }
        }


//		batch.draw(img, x, y);
        batch.draw(img, x, y, img.getWidth() * 4, img.getHeight() * 4);
//        batch.draw(img, x, y, 100, 100);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }
}
