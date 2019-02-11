package com.mygdx.game.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FillViewport;

import java.util.ArrayList;
import java.util.Random;

import static com.badlogic.gdx.Gdx.graphics;

public class Main extends ApplicationAdapter {
    public boolean end = false; //indicating the end of introduction
    Music intro_music; //main theme
    public static SpriteBatch batch;
    boolean starting1 = false; //boolean to switch to 2nd phase of intro
    boolean starting2 = false; //boolean to switch to 3rd phase of intro
    Texture background;
    BitmapFont font, font2, font3, font4; //fonts used
    Texture intro_player;
    Texture bullet;
    Texture powerup1, powerup2, powerup3; //pictures of pu
    Texture enemy1, enemy2, enemy3;
    Texture ship;
    Texture intro_explosion;
    Texture bulletside;
    Music start;//sound effects in "Music" to use the setOncompletionlistner which is not available for sounds
    Music start2;
    Music start0;
    Music music;
    Texture bg;
    ArrayList<Bullet> enemybullets = new ArrayList<Bullet>();
    ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    ArrayList<PowerUp> powerups = new ArrayList<PowerUp>();
    public static ArrayList<ArrayList<Enemy>> enemies = new ArrayList<ArrayList<Enemy>>();
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 1024;

    public static Player player;
    public static HUD hud;

    public static Texture[] explosion = new Texture[73];

    public boolean playerAlive = true;
    public boolean gameStarted = false;
    private int aliveEnemies;
    private BitmapFont diedFont;
    @Override
    public void create() {
        start0 = Gdx.audio.newMusic(Gdx.files.internal("Assets/Sound/start0.mp3")); //first sound in intro
        start = Gdx.audio.newMusic(Gdx.files.internal("Assets/Sound/start.mp3")); //2nd
        start2 = Gdx.audio.newMusic(Gdx.files.internal("Assets/Sound/start2.mp3")); //3rd
        intro_player = new Texture("Assets/0.png");
        bullet = new Texture("Assets/1.png");
        bulletside = new Texture("Assets/1rotated.png");
        ship = new Texture("Assets/2.png");
        enemy1 = new Texture("Assets/Enemies/0.png");
        enemy2 = new Texture("Assets/Enemies/1.png");
        enemy3 = new Texture("Assets/Enemies/2.png");
        powerup1 = new Texture("Assets/mirror.png");
        powerup2 = new Texture("Assets/spiritBomb.png");
        powerup3 = new Texture("Assets/invincible.png");
        intro_explosion = new Texture("Assets/Explosion/29.png");
        font = new BitmapFont(Gdx.files.internal("Assets/one/impact.fnt")); //title font
        font2 = new BitmapFont(Gdx.files.internal("Assets/one/adventures.fnt")); //description font
        font3 = new BitmapFont(Gdx.files.internal("Assets/one/text.fnt")); //description but smaller
        font4 = new BitmapFont(Gdx.files.internal("Assets/one/sub.fnt")); //for instructions
        diedFont = new BitmapFont(Gdx.files.internal("ASSETS/one/died.fnt"));
        font3.getData();
        font.getData();
        font2.getData();
        background = new Texture("Assets/start.png");
        intro_music = Gdx.audio.newMusic(Gdx.files.internal("Assets/1.mp3"));
        intro_music.play();
        //next up are assets used in main game
        music = Gdx.audio.newMusic(Gdx.files.internal("Assets/Sound/main.mp3"));
        graphics.setWindowedMode(WIDTH, HEIGHT);
        bg = new Texture("Assets/jpgs/space-1.jpg");
        batch = new SpriteBatch();
        player = new Player(0, 50);
        for (int i = 0; i < 5; i++) {
            ArrayList<Enemy> temp = new ArrayList<Enemy>();
            for (int j = 0; j < 8; j++) {
                if (i == 0) temp.add(new Enemy("red", j, i));
                if (i == 1 || i == 2) temp.add(new Enemy("yellow", j, i));
                if (i == 3 || i == 4) temp.add(new Enemy("blue", j, i));
            }
            enemies.add(temp);
        }

        hud = new HUD();

        for (int i = 0; i < 73; i++) {
            explosion[i] = new Texture("Assets/EXPLOSION/" + i + ".png");
        }
    }


    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (gameStarted){
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) player.goLeft();
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) player.goRight();
            if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) player.usePowerup();
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && !player.isShooting() && bullets.size() == 0) bullets.add(player.shootBullet());
        }

        batch.begin();
