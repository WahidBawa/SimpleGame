package com.mygdx.game.world;

import java.util.HashMap;

public enum TileType {

    TOP_LEFT_CORNER(30, true, "Top-Left Corner");
//    DIRT(2, true, "Dirt"),
//    SKY(3, false, "Sky"),
//    LAVA(4, true, "Lava"),
//    CLOUD(5, true, "Cloud"),
//    STONE(6, true, "Stone");

    private int id;
    private String name;
    private boolean collideable;
    private float damage;
    public final static int TILE_SIZE = 16;

    TileType (int id, boolean collideable, String name){
        this(id, collideable, name, 0);
    }

    TileType (int id, boolean collideable, String name, float damage){
        this.id = id;
        this.collideable = collideable;
        this.name = name;
        this.damage = damage;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isCollideable() {
        return collideable;
    }

    public float getDamage() {
        return damage;
    }

    private static HashMap<Integer, TileType> tileMap;

    static{
        tileMap = new HashMap<Integer, TileType>();
        for (TileType i : TileType.values()){
            tileMap.put(i.getId(), i);
        }
    }

    public static TileType getTileTypeById(int id){
        return tileMap.get(id);
    }

}
