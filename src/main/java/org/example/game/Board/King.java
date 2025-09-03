package org.example.game.Board;

public class King extends Pawn {
    public King(Color color) {
        this.setType((color == Color.WHITE) ? PieceType.White_king : PieceType.Black_king);
    }

    public Object Clone() {
        return new King(getColor());
    }
}
