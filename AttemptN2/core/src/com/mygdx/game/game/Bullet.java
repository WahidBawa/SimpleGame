/*
    Author: Wahid Bawa, Nizar Alrifai
    Class Name: Bullet
    Purpose: creates bullets for the player and enemy to kill each other with

        */
package com.mygdx.game.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;

public class Bullet {
    public int type; // stores whether the enemy shot it or the player shot the bullet
    private static final int PLAYER = 0;
    private static final int ENEMY = 1;
    private float x, y, width;
    private int speed = 12;
    Texture bullet_sprite;
    Sprite bullet;
    Rectangle rect;

    public Bullet(float x, float y, float width, int type) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.type = type;
        if (type == PLAYER) { // will load a texture that points the bullets towards the enemies
            bullet_sprite = new Texture("Assets/1.png");
        } else if (type == ENEMY) { // will load a texture that points the bullets towards the player
            bullet_sprite = new Texture("Assets/1flipped.png");
        }
        speed = (type == 0 ? 12 : -12); // will determine the direction depending on the type
        bullet = new Sprite(bullet_sprite); // creates a bullet sprite
        // rect created
        rect = new Rectangle((int) bullet.getX(), (int) bullet.getY(), (int) bullet.getWidth(), (int) bullet.getHeight());
    }

    public void render(SpriteBatch batch) { // renders the bullet
        bullet.setX(x + width / 2 - bullet.getWidth() / 2); // sets the x of the bullet
        // sets a new rect
        rect = new Rectangle((int) bullet.getX(), (int) bullet.getY(), (int) bullet.getWidth(), (int) bullet.getHeight());
        bullet.draw(batch); // draws the bullet on the screen
    }

    public void update(SpriteBatch batch) {// moves the bullet in the appropriate direction
        y += speed;
        bullet.setY(y); // sets the y for the sprite
        this.render(batch); // calls the render method
    }

    public float getY() {
        return y;
    } // returns the y position

    public Rectangle getRect() {
        return rect;
    } // returns the Rectangle

    public int getType() {
        return type;
    } // returns the type

    public void reflect() { // will reflect the bullet towards the enemies
        speed *= -1; // the direction is reversed
        bullet_sprite = new Texture("Assets/1.png"); // changes the bullet sprite
        bullet = new Sprite(bullet_sprite); // creates a new sprite based on the new sprite image
        type = 0; // type is changed to 0
        Main.enemybullets.remove(Main.enemybullets.indexOf(this)); // removes from the enemy bullets
        Main.bullets.add(this); // added to the players bullets
    }


}
