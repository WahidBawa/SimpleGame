package com.mygdx.game.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import java.util.Random;

import static com.badlogic.gdx.Gdx.graphics;
import static com.badlogic.gdx.math.MathUtils.random;

public class Main extends ApplicationAdapter {
    public static SpriteBatch batch;
    Texture bg;
    ArrayList<Bullet> enemybullets= new ArrayList<Bullet>();
    ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    ArrayList<PowerUp> powerups = new ArrayList<PowerUp>();
    public static ArrayList<ArrayList<Enemy>> enemies =new ArrayList<ArrayList<Enemy>>();
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 1024;

    public static ShapeRenderer shapeRenderer;
    Player player;
    Random powerupDrop = new Random();

    public static HUD hud;

    int counter = 0;
    int pos = 0;
    int animation_speed = 2;

    @Override
    public void create() {
        graphics.setWindowedMode(WIDTH, HEIGHT);
        bg = new Texture("Assets/jpgs/space-1.jpg");
        batch = new SpriteBatch();
        player = new Player(0, 50);
        shapeRenderer = new ShapeRenderer();
        for(int i = 0; i<5; i++) {
            ArrayList<Enemy> temp = new ArrayList<Enemy>();
            for (int j = 0; j < 8; j++) {
                if (i == 4) temp.add(new Enemy("red", j, i));
                if (i == 3 || i == 2) temp.add(new Enemy("yellow", j, i));
                if (i == 1 || i == 0) temp.add(new Enemy("blue", j, i));
            }
            enemies.add(temp);
        }

        hud = new HUD();
    }


    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) player.goLeft();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) player.goRight();
        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) {
            player.usePowerup();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && !player.isShooting() && bullets.size() == 0) {
            bullets.add(player.shootBullet());
        }
        ArrayList<Enemy> canShoot=new ArrayList<Enemy>(); //start with the bottom row
        for(int n=0;n<enemies.get(4).size()-1;n++){
            canShoot.add(enemies.get(4).get(n));
        }
        for (int i = enemies.size()-1; i >0; i--) { //if any enemy is dead in the bottom row it will get replaced by the enemy on top of it
            for (int n = 0; n < enemies.get(i).size()-1; n++) {
                if(canShoot.get(n).isDead()){
                    canShoot.set(n,enemies.get(i).get(n));
                }
            }
        }
        ArrayList<Integer>enemyShooterNumber= new ArrayList<Integer>();
        Random rand=new Random();
        int pew = rand.nextInt(canShoot.size());
        if(!canShoot.get(pew).isShooting&&enemybullets.size()<6){
            enemybullets.add(canShoot.get(pew).shootBullet());
            enemyShooterNumber.add(pew);
        }



//        System.out.println(System.currentTimeMillis());

        batch.begin();
        batch.draw(bg, 0, 0);


        player.update(batch);

//        int isDrop = powerupDrop.nextInt(1000);
        int isDrop = powerupDrop.nextInt(10);
        if (isDrop < 2 && powerups.size() == 0) powerups.add(new PowerUp());
        for (int i = 0; i < powerups.size(); i++) {
            powerups.get(i).update(batch);
            if (powerups.get(i).getRect().y + powerups.get(i).getRect().height < 0) {
                powerups.remove(i);
            } else if (player.isCollidingWith(powerups.get(i))) {
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
        for (int i = 0; i < enemybullets.size(); i++) {
            enemybullets.get(i).update(batch);
            if (enemybullets.get(i).getY() > HEIGHT) {
                enemybullets.remove(i);
                if (!canShoot.isEmpty()&&!enemyShooterNumber.isEmpty()) {
                    canShoot.get(enemyShooterNumber.get(i)).isShooting = false;
                    enemyShooterNumber.remove(i);
                }
            }
        }
        for (int f = 0; f < enemybullets.size(); f++){
            if(player.isCollidingWith(enemybullets.get(f))){
                System.out.println("man down");
                enemybullets.remove(f);
                player.setShooting(false);
                canShoot.get(enemyShooterNumber.get(f)).isShooting=false;
            }
        }

        for (int i = 0; i < enemies.size(); i++){
            for (int n = 0; n < enemies.get(i).size(); n++){
                enemies.get(i).get(n).update(batch);
                if ((enemies.get(i).get(n).getRect().x + enemies.get(i).get(n).getRect().width > Main.WIDTH || enemies.get(i).get(n).getRect().x < 0) && !enemies.get(i).get(n).isDead()) inverseShipDirection();
                for (int f = 0; f < bullets.size(); f++){
                    if(enemies.get(i).get(n).isCollidingWith(bullets.get(f))){
                        System.out.println("man down");
                        bullets.remove(f);
                        player.setShooting(false);
                        enemies.get(i).get(n).setDead(true);
                    }
                }
            }
        }

//        enemy.get(0).get(0).moveSideway(enemy);
//        enemy.get(0).get(0).update(batch);
        hud.update(batch);

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

    public void inverseShipDirection(){
        for (int i = 0; i < enemies.size(); i++){
            for (int n = 0; n < enemies.get(i).size(); n++){
//                enemies.get(i).get(n).setX(Main.HEIGHT - enemies.get(i).get(n).getRect().width);
                enemies.get(i).get(n).inverseSpeed();
            }
        }
    }
}
