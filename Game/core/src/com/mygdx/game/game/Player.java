package com.mygdx.game.game;

import com.badlogic.gdx.graphics.Texture;


public class Player {
    //stores x loc (start at 240)
    private int x = 240;
    //stores y loc (start at 167)
    private int y = 167;
    //texture for our character
    public Texture player_img = new Texture("Assets/SPRITES/Megaman/0.png");

    //speed for our character in pixels/s

    private String name;

    //defining constants for direction
    private static final int LEFT = -1;
    private static final int RIGHT = 1;
    private static final int UP = 2;
    private static final int DOWN = -2;
    private final static int JUMP = 100;
    private static final int STANDING = 0;
    private int direction = LEFT;

    private int height;
    private int width;

    //initialization code
    public Player(String name) {
        this.name = name;
        Main.players.add(this);
        height = player_img.getHeight() * 2;
        width = player_img.getWidth() * 2;
    }

    //updates character's position
    public void render() {
        Main.batch.draw(player_img, x, y, width, height);

    }

    public void update(int direction) {
//        y -= 2;
    }

    //changes the character direction to right
    public void goRight() {
        direction = RIGHT;
        x += 10;
    }

    //changes character direction to left
    public void goLeft() {
        direction = LEFT;
        x -= 10;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void goUp() {
        direction = UP;
        y += 10;
    }

    public void goDown() {
        direction = RIGHT;
        y -= 10;
    }

    public void jump(int count){
        direction = JUMP;
        y += (count <= 25 ? 2 : -2);
//        for (int i = 0; i < 100; i++){
//            y += 1;
//            this.render();
//
//        }
//        for (int i = 0; i < 100; i++){
//            y -= 1;
//            this.render();
//        }
    }

    //changes character direction to standing
    public void setStanding() {
        direction = STANDING;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public int getDirection() {
        return direction;
    }
}

