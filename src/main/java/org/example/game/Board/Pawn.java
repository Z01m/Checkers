package org.example.game.Board;

public class Pawn extends Piece {
    public Pawn(){}
    public Pawn(Color color)
        {
            this.color = color;
            this.setType(PieceType.PAWN);
        }
    public Object Clone(){
        return new Pawn(this.getColor());
    }    
        
}
