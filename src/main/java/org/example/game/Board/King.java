package org.example.game.Board;

public class King extends Pawn {
    public King(Piece.Color color)  {
        this.color = color;
        this.setType(PieceType.KING);
    }

    public Object Clone() {
        return new King(this.getColor());
    }
}
