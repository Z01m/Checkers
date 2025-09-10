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
                
                if(CheckersHasDifferentColor(board,x,y,xN,yN)) {
                    Point Beated = new Point(xN, yN);
                    
                    if(kills.BranchContaintsValue(Beated)) continue;
                    if(kills.Move == null) kills.Move = new Point(x,y);
                    var beat = new BeatNode();
                    beat.setMove(new Point(xN2, yN2));
                    beat.setBeated(Beated);

                    kills.addChild(beat);
                    Board boardCopy = (Board) board.Clone();

                    boardCopy.setPiece(xN2,yN2,boardCopy.getPiece(x,y));
                    boardCopy.setPiece(x,y,new Piece(Piece.PieceType.NONE));
                    if (ShouldBecomeKing(yN2, isWhite))
                        King.FindKills(boardCopy, isWhite, xN2, yN2,  beat);
                    else
                        FindKills(boardCopy, isWhite, xN2, yN2,  beat);
                }
                
            }
        }
        
        static boolean canMove(Board board, int xN, int yN) {
            if(!InBounds(xN) || !InBounds(yN)) return false;
            return board.getPiece(xN,yN).getType() == Piece.PieceType.NONE;
        }
        
    }
    
    private static class King{
        static void FindMotion(Board board, boolean isWhite, int x, int y, List<List<Point>> moves, BeatNode kill){
            FindMoves(board,x,y,moves);
            FindKills(board,isWhite,x,y,kill);
            kill.FilterForKingKills();
        }
        
        static void FindMoves(Board board, int x, int y, List<List<Point>> moves){
            for(int i = 0;i<4;i++) {
                int xN = x + moveDirections[i][0];
                int yN = y + moveDirections[i][1];
                
                while (InBounds(xN) && InBounds(yN) && board.isEmpty(xN,yN)) {
                    moves.add(Arrays.asList(new Point(x, y), new Point(xN, yN)));
                    xN = x + moveDirections[i][0];
                    yN = y + moveDirections[i][1];
                }
            }
        }
        
        static void FindKills(Board board, boolean isWhite, int x, int y, BeatNode kills){
            for(int i = 0;i<4;i++) {
                int dx = moveDirections[i][0];
                int dy = moveDirections[i][1];
                
                int xN = x + dx;
                int yN = y + dy;
                
                while (InBounds(xN) && InBounds(yN) && board.isEmpty(xN,yN)) {
                    xN += dx;
                    yN += dy;
                }
                if(!InBounds(xN) || !InBounds(yN) || CheckersHasSameColor(board,x,y,xN,yN)) continue;

                int xN2 = xN + dx;
                int yN2 = yN + dy;

                while (InBounds(xN2) && InBounds(yN2) && board.isEmpty(xN2, yN2)){
                    var Beated = new Point(xN, yN);
                    if (kills.BranchContaintsValue(Beated))
                    {
                        xN2 += dx;
                        yN2 += dy;
                        continue;
                    }
                    if (kills.Move == null)
                    {
                        kills.Move = new Point(x, y);
                    }
                    var beat = new BeatNode();
                    beat.setMove(new Point(xN2, yN2));
                    beat.setBeated(Beated);
                    
                    kills.addChild(beat);

                    Board boardCopy = (Board) board.Clone();

                    boardCopy.setPiece(xN2,yN2,boardCopy.getPiece(x,y));
                    boardCopy.setPiece(x,y,new Piece(Piece.PieceType.NONE));
                    
                    Pawn.FindKills(boardCopy, isWhite, xN2, yN2, beat);

                    xN2 += dx;
                    yN2 += dy;
                }
            }
        }
        
    }
    
    private static boolean InBounds(int val)
    {
        return val >= 0 && val < Board.BOARD_SIZE;
    }

    private static boolean ShouldBecomeKing(int y, boolean isWhite)
    {
        return y == 7 && !isWhite || y == 0 && isWhite;
    }

    private static boolean CheckersHasSameColor(Board board, int x, int y, int xN, int yN)
    {
        return (board.getPiece(x,y).getColor() == Piece.Color.WHITE && board.getPiece(xN,yN).getColor() == Piece.Color.WHITE ||
                (board.getPiece(x,y).getColor() == Piece.Color.BLACK && board.getPiece(xN,yN).getColor() == Piece.Color.BLACK));
    }

    private static boolean CheckersHasDifferentColor(Board board, int x, int y, int xN, int yN)
    {
        return (board.getPiece(x,y).getColor() == Piece.Color.WHITE && board.getPiece(xN,yN).getColor() == Piece.Color.BLACK ||
                (board.getPiece(x,y).getColor() == Piece.Color.BLACK && board.getPiece(xN,yN).getColor() == Piece.Color.WHITE));
    }
    
}
