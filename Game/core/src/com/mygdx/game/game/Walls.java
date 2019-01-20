package com.mygdx.game.game;


import com.badlogic.gdx.maps.MapObject;

public class Walls {
    private float x, y, width, height;
    private float ID;

    public Walls(MapObject wall) {
        width = wall.getProperties().get("width", Float.class);
        height = wall.getProperties().get("height", Float.class);
        x = wall.getProperties().get("x", Float.class);
        y = wall.getProperties().get("y", Float.class);
        ID = wall.getProperties().get("id", Integer.class);
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

    public float getID() {
        return ID;
    }

    public boolean isCollideWith(Player player){
        // getting the player variables for ease of access
        int playerWidth = player.getWidth();
        int playerHeight = player.getHeight();
        int playerX = player.getX();
        int playerY = player.getY();

        // casting the float variables to int for better cohesion
        int thisWidth = (int) this.getWidth();
        int thisHeight = (int) this.getHeight();
        int thisX = (int) this.getX();
        int thisY = (int) this.getY();

        

        return false;
    }

    public void update() {
        this.x -= Main.xShift;
        this.y -= Main.yShift;
    }
}
