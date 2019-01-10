package com.mygdx.game.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class TiledGameMap extends GameMap {

    TiledMap tiledMap;
    OrthogonalTiledMapRenderer tiledMapRenderer;

    public TiledGameMap(){
        tiledMap = new TmxMapLoader().load("Assets/MAPS/test.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
    }


    @Override
    public void render(OrthographicCamera camera) {
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void dispose() {
        tiledMap.dispose();
    }

    @Override
    public TileType getTileTypeByCoordinate(int layer, int col, int row) {
        return null;
    }

    @Override
    public int getWidth() {
        return 640;
    }

    @Override
    public int getHeight() {
        return 480;
    }

    @Override
    public int getLayers() {
        return 0;
    }
}
