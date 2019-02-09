package com.mygdx.game.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class HUD {
    private Texture powerups_hud;
    private ArrayList<Texture> powerup_sprites = new ArrayList<Texture>();

    public HUD() {
        powerups_hud = new Texture("Assets/powerupsHUD.png");
    }

    public void render(SpriteBatch batch) {
        batch.draw(powerups_hud, Main.WIDTH - powerups_hud.getWidth(), Main.HEIGHT - powerups_hud.getHeight());
        for (int i = 0; i < powerup_sprites.size(); i++)
            batch.draw(powerup_sprites.get(i), 730 + 58 * i, Main.HEIGHT - 105);
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
