package org.example.game.models.model;

import org.example.game.Board.Board;
import org.example.game.models.player.AIPlayer;
import org.example.game.models.player.BasePlayer;
import org.example.game.motion.Motion;
import org.example.game.rules.EndGameEnum;
import org.example.game.viewmodels.BoardViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Game {

    private BoardViewModel boardViewModel;
    private BasePlayer whitePlayer, blackPlayer, currentPlayer;
    private GameHistory gameHistory;

    private int movesWithoutBeats = 0;
    private static final int MOVES_WITHOUT_BEATS_LIMIT = 5;

    private List<BasePlayer.GameOverILooseHandler> onGameOverHandlers = new ArrayList<>();

    public BoardViewModel getBoardViewModel() {
        return boardViewModel;
    }

    public GameHistory getGameHistory() { return gameHistory; }
    public void setGameHistory(GameHistory gameHistory) { this.gameHistory = gameHistory; }
    public BasePlayer getWhitePlayer() { return whitePlayer; }
    public BasePlayer getBlackPlayer() { return blackPlayer; }

    public Game(BasePlayer whitePlayer, BasePlayer black){
        this.whitePlayer = whitePlayer;
        this.whitePlayer.setBroadcastMotion(this::playerPrepareMotion);

    }

    private void playerPrepareMotion(Motion mtn){
        boolean currentWhite = currentPlayer == whitePlayer;
        Board board = (Board) boardViewModel.getBoard().Clone();
    }

    private void gameOverPlayerLoose(BasePlayer loosePlayer, EndGameEnum type){
        BasePlayer winner = isWhite(loosePlayer) ? blackPlayer : whitePlayer;

    }

    private boolean isWhite(BasePlayer player){
        return whitePlayer == player;
    }

    private void gameOver(EndGameEnum type, BasePlayer winner){
        for(BasePlayer.GameOverILooseHandler handler : onGameOverHandlers){
            handler.onGameOver(this, new GameOverEventArgs(type,winner,whitePlayer,blackPlayer));
        }
        dispatchResult(whitePlayer,blackPlayer,winner);
    }

    private static void dispatchResult(BasePlayer white, BasePlayer black, BasePlayer winner){
        AIPlayer whiteAI = white instanceof AIPlayer ? (AIPlayer) white : null;
        AIPlayer blackAI = black instanceof AIPlayer ? (AIPlayer) black : null;

        if (whiteAI == null || blackAI == null)
            return;

        if (winner == null) {
            Rating.getInstance(Rating.class).dispatchDrawGameResult(
                    whiteAI.getBrain(),
                    blackAI.getBrain()
            );
        } else {
            Rating.getInstance(Rating.class).dispatchWinGameResult(
                    whiteAI.getBrain(),
                    blackAI.getBrain()
            );
        }
    }

    public static String getEnumDescription(Enum value){
        try {
            Field fi = value.getClass().getField(value.name());
            DescriptionAttribute[] attributes = fi.getAnnotationsByType(DescriptionAttribute.class);

            if (attributes != null && attributes.length > 0)
                return attributes[0].value();
            else
                return value.name();
        } catch (Exception e) {
            return value.name();
        }
    }

    private void requestMotion(){
        BasePlayer secondPlayer = currentPlayer == blackPlayer ? whitePlayer : blackPlayer;
    }


    class GameOverEventArgs {
        private EndGameEnum reason;
        private BasePlayer winner;
        private BasePlayer whitePlayer;
        private BasePlayer blackPlayer;

        public GameOverEventArgs(EndGameEnum reason, BasePlayer winner, BasePlayer white, BasePlayer black) {
            this.reason = reason;
            this.winner = winner;
            this.whitePlayer = white;
            this.blackPlayer = black;
        }

        public EndGameEnum getReason() { return reason; }
        public BasePlayer getWinner() { return winner; }
        public BasePlayer getWhitePlayer() { return whitePlayer; }
        public BasePlayer getBlackPlayer() { return blackPlayer; }

        @Override
        public String toString() {
            return getGameInfo() + getGameResult();
        }

        private String getGameInfo() {
            return String.format("Партия [%s против %s] закончена. ", whitePlayer.getName(), blackPlayer.getName());
        }

        private String getGameResult() {
            if (reason == EndGameEnum.EG_DRAW)
                return String.format("Результат: %s", Game.getEnumDescription(reason));
            else
                return String.format("Результат: выиграл %s : %s", winner.getName(), Game.getEnumDescription(reason));
        }
    }

    @interface DescriptionAttribute {
        String value();
    }

}
