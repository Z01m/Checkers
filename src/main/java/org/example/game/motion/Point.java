package org.example.game.motion;

import org.example.game.Board.Board;

public class Point {
    public Integer X;
    public Integer Y;
    
    public Point(Integer x, Integer y) {
        this.X = x;
        this.Y = y;
    }
    public Point() {
        this.X = 0;
        this.Y = 0;
    }
    
    public Point(int index){
        this.X = index/ Board.BOARD_SIZE;
        this.Y = index % Board.BOARD_SIZE;
    }
    

    @Override
    public boolean equals(Object obj) {
        var other = (Point) obj;
        if(other == null) return false;
        return this.X == other.X && this.Y == other.Y;
    }
}
