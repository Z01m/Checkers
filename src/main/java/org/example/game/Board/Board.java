package org.example.game.Board;

import java.lang.constant.Constable;

public class Board {
    public final int BOARD_SIZE = 8;
    public Piece[][] pieces  = new Piece[BOARD_SIZE][BOARD_SIZE];
    private Board() {}
    public static Board CreateBoard() 
    {
        Board board = new Board();
        
        for(int x =0; x< board.BOARD_SIZE;x++)
            for (int y = 0; y < board.BOARD_SIZE; y++) 
            {
                int dx = (y + 1) % 2;
                if((x + dx) % 2 == 0) 
                {
                    if(y<3)
                        board.pieces[x][y]=new Pawn(Pawn.Color.BLACK);
                    else if(y>4)
                        board.pieces[x][y]=new Pawn(Pawn.Color.WHITE);
                    else
                        board.pieces[x][y]=new Piece(Piece.PieceType.None);
                } 
                else
                    board.pieces[x][y]=new Piece(Piece.PieceType.None);
            }
        return board;
    }
}
