package org.example.game.Board;

import java.lang.constant.Constable;

public class Board {
    public final static int BOARD_SIZE = 8;
    public Piece[][] pieces  = new Piece[BOARD_SIZE][BOARD_SIZE];
    private Board() {}
    public static Board CreateBoard() 
    {
        Board board = new Board();
        
        for(int x =0; x< BOARD_SIZE;x++)
            for (int y = 0; y < BOARD_SIZE; y++) 
            {
                int dx = (y + 1) % 2;
                if((x + dx) % 2 == 0) 
                {
                    if(y<3)
                        board.pieces[x][y]=new Pawn(Piece.Color.BLACK);
                    else if(y>4)
                        board.pieces[x][y]=new Pawn(Piece.Color.WHITE);
                    else
                        board.pieces[x][y]=new Piece(Piece.PieceType.NONE);
                } 
                else
                    board.pieces[x][y]=new Piece(Piece.PieceType.NONE);
            }
        return board;
    }

    public Piece getPiece(int x, int y) {
        return pieces[x][y];
    }
    public void setPiece(int x, int y, Piece piece) {
        pieces[x][y]=piece;
    }
    public boolean isEmpty(int x, int y) {
        return pieces[x][y].getType() == Piece.PieceType.NONE;
    }
    public Object Clone(){
        Board ret = new Board();
        for(int x =0; x < BOARD_SIZE; x++)
            for(int y = 0; y < BOARD_SIZE; y++) {
            ret.pieces[x][y] = getPiece(x, y);
            }
        return ret;
    }
}
