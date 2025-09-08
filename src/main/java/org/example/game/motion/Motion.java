package org.example.game.motion;

import java.util.ArrayList;
import java.util.List;

public class Motion {
    public List<Point> Moves;
    
    public Motion() {
        Moves = new ArrayList<Point>();
    }
    
    public Motion(List<Point> moves) {
        Moves = moves;
    }
    public boolean isEmpty(){
        return Moves.isEmpty();
    }
    
    public int size(){
        return Moves.size();
    }
    public List<Point> getMoves(){
        return Moves;
    }
    public void setMoves(List<Point> moves){
        Moves = moves;
    }
}