package org.example.game.models.player;

import org.example.game.UI.BoardPanel;
import org.example.game.motion.Motion;
import org.example.game.motion.Point;
import org.example.game.rules.EndGameEnum;
import org.example.game.viewmodels.BoardViewModel;

public abstract class BasePlayer {
    
    public interface MotionHandler {
        void onMotionMade(Motion motion);
    }
    
    public interface GameOverILooseHandler {
        void onGameOver(BasePlayer sender, EndGameEnum type);
    }
    
    protected MotionHandler broadcastMotion;
    protected GameOverILooseHandler broadcastGameOverILoose;

    public abstract void requestMotion(BoardViewModel BoardPanel, boolean isWhite);
    public abstract String getName();
    
    public void setBroadcastMotion(MotionHandler motionHandler) {
        this.broadcastMotion = motionHandler;
    }
    
    public void setBroadcastGameOverILoose(GameOverILooseHandler gameOverHandler) {
        this.broadcastGameOverILoose = gameOverHandler;
    }

    protected void boardcastMotion(Motion mtn) {
        if (broadcastMotion != null) {
            broadcastMotion.onMotionMade(mtn);
        }
    }
    protected void onBroadcastGameOver(EndGameEnum type) {
        if (broadcastGameOverILoose != null) {
            broadcastGameOverILoose.onGameOver(this, type);
        }
    }
    
    
    public BasePlayer() { }
    
    public void onSelectCell(Point selection) {}
    public void onBoardChangeCancel(){}
    
}
