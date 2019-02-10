package com.mygdx.game.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class HUD {
    private Texture powerups_hud;
    private Texture points_hud;
    private Texture heart;
    private BitmapFont font = new BitmapFont(Gdx.files.internal("Assets/one/impact.fnt"), false);
    private ArrayList<Texture> powerup_sprites = new ArrayList<Texture>();

    public HUD() {
        powerups_hud = new Texture("Assets/powerupsHUD.png");
        points_hud = new Texture("Assets/points.png");
        heart = new Texture("Assets/heart.png");
    }

    public void render(SpriteBatch batch) {
        batch.draw(powerups_hud, Main.WIDTH - powerups_hud.getWidth(), Main.HEIGHT - powerups_hud.getHeight());
        batch.draw(points_hud, 0, Main.HEIGHT - points_hud.getHeight());
        font.draw(batch, "" + Main.player.getPoints(), 235, Main.HEIGHT - 25);
        for (int i = 0; i < powerup_sprites.size(); i++)
            batch.draw(powerup_sprites.get(i), 730 + 58 * i, Main.HEIGHT - 105);
        for (int i = 0; i < Main.player.getLives(); i++) {
            batch.draw(heart, 375 + i * (heart.getWidth() / 3 + 10), Main.HEIGHT - heart.getHeight() / 3 - 25, heart.getWidth() / 3, heart.getHeight() / 3);
        }
//        batch.draw(heart, 0, 0);
    }

    public void update(SpriteBatch batch) {
        this.render(batch);

    }

    public void addPowerup(Texture img) {
        powerup_sprites.add(img);
    }

    public void removePowerup() {
        powerup_sprites.remove(0);
    }
}
