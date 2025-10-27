package org.example.game.models.player;

import org.example.game.Board.Board;
import org.example.game.Board.Piece;
import org.example.game.UI.BoardPanel;
import org.example.game.motion.Motion;
import org.example.game.motion.Point;

import java.util.ArrayList;
import java.util.List;

import org.example.game.rules.Rules;
import org.example.game.rules.motionValidator;
import org.example.game.viewmodels.BoardViewModel;

public class HumanPlayer extends BasePlayer{
    motionValidator validator;
    private Motion motion;
    private List<Point> validPoints;


    private Board initialBoard;

    private BoardViewModel currentBoard;

    private boolean isWhite;



    @Override
    public String getName() {
        return "Human";
    }
    
    @Override
    public void requestMotion(BoardViewModel BoardPanel, boolean isWhite) {

        this.initialBoard = (Board) BoardPanel.getBoard().Clone();
        this.currentBoard = BoardPanel;
        this.motion = new Motion();
        
        this.validator = Rules.FindValidMotions(BoardPanel.getBoard(), isWhite);
        //возможны ошики
        validPoints = validator.FindValidPoints(motion);
        this.currentBoard.setAvailableMovesPieces(new ArrayList<>(validPoints));
    }

    @Override
    public void onSelectCell(Point selection){
        currentBoard.setAvailableMovesPieces(new ArrayList<>(validPoints));
        if(! validPoints.contains(selection))return;
        if (!motion.getMoves().isEmpty()) {
            currentBoard.setSelectedPiece(motion.getMoves().stream()
                    .reduce((first, second) -> second)
                    .orElse(null));
        }
        if (currentBoard.get_selectedPiece() == null && currentBoard.getBoard().getPiece(selection).getType() == Piece.PieceType.NONE) return;
        if (currentBoard.get_selectedPiece() == null && currentBoard.getBoard().getPiece(currentBoard.get_selectedPiece()).getType() == Piece.PieceType.NONE)
        {
            if (currentBoard.get_selectedPiece().equals(selection)) return;

            currentBoard.getBoard().setPiece(selection,currentBoard.getBoard().getPiece(currentBoard.get_selectedPiece()));
            currentBoard.getBoard().getPiece(selection).setType(Piece.PieceType.NONE);

        }
        currentBoard.set_selectedPiece(selection);

        motion.setMoves(motion.getMoves().add(selection));
        validPoints = validator.FindValidPoints(motion);

        currentBoard.setAvailableMovesPieces(new ArrayList<>(validPoints));

        if(validPoints.size() == 0){
            currentBoard.set_selectedPiece(null);
            currentBoard.updateBoard(initialBoard);
            initialBoard = null;
            boardcastMotion(motion);
        }
    }

}
