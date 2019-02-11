/*
    Author: Wahid Bawa, Nizar Alrifai
    Class Name: Player
    Purpose: this will carry out all the functions of a player object such as movement, shooting bullets, tracking points
             , tracking health, using powerups and much more.

        */
package com.mygdx.game.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;
import java.util.ArrayList;

public class Player {
    private float x, y; // stores the x and the y of the player
    private Texture player_sprite;
    private Sprite player;
    private boolean shooting = false;
    // powerup types
    private final static int SPIRITBOMB = 0;
    private final static int INVINCIBLE = 1;
    private final static int MIRROR = 2;
    private boolean using_spiritbomb = false; // will be used to determine if spiritbomb powerup is being used
    private SpiritBomb spiritbomb; // creates a SpiritBomb object
    private ArrayList<Integer> powerupID = new ArrayList<Integer>(); // will store the id of the powerup
    private int points = 0; // will store the points
    private int lives = 3; // stores the amount of lives left
    private boolean invincible = false; // used to determine invincibility
    private Sprite barrier = new Sprite(new Texture("Assets/barriers.png")); // sprite of the barrier
    boolean musicPlaying = false; // checks if music is playing
    private boolean mirror_activated = false; // checks if the mirror powerup is active
    private Mirror[] mirrors = new Mirror[3]; // stores three mirror if the power up is used
    Rectangle rect; // stores a rectangle of the player which is used for collision and more

    public Player(float x, float y) { // constructor takes in x and y
        player_sprite = new Texture("Assets/0.png"); // loads in player sprite image
        player = new Sprite(player_sprite); // creates a sprite out of the image
        this.x = x; // sets the x variable
        this.y = y; // sets the y variable
        player.setX(x); // sets the player's x
        player.setY(y); // sets the player's y
        rect = new Rectangle((int) player.getX(), (int) player.getY(), (int) player.getWidth(), (int) player.getHeight()); // creates a rect based on the sprite's dimensions
    }

    //updates character's position
    private void render(SpriteBatch batch) { // renders in the player and the invincibility circle if the ability is active
        if (invincible) { // if the player is invincible
            // ther barrier sprite's x and y is set corresponding to the player's x and y
            barrier.setX(player.getX() - 35);
            barrier.setY(player.getY() - 40);
            barrier.rotate(1); // will rotate the barrier
            barrier.draw(batch); // draws the barrier onto the screen
        }
        player.draw(batch); // draws the player on to the screen
    }

    public void update(SpriteBatch batch) { // updates the position of the player and updates any abilities being used
        // sets the x and the y of the player
        player.setX(x);
        player.setY(y);
        // creates a new rectangle
        rect = new Rectangle((int) player.getX(), (int) player.getY(), (int) player.getWidth(), (int) player.getHeight());
        if (using_spiritbomb) { // if the spiritbomb is being used
            spiritbomb.update(batch); // updates the spiritbomb object
            if (spiritbomb.getRect().y > Main.HEIGHT) { // will stop using spirit bomb after it goes off-screen
                using_spiritbomb = false; // set to false
            }
            for (int i = 0; i < Main.enemies.size(); i++) { // performs collision with the enemies
                for (int n = 0; n < Main.enemies.get(i).size(); n++) {
                    if (Main.enemies.get(i).get(n).isCollidingWith(spiritbomb)) {
                        this.addPoints(Main.enemies.get(i).get(n).getPointValue()); // adds points to the total score
                        Main.enemies.get(i).get(n).setDead(true, this); // sets the enemies that are hit to dead
                    }
                }
            }
        }

        if (mirror_activated){ // if the mirror is being used
            for (int i = 0; i < mirrors.length; i++){
                mirrors[i].update(batch, i); // updates the mirror
                for (int n = 0; n < Main.enemybullets.size(); n++){ // checks if the enemy bullets hit the mirror
                    if (mirrors[i].isCollidesWith(Main.enemybullets.get(n))){
                        Main.enemybullets.get(n).reflect(); // reflects the bullet back towards the enemy
                        mirrors[i].hit(); // the mirror is hit and the amount of bullets it can reflect is reduced
                    }
                }
            }

        }
        this.render(batch); // calls the render method
    }

    public void usePowerup() { // uses a powerup when the shift button is pressed
        if (powerupID.size() > 0) { // if there is a powerup that is present
            if (powerupID.get(0) == SPIRITBOMB) { // if the powerup is a spiritbomb
                using_spiritbomb = true; // sets using_spiritbomb to true
                spiritbomb = new SpiritBomb(player.getX(), player.getY(), player.getWidth()); // initializes the spiritbomb object
            } else if (powerupID.get(0) == INVINCIBLE) { // if the powerup is invincible
                invincible = true; // invincible  is set to true
            }else if (powerupID.get(0) == MIRROR){ // if the powerup is a mirror
                for (int i = 0; i < mirrors.length; i++){ // creates a new Mirror object int the mirrors array
                    mirrors[i] = new Mirror();
                }
                mirror_activated = true; // mirror activated is set to true
            }
            powerupID.remove(0); // removes the powerup id as it is not used
            Main.hud.removePowerup(); // removes a powerup from the HUD
        }
    }

    public void getPowerup(PowerUp powerup) { // called when the player collides with a powerup object
        int type = powerup.getType(); // gets the type of powerup
        if (powerupID.size() == 0) { // will only receive it if there are non presently used
            if (type == INVINCIBLE) { // if the type is invincible
                Main.hud.addPowerup(new Texture("Assets/invincible.png")); // adds to the HUD
                powerupID.add(INVINCIBLE); // adds the ID to the arraylist
            } else if (type == SPIRITBOMB) {
                Main.hud.addPowerup(new Texture("Assets/spiritbomb.png"));
                powerupID.add(SPIRITBOMB);
            } else if (type == MIRROR) {
                Main.hud.addPowerup(new Texture("Assets/Mirror.png"));
                powerupID.add(MIRROR);
            }
        }
    }

    public void goLeft() { // goes left
        if (player.getX() > 0) x -= 8;
    }

    public void goRight() { // goes right
        if (player.getX() + player.getWidth() < Main.WIDTH) x += 8;
    }

    public Bullet shootBullet() { // sets shooting to true and returns a new bullet object
        shooting = true;
        return new Bullet(player.getX(), player.getY(), player.getWidth(), 0);
    }

    public boolean isShooting() { // returns the shooting boolean
        return shooting;
    }

    public void setShooting(boolean shooting) { // sets the shooting boolean
        this.shooting = shooting;
    }

    public boolean isCollidingWith(PowerUp powerup) { // checks if the player is colliding with a powerup object and return a boolean
        return powerup.getRect().intersects(this.getRect());
    }

    public boolean isCollidingWith(Bullet bullet) { // checks if the player is colliding with a bullet and returns a boolean
        return bullet.getRect().intersects(this.getRect()) && bullet.getType() == 1;
    }

    public Rectangle getRect() { // returns the Rectangle
        return rect;
    }

    public void addPoints(int points) { // adds points when given a point value
        this.points += points;
    }

    public int getPoints() { // returns the amount of points
        return points;
    }

    public int getLives() { // returns the amount of lives
        return lives;
    }

    public void takeAwayLife() { // takes away a life
        if (!invincible) lives -= 1;
        else invincible = false; // if the player was invincible then the ability will end
    }

}

