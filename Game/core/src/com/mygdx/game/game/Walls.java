package com.mygdx.game.game;


import com.badlogic.gdx.maps.MapObject;

public class Walls {
    float x, y, width, height;
    public Walls(MapObject wall){
        width = wall.getProperties().get("width", Float.class);
        height = wall.getProperties().get("height", Float.class);
        x = wall.getProperties().get("x", Float.class);
        y = wall.getProperties().get("y", Float.class);
        Main.walls.add(this);

    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
