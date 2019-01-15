package com.mygdx.game.game;


import java.util.ArrayList;

public class AllClassRenderer {
    public ArrayList<Player> players = new ArrayList<Player>();
    public ArrayList<Object> walls = new ArrayList<Object>();
    public ArrayList<Object> ladders = new ArrayList<Object>();

    public ArrayList<Object> objects = new ArrayList<Object>();

    public AllClassRenderer(ArrayList<Player> players, ArrayList<Object> walls, ArrayList<Object> ladders){
        this.players = players;
        this.walls = walls;
        this.ladders = ladders;
    }

    public void add(Object obj){
        objects.add(obj);
        System.out.println(objects.toString());
    }
}
