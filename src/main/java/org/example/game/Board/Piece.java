package org.example.game.Board;

public class Piece {
    
    public enum PieceType {
        None,
        White_pawn,
        Black_pawn,
        White_king,
        Black_king,
        white,
        black
    }
    public Piece() {}
    public Piece(PieceType type) {this.setType(type);}
    
    private PieceType type = PieceType.None;

    public PieceType getType() {return type;}
    public void setType(PieceType type) {this.type = type;}
}
