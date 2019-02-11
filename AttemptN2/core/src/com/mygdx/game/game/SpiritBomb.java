/*
    Author: Wahid Bawa, Nizar Alrifai
    Class Name: SpiritBomb
    Purpose: creates a SpiritBomb attack which would attack enemies.

        */
package com.mygdx.game.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;

public class SpiritBomb {
    private float x, y, width;
    Texture spiritbomb_sprite;
    Sprite spiritbomb;

    Rectangle rect;

    public SpiritBomb(float x, float y, float width) {
        this.x = x;
        this.y = y;
        this.width = width;
        spiritbomb_sprite = new Texture("Assets/spiritbomb_attack.png"); //loads in the spiritbomb sprite
        spiritbomb = new Sprite(spiritbomb_sprite); // creates a sprite based on the sprite image
        //creates a rectangle object
        rect = new Rectangle((int) spiritbomb.getX(), (int) spiritbomb.getY(), (int) spiritbomb.getWidth(), (int) spiritbomb.getHeight());
    }

    public void render(SpriteBatch batch) { // renders the spiritbomb
        spiritbomb.setX(x + width / 2 - spiritbomb.getWidth() / 2); // sets the x
        // creates a new rect
        rect = new Rectangle((int) spiritbomb.getX(), (int) spiritbomb.getY(), (int) spiritbomb.getWidth(), (int) spiritbomb.getHeight());
        spiritbomb.draw(batch); // draws the spiritbomb on to the screen
    }

    public void update(SpriteBatch batch) {
        y += 12; // accelerates the spiritbomb towards the enemies
        spiritbomb.setY(y); // sets the y
        this.render(batch); // calls the render method
    }

    public Rectangle getRect() {
        return rect;
    } // returns the Rectangle
}