//        OrthographicCamera camera = new OrthographicCamera();
//        camera.position.set(512, 512, 0);
//        FillViewport viewport = new FillViewport(1024, 1024, camera);
//        viewport.update(1600,1600,true);
        intro();
        aliveEnemies = numOfAliveEnemies();
        if (playerAlive && gameStarted && aliveEnemies > 0) {

            music.play();
            music.setOnCompletionListener(new Music.OnCompletionListener() {//once the song is over repeat it!
                @Override
                public void onCompletion(Music music) {
                    Gdx.app.log("Music:", "Beginning ended");
                    music.play();
                }
            });
            batch.draw(bg, 0, 0);
            player.update(batch);
            dropPowerup();
            bulletsUpdate();
            isEnemyShot();
            enemiesShoot();
            isPlayerShot();
            isPlayerDead();
            hud.update(batch);
        } else {
            if (aliveEnemies > 0 && !playerAlive) {
                youDied();
            } else if (playerAlive && aliveEnemies == 0){
                System.out.println("YOU WON BUDDY");
            }
        }
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    public int numOfAliveEnemies() {
        int counter = 0;
        for (int i = 0; i < enemies.size(); i++) {
            for (int n = 0; n < enemies.get(i).size(); n++) {
                if (!enemies.get(i).get(n).isDead()) counter++;
            }
        }
        return counter;
    }

    public void inverseShipDirection() {
        for (int i = 0; i < enemies.size(); i++) {
            for (int n = 0; n < enemies.get(i).size(); n++) {
                enemies.get(i).get(n).setY(enemies.get(i).get(n).getRect().y - 25);
                enemies.get(i).get(n).inverseSpeed();
            }
        }
    }

    public void youDied(){
        diedFont.draw(batch, "YOU DIED", WIDTH / 2 - 150, HEIGHT / 2 + 25);
    }


    public void dropPowerup() {
        Random powerupDrop = new Random();
//        int isDrop = powerupDrop.nextInt(1000);
        int isDrop = powerupDrop.nextInt(100);
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
    }

    public void isEnemyShot() {
        for (int i = 0; i < enemies.size(); i++) {
            for (int n = 0; n < enemies.get(i).size(); n++) {
                enemies.get(i).get(n).update(batch);
                if ((enemies.get(i).get(n).getRect().x + enemies.get(i).get(n).getRect().width > Main.WIDTH || enemies.get(i).get(n).getRect().x < 0) && !enemies.get(i).get(n).isDead())
                    inverseShipDirection();
                for (int f = 0; f < bullets.size(); f++) {
                    if (enemies.get(i).get(n).isCollidingWith(bullets.get(f)) && bullets.get(f).getType() == 0) {
                        bullets.remove(f);
                        player.setShooting(false);
                        player.addPoints(enemies.get(i).get(n).getPointValue());
                        enemies.get(i).get(n).setDead(true, player);
                    }
                }
            }
        }
    }

    public void isPlayerShot() {
        for (int i = 0; i < enemybullets.size(); i++) {
            if (player.isCollidingWith(enemybullets.get(i)) && enemybullets.get(i).getType() == 1) {
                enemybullets.remove(i);
                player.takeAwayLife();
                System.out.println(player.getLives());
            }
        }
    }

    public void bulletsUpdate() {
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).update(batch);
            if (bullets.get(i).getY() > HEIGHT) {
                bullets.remove(i);
                player.setShooting(false);
            }
        }
    }

    public ArrayList<Bullet> chooseShootingEnemies() {
        ArrayList<Bullet> enemyBullets = new ArrayList<Bullet>();
        int shootChance;
        Random enemyShootChance = new Random();
        for (int i = 0; i < enemies.size(); i++) {
            for (int n = 0; n < enemies.get(i).size(); n++) {
                shootChance = enemyShootChance.nextInt(100);
                if (shootChance < 2 && !enemies.get(i).get(n).isDead())
                    enemyBullets.add(enemies.get(i).get(n).shootBullet());
            }
        }
        return enemyBullets;
    }

    public void enemiesShoot() {
        if (enemybullets.size() == 0) enemybullets = chooseShootingEnemies();
        for (int i = 0; i < enemybullets.size(); i++) {
            enemybullets.get(i).update(batch);
            if (enemybullets.get(i).getRect().y + enemybullets.get(i).getRect().height < 0) enemybullets.remove(i);
        }
    }

    public void isPlayerDead() {
        if (player.getLives() <= 0) {
            playerAlive = false;
        }
    }

    public void intro() {
        if (end == false) { //intro starts here
            graphics.setWindowedMode(WIDTH, HEIGHT);
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && starting1 == false) {//starting to play

                starting1 = true; //starting 2nd phase
                start0.play();
                intro_music.stop();//stopping overall theme
                start0.setOnCompletionListener(new Music.OnCompletionListener() { //waiting for first sound to finish to start 2nd
                    @Override
                    public void onCompletion(Music music) {//waitig for 2nd sound to finish to play third
                        start.play();
                    }
                });
                start.setOnCompletionListener(new Music.OnCompletionListener() { //2nd to play third sound and start 3rd phase
                    @Override
                    public void onCompletion(Music music) {
                        start2.play();
                        starting2 = true;
                    }
                });
            }
            if ((Gdx.input.isKeyPressed(Input.Keys.X) && starting2 == true) || Gdx.input.isKeyPressed(Input.Keys.P)) {//to skip intro quickly/advance into game from third phase
                end = true; //boolean that intro has ended
            }
            batch.draw(background, Main.WIDTH - background.getWidth(), Main.HEIGHT - background.getHeight());
//            batch.draw(background, 0, 0);
            if (starting1 == false) { //drawing lots of texts and diagrams
                font.draw(batch, " SPACE LEGEND", 50, 300);
                font2.draw(batch, "Press Space to initiate your mission", 105, 120);
                batch.draw(enemy1, 265, 400);
                batch.draw(bullet, 285, 365);
            }
            if (starting2 == false) { //second phase including explosion
                batch.draw(intro_player, 275, 0);
                batch.draw(ship, 60, 150);
                batch.draw(intro_explosion, 500, 150);
                batch.draw(bulletside, 300, 180);
                if (starting1 == true) {
                    batch.draw(intro_explosion, 250, 360);
                    font2.draw(batch, "Your Mission Initiates in T-7", 150, 320);
                }
            } else if (starting2 = true) { //3rd phase screen including ships powerups and controls
                font3.draw(batch, "Commander Shepard was abroad the normandy when he", 50, 460);
                font3.draw(batch, "encountered a fleet of hostile ships with the intention of", 50, 420);
                font3.draw(batch, "ruining balance in the universe.The Galactic council will ", 50, 380);
                font3.draw(batch, "send reinforcements as power-ups, use them wisely!", 50, 340);
                font4.draw(batch, "ENEMIES", 50, 300);
                font4.draw(batch, "POWER-UPS", 450, 300);
                font4.draw(batch, "40 pts", 0, 250);
                font4.draw(batch, "20 pts", 0, 150);
                font4.draw(batch, "10 pts", 0, 50);
                font4.draw(batch, "Controls:", 260, 300);
                font4.draw(batch, "Start: X", 260, 250);
                font4.draw(batch, "Shoot: Space", 235, 150);
                font4.draw(batch, "Power-up: Shift", 230, 50);
                batch.draw(enemy1, 130, 210);
                batch.draw(enemy2, 140, 110);
                batch.draw(enemy3, 130, 10);
                batch.draw(powerup1, 455, 210);
                batch.draw(powerup2, 455, 110);
                batch.draw(powerup3, 455, 10);
                font4.draw(batch, "Mirror", 550, 250);
                font4.draw(batch, "Bomb", 550, 150);
                font4.draw(batch, "Invincible", 520, 50);
            }
        } else {
            gameStarted = true;
        }
    }
}
