package com.mygdx.game.game;


import java.util.ArrayList;

public class AllClassRenderer {
    public ArrayList<Player> players;// = new ArrayList<Player>();
    public ArrayList<Object> walls;// = new ArrayList<Object>();
    public ArrayList<Object> ladders;// = new ArrayList<Object>();

    public ArrayList<ArrayList<?>> objects = new ArrayList<ArrayList<?>>();
    public AllClassRenderer(ArrayList<Player> players, ArrayList<Object> walls, ArrayList<Object> ladders){
        this.players = players;
        this.walls = walls;
        this.ladders = ladders;
//        objects.add(this.walls);
        objects.add(this.players);
//        objects.add(this.ladders);
    }

    public void add(Object obj){
        if (obj instanceof Player){
            players.add((Player) obj);
        }
//        else if (obj instanceof Object){ // *FIX* this must be changed to the wall class once that is made
//            walls.add(obj);
//        }else if (obj instanceof Object){ // *FIX* this must be changed to the ladder class once that is made
//            ladders.add(obj);
//        }
        System.out.println(objects.toString());
    }

    public void render(){
        for(int i = 0; i < objects.size(); i++){
            for (int n = 0; n > objects.get(i).size(); n++){
                ((Player) objects.get(i).get(n)).render();
            }
        }
    }
}
