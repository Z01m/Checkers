package org.example.game.player;

import org.example.game.Board.Board;
import org.example.game.motion.Motion;
import org.example.game.motion.Point;

import java.util.List;

import org.example.game.rules.Rules;
import org.example.game.rules.motionValidator;

public class HumanPlayer extends BasePlayer{
    private Board initialBoard;
    private Board currentBoard;
    private boolean isWhite;
    private Motion currentMotion;
    private List<Point> validPoints;
    motionValidator validator;
    
    @Override
    public String getName() {
        return "Human";
    }
    
    @Override
    public void requestMotion(Board board, boolean isWhite) {
        this.initialBoard = (Board) board.Clone();
        this.currentBoard = (Board) board;
        this.isWhite = true;
        this.currentMotion = new Motion();
        
        this.validator = Rules.FindValidMotions(board, isWhite); //возможны ошики
        
        
    }
}
