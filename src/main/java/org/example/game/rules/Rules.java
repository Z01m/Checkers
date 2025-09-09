package org.example.game.rules;

import org.example.game.Board.Board;
import org.example.game.Board.Piece;
import org.example.game.motion.Motion;
import org.example.game.motion.Point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.XMLFormatter;

public final class Rules {
    private static int[][] moveDirections = new int[][] { { 1, 1 }, { -1, 1 }, { -1, -1 }, { 1, -1 } };
    
    private static class Pawn{
        private static int[] dX = new int[] { 1, -1 };
        
        static void findMotion(Board board, int x, int y, boolean isWhite, List<List<Point>>moves, BeatNode kills){
            FindMoves(board, isWhite, x, y, moves);
            FindKills(board, isWhite, x, y, kills);
        }
        
        private static void FindMoves(Board board, boolean isWhite, int x, int y, List<List<Point>>moves){
            int dy = isWhite ? -1 : 1;
            int yN = y + dy;
            
            for (int i = 0;i< dX.length;i++) {
                int xN = x + dX[i];
                if(canMove(board,xN,yN)) {
                    moves.add(Arrays.asList(new Point(x, y), new Point(xN, yN)));
                }
            }
        }
        
        private static void FindKills(Board board, boolean isWhite, int x, int y, BeatNode kills){
            for (int i = 0;i<4;i++) {

                int xN = x + moveDirections[i][0];
                int yN = y + moveDirections[i][1];
                int xN2 = x + 2 * moveDirections[i][0];
                int yN2 = y + 2 * moveDirections[i][1];

                if (!InBounds(xN) || !InBounds(yN) || !InBounds(xN2) || !InBounds(yN2))
                    continue;

                if (board.getPiece(xN2,yN2).getType() != Piece.PieceType.NONE) continue;
                
            }
        }
        
        
        
        static boolean canMove(Board board, int xN, int yN) {
            if(!InBounds(xN) || !InBounds(yN)) return false;
            return board.getPiece(xN,yN).getType() == Piece.PieceType.NONE;
        }
    }
    private static boolean InBounds(int val)
    {
        return val >= 0 && val < Board.BOARD_SIZE;
    }

    private static boolean CheckersHasDifferentColor(Board board, int x, int y, int xN, int yN)
    {
        return (board.getPiece(x,y).getColor() == Piece.Color.WHITE && board.getPiece(xN,yN).getColor() == Piece.Color.BLACK ||
                (board.getPiece(x,y).getColor() == Piece.Color.BLACK && board.getPiece(xN,yN).getColor() == Piece.Color.WHITE));
    }
    
}
