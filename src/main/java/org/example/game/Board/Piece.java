package org.example.game.Board;

import java.security.PrivateKey;

public class Piece {
    public enum Color {WHITE, BLACK}
    public enum PieceType {
        NONE,
        PIECE,
        PAWN,
        KING
    }
    public Piece() {}
    public Piece(PieceType type) {this.setType(type);}
    protected Color color;
    
    protected PieceType type = PieceType.NONE;

    public PieceType getType() {return type;}
    public void setType(PieceType type) {this.type = type;}
    public Color getColor() {return this.color;}
}