/*Simple Game
Enemy class
Nizar Alrifai+Wahid Bawa
Class to deal with enemy objects, their animations, collisions, types and actions.
*/
package com.mygdx.game.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.awt.*;
import java.util.Random;


public class Enemy {
    Music die; //music played when they die
    private Sprite sprite;
    private Texture blueship; //differnt types of enemies
    private Texture yellowship;
    private Texture redship;
    private boolean dead = false;
    private Rectangle rect;
    private int speed = 1;
    public boolean isShooting=false;
    private int pointValue; //for score

    int counter = 0;
    int pos = 0;
    int animation_speed = 1; //for movement

    Sprite explosion; //everybody goes booom when they die

    private int deathX, deathY; //location of death

    private boolean isExplosionDone = false; //checking when explosion is over
    //initialization code

    public Enemy(String type, int x, int y) { //creating object, we need the row and coloumn of the enemy and it's type
        //type is assigned according to row
        blueship = new Texture("Assets/Enemies/2.png");
        redship = new Texture("Assets/Enemies/0.png");
        yellowship = new Texture("Assets/Enemies/1.png");

        if (type.equals("yellow")) {
            sprite = new Sprite(yellowship);
            pointValue = 20;
        } else if (type.equals("red")) {
            sprite = new Sprite(redship);
            pointValue = 40;
        } else if (type.equals("blue")) {
            sprite = new Sprite(blueship);
            pointValue = 10;
        }
        sprite.setX(50 + x * 100);
        sprite.setY(Main.HEIGHT - 200 - (75 * y));
        rect = new Rectangle((int) sprite.getX(), (int) sprite.getY(), (int) sprite.getWidth(), (int) sprite.getHeight());
    }
    public Bullet shootBullet() { //making the enemy shoot
        isShooting=true;
        return new Bullet(sprite.getX(), sprite.getY(), sprite.getWidth(),1);
    }


    public void render(SpriteBatch batch) {//rendering the updated locations of the enemies and making explosiosn where they die
        rect = new Rectangle((int) sprite.getX(), (int) sprite.getY(), (int) sprite.getWidth(), (int) sprite.getHeight());
        sprite.draw(batch);
        if (this.isDead() && !isExplosionDone){
            if (pos == 72) isExplosionDone = true;
            explosion = new Sprite(Main.explosion[pos]);
            explosion.setX(deathX);
            explosion.setY(deathY);
            explosion.draw(batch);
        }
    }

    public void update(SpriteBatch batch) { //updating locations of enemies that are not dead
        if (this.isDead()) {
            sprite.setAlpha(0);

            counter += 1;
            if (counter > animation_speed) {
                counter = 0;
                pos += 1;
                if (pos >= 73) {
                    pos = 0;
                }
            }
        }
        sprite.setX(sprite.getX() + speed);
        this.render(batch);
    }

    public boolean isCollidingWith(Bullet bullet) { //check if the enmy is hit with a bullet
        return bullet.getRect().intersects(this.getRect()) && !this.isDead();
    }

    public boolean isCollidingWith(SpiritBomb spiritBomb) { //if enemy is hit with a bomb
        return spiritBomb.getRect().intersects(this.getRect()) && !this.isDead();
    }

    public Rectangle getRect() {
        return rect;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead, final Player player) { //if it is dead play one of 4 audio files or none at all, and update that the ship is dead
        Random rand = new Random();
        int n = rand.nextInt(5);
        if(n==1){
            die=  Gdx.audio.newMusic(Gdx.files.internal("Assets/Sound/kill.mp3"));
        }
        else if (n==2){
            die= Gdx.audio.newMusic(Gdx.files.internal("Assets/Sound/kill2.mp3"));
        }
        else if(n==3){
            die= Gdx.audio.newMusic(Gdx.files.internal("Assets/Sound/kill3.mp3"));
        }
        else if(n==4){
            die= Gdx.audio.newMusic(Gdx.files.internal("Assets/Sound/kill4.mp3"));
        }
        this.dead = dead;
        deathX = this.getRect().x;
        deathY = this.getRect().y;
        //music will NOT play if a file was already playing
        //that is done with the help of the player class, as the enemies change and therefore the boolean can not be contained here
        if(n>0&&n<5&&player.musicPlaying==false) {
            die.play();
            player.musicPlaying=true;
            die.setOnCompletionListener(new Music.OnCompletionListener() {
                @Override
                public void onCompletion(Music music) { //wait till music ends to update the boolean
                    player.musicPlaying=false;
                    Gdx.app.log("Music:" , "Beginning ended; switching to loop");

                }
            });

        }
    }

    public void inverseSpeed(){
        speed *= -1;
    } //reversing direction

    public void setY(int y){
        sprite.setY(y);
    }

    public int getPointValue() {
        return pointValue;
    } //points for killing
}