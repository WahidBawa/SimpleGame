package com.mygdx.game.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.mygdx.game.game.Main;
import com.mygdx.game.game.Walls;

public class TiledGameMap extends GameMap {

    public static TiledMap tiledMap;
    public static TiledMap tiledMap2;
    OrthogonalTiledMapRenderer tiledMapRenderer;

    public TiledGameMap() {
        tiledMap = new TmxMapLoader().load("Assets/MAPS/MEGAMAN/level1_real.tmx");
//        tiledMap2 = new TmxMapLoader().load("Assets/MAPS/MEGAMAN/lvl1.tmx");
        this.loadObjects();

        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
    }

    private void loadObjects(){
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

    @Override
    public void render(OrthographicCamera camera) {
        tiledMapRenderer.setMap(tiledMap);
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
        TiledMapTileLayer.Cell cell = ((TiledMapTileLayer) tiledMap.getLayers().get(layer)).getCell(col, row);
        if (cell != null) {
            TiledMapTile tile = cell.getTile();
            if (tile != null) {
                int id = tile.getId();
                return TileType.getTileTypeById(id);
            }
        }
        return null;
    }

    @Override
    public int getWidth() {
        return ((TiledMapTileLayer) tiledMap.getLayers().get(0)).getWidth();
    }

    @Override
    public int getHeight() {
        return ((TiledMapTileLayer) tiledMap.getLayers().get(0)).getHeight();
    }

    @Override
    public int getLayers() {
        return 0;
    }
}
