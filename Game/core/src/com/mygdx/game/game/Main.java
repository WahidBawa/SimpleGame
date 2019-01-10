package com.mygdx.game.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.world.GameMap;
import com.mygdx.game.world.TiledGameMap;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	OrthographicCamera cam1;

	GameMap gameMap;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("Assets/badlogic.jpg");
		cam1 = new OrthographicCamera();
		cam1.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam1.update();

		gameMap = new TiledGameMap();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// this is old code but it is a good example for keyboard input although this is keyboard input
//		if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)){
//			if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
//				cam1.translate(x-=speed, y);
//			}
//			if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
//				cam1.translate(x+=speed, y);
//			}
//			if (Gdx.input.isKeyPressed(Input.Keys.UP)){
//				cam1.translate(x, y+=speed);
//			}
//			if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
//				cam1.translate(x, y-=speed);
//			}
//		}else{
//			x = 0;
//			y = 0;
//		}
		cam1.update();

		gameMap.render(cam1);
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
