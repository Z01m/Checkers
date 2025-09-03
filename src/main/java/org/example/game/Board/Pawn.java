package org.example.game.Board;

public class Pawn extends Piece {
    public enum Color {WHITE, BLACK}
    public Pawn(){}
    public Pawn(Color color) {
        this.setType((color == Color.WHITE) ? PieceType.White_pawn : PieceType.Black_pawn);
    }

    public Object Clone() {
        return new Pawn(getColor());
    }

    public Color getColor() {
        return (this.getType() == PieceType.White_pawn || this.getType() == PieceType.White_king)
                ? Color.WHITE
                : Color.BLACK;
    }
}
