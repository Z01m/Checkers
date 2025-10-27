package org.example.game.models.model;

import org.example.game.motion.Motion;
import org.example.game.viewmodels.BoardViewModel;

public class MotionInfo {

    private Motion motion;
    private BoardViewModel resultBoard;
    private Boolean playerColorWhite;
    private String gameOverMessage;

    // Конструкторы
    public MotionInfo() {}

    public MotionInfo(Motion motion, BoardViewModel resultBoard, Boolean playerColorWhite) {
        this.motion = motion;
        this.resultBoard = resultBoard;
        this.playerColorWhite = playerColorWhite;
    }

    public MotionInfo(Motion motion, BoardViewModel resultBoard, Boolean playerColorWhite, String gameOverMessage) {
        this.motion = motion;
        this.resultBoard = resultBoard;
        this.playerColorWhite = playerColorWhite;
        this.gameOverMessage = gameOverMessage;
    }

    // Геттеры и сеттеры
    public Motion getMotion() {
        return motion;
    }

    public void setMotion(Motion motion) {
        this.motion = motion;
    }

    public BoardViewModel getResultBoard() {
        return resultBoard;
    }

    public void setResultBoard(BoardViewModel resultBoard) {
        this.resultBoard = resultBoard;
    }

    public Boolean getPlayerColorWhite() {
        return playerColorWhite;
    }

    public void setPlayerColorWhite(Boolean playerColorWhite) {
        this.playerColorWhite = playerColorWhite;
    }

    public String getGameOverMessage() {
        return gameOverMessage;
    }

    public void setGameOverMessage(String gameOverMessage) {
        this.gameOverMessage = gameOverMessage;
    }

    @Override
    public String toString() {
        return MotionInfoConverter.toString(playerColorWhite, motion, gameOverMessage);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        MotionInfo that = (MotionInfo) obj;

        if (motion != null ? !motion.equals(that.motion) : that.motion != null) return false;
        if (resultBoard != null ? !resultBoard.equals(that.resultBoard) : that.resultBoard != null) return false;
        if (playerColorWhite != null ? !playerColorWhite.equals(that.playerColorWhite) : that.playerColorWhite != null)
            return false;
        return gameOverMessage != null ? gameOverMessage.equals(that.gameOverMessage) : that.gameOverMessage == null;
    }

    @Override
    public int hashCode() {
        int result = motion != null ? motion.hashCode() : 0;
        result = 31 * result + (resultBoard != null ? resultBoard.hashCode() : 0);
        result = 31 * result + (playerColorWhite != null ? playerColorWhite.hashCode() : 0);
        result = 31 * result + (gameOverMessage != null ? gameOverMessage.hashCode() : 0);
        return result;
    }

}
