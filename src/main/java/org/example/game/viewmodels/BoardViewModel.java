package org.example.game.viewmodels;

import org.example.game.Board.Board;
import org.example.game.Board.Piece;
import org.example.game.motion.Point;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class BoardViewModel extends ViewModelBase{
    private Board board;
    private Point selectedPiece;
    private boolean currentPlayerWhite;
    private boolean frozen = false;
    private List<Point> availableMovesPieces = new ArrayList<>();

    private SelectCellHandler onSelectCell;
    private BoardChangeCancelHandler boardChangeCancel;

    private Point _selectedPiece = null;

    public Point get_selectedPiece() {
        return _selectedPiece;
    }

    public void set_selectedPiece(Point _selectedPiece) {
        this._selectedPiece = _selectedPiece;
    }

    private Timer uiTimer;

    @FunctionalInterface
    public interface SelectCellHandler {
        void onCellSelected(Point point);
    }
    @FunctionalInterface
    public interface BoardChangeCancelHandler{
        void onBoardChangeCancel();
    }

    public BoardViewModel(Board board){
        this.board = board;
        this.selectedPiece = null;
        this.availableMovesPieces = new ArrayList<>();

        initializeUTTimer();
    }

    private void initializeUTTimer(){
        uiTimer = new Timer(16,e -> {});
        uiTimer.start();
    }

    public Board getBoard(){
        return board;
    }

    public List<Point> getAvailableMovesPieces() {
        return new ArrayList<>(availableMovesPieces);
    }

    public void setAvailableMovesPieces(List<Point> availableMovesPieces) {
        this.availableMovesPieces = new ArrayList<>(availableMovesPieces);
        onPropertyChanged("AvailableMovesPieces");
    }

    public Boolean getCurrentPlayerWhite() {
        return currentPlayerWhite;
    }
    public void setCurrentPlayerWhite(boolean currentPlayerWhite) {
        this.currentPlayerWhite = currentPlayerWhite;
        onPropertyChanged("CurrentPlayerWhite");
    }
    public Point getSelectedPiece() {
        return selectedPiece;
    }
    public void setSelectedPiece(Point selectedPiece) {
        this.selectedPiece = selectedPiece;
        onPropertyChanged("SelectedPiece");
    }
    public Boolean getFrozen() {
        return frozen;
    }

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
        onPropertyChanged("Frozen");
    }
    public void setOnSelectCell(SelectCellHandler handler) {
        this.onSelectCell = handler;
    }
    public void setBoardChangeCancel(BoardChangeCancelHandler handler) {
        this.boardChangeCancel = handler;
    }
    public void onBoardChangeCancel(){
        if(boardChangeCancel != null){
            boardChangeCancel.onBoardChangeCancel();
        }
    }
    public Piece getPiece(int x, int y) {
        if (board == null)
            return new Piece();
        return board.getPiece(x, y);
    }

    public void setPiece(int x, int y, Piece piece) {
        if (board == null) return;
        board.setPiece(x, y, piece);
        onPropertyChanged("Board");
    }

    public Piece getPiece(Point pt) {
        return board.getPiece(pt.X, pt.Y);
    }

    public void setPiece(Point pt, Piece piece) {
        board.setPiece(pt.X, pt.Y, piece);
        onPropertyChanged("Board");
    }
    public void updateBoard(Board newBoard) {
        for (int i = 0; i < Board.BOARD_SIZE; i++) {
            for (int j = 0; j < Board.BOARD_SIZE; j++) {
                this.setPiece(i, j, newBoard.getPiece(i, j));
            }
        }

        onPropertyChanged("Board");
        onPropertyChanged("SelectedPiece");
    }
    public void updateUI() {
        onPropertyChanged("Board");
    }
    public void handleMouseClick(Point pt) {
        if (!frozen && onSelectCell != null) {
            onSelectCell.onCellSelected(pt);
        }
    }

    public boolean canHandleMouseClick(Point pt) {
        return true;
    }

    public void dispose() {
        if (uiTimer != null) {
            uiTimer.stop();
        }
    }
}
