package com.mygdx.game.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.mygdx.game.game.Walls;

public class TiledGameMap{

    public static TiledMap tiledMap;
    public static TiledMap tiledMap2;
    OrthogonalTiledMapRenderer tiledMapRenderer;

    public TiledGameMap() {
        tiledMap = new TmxMapLoader().load("Assets/MAPS/MEGAMAN/level1_real.tmx");
//        tiledMap2 = new TmxMapLoader().load("Assets/MAPS/MEGAMAN/lvl1.tmx");
        this.loadObjects();

        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
    }

    private void loadObjects() {
        MapObjects objects;
        MapLayer walls = tiledMap.getLayers().get("Walls");
        objects = walls.getObjects();
        for (MapObject i : objects) {
            new Walls(i);
        }

//        MapLayer info = tiledMap.getLayers().get("INFO");
//        objects = info.getObjects();
//        int xOff = objects.get(0).getProperties().get("xOffset", Integer.class);
//        int yOff = objects.get(0).getProperties().get("yOffset", Integer.class);
//        Main.xShift = xOff;
//        Main.yShift = yOff;
    }

    public void render(OrthographicCamera camera) {
        tiledMapRenderer.setMap(tiledMap);
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
    }

    public void update(float delta) {

    }

    public void dispose() {
        tiledMap.dispose();
    }


    public int getWidth() {
        return ((TiledMapTileLayer) tiledMap.getLayers().get(0)).getWidth();
    }

    public int getHeight() {
        return ((TiledMapTileLayer) tiledMap.getLayers().get(0)).getHeight();
    }

    public int getLayers() {
        return 0;
    }
}
