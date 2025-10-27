package org.example.game.models.player;

import org.example.game.Board.Board;
import org.example.game.UI.BoardPanel;
import org.example.game.motion.Motion;
import org.example.game.motion.Point;

import java.util.List;

import org.example.game.rules.Rules;
import org.example.game.rules.motionValidator;

public class HumanPlayer extends BasePlayer{
    motionValidator validator;
    private Motion motion;
    private List<Point> validPoints;


    private Board initialBoard;

    private BoardPanel CurrentPanel;

    private boolean isWhite;

    @Override
    public String getName() {
        return "Human";
    }
    
    @Override
    public void requestMotion(BoardPanel BoardPanel, boolean isWhite) {

        this.initialBoard = (Board) BoardPanel.getBoard().Clone();
        this.CurrentPanel =BoardPanel;
        this.motion = new Motion();
        
        this.validator = Rules.FindValidMotions(BoardPanel.getBoard(), isWhite);
        //возможны ошики
        validPoints = validator.FindValidPoints(motion);
        
    }

    @Override
    public void onBoardChangeCancel(){
        motion.getMoves().clear();
        validPoints = validator.FindValidPoints(new Motion());

    }
}
