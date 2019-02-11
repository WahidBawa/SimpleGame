/*
    Author: Wahid Bawa, Nizar Alrifai
    Class Name: HUD
    Purpose: this will be in charge of all the function necessary for a heads up display. This class will display things
             such a the health, points, and which powerups they are able to use.

        */
package com.mygdx.game.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class HUD {
    private Texture powerups_hud; // the background for the powerups HUD
    private Texture points_hud; // the COOL text for the points
    private Texture heart; // the texture for the heart to display the players life
    private BitmapFont font = new BitmapFont(Gdx.files.internal("Assets/one/impact.fnt"), false); // will load in a font for the points
    private ArrayList<Texture> powerup_sprites = new ArrayList<Texture>(); //will store the powerup texture

    public HUD() {// will load in various textures
        powerups_hud = new Texture("Assets/powerupsHUD.png");
        points_hud = new Texture("Assets/points.png");
        heart = new Texture("Assets/heart.png");
    }

    public void render(SpriteBatch batch) { // this will render all the information that is to be displayed
        batch.draw(powerups_hud, Main.WIDTH - powerups_hud.getWidth(), Main.HEIGHT - powerups_hud.getHeight()); // draws the powerup HUD background
        batch.draw(points_hud, 0, Main.HEIGHT - points_hud.getHeight()); // draws the COOL text for points
        font.draw(batch, "" + Main.player.getPoints(), 235, Main.HEIGHT - 25); // draws the point value on screen
        if (powerup_sprites.size() > 0) batch.draw(powerup_sprites.get(0), 790, Main.HEIGHT - 105); // draws the powerup sprites on screen
        for (int i = 0; i < Main.player.getLives(); i++) { // draws the players life represented by hearts at the top of the screen
            batch.draw(heart, 375 + i * (heart.getWidth() + 10), Main.HEIGHT - heart.getHeight() - 25);
        }
    }

    public void update(SpriteBatch batch) { // calls the render method only in this case
        this.render(batch);

    }

    public void addPowerup(Texture img) { // this will add a powerup so that it can be displayed
        powerup_sprites.add(img);
    }

    public void removePowerup() { // removes a powerup once it has been used
        powerup_sprites.remove(0);
    }
}
