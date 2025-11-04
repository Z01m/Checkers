package org.example.game.models.player;


import org.example.game.Board.Board;
import org.example.game.brainBase.BrainBase;
import org.example.game.brainBase.BrainBaseExtensions;
import org.example.game.motion.Motion;
import org.example.game.rules.EndGameEnum;
import org.example.game.viewmodels.BoardViewModel;

import javax.swing.*;
import java.util.concurrent.*;

public class AIPlayer extends BasePlayer{

    private BrainBase brain;
    private ExecutorService executorService;

    public AIPlayer(BrainBase brain) {
        super();
        this.brain = brain;
        this.executorService = Executors.newCachedThreadPool();
    }
    public BrainBase getBrain() {
        return brain;
    }

    @Override
    public String getName() {
        return (brain != null ? BrainBaseExtensions.getName(brain) : "AIPlayer");
    }

    @Override
    public void requestMotion(BoardViewModel boardViewModel, boolean isWhite) {
        requestMotionAsync(boardViewModel.getBoard(), isWhite);
    }

    private void requestMotionAsync(Board board, boolean isWhite) {
        SwingWorker<Motion, Void> worker = new SwingWorker<Motion, Void>() {
            @Override
            protected Motion doInBackground() throws Exception {
                return findMotion(board, isWhite);
            }

            @Override
            protected void done() {
                try {
                    Motion mtn = get();
                    if (mtn != null) {
                        broadcastMotion(mtn);
                    } else {
                        onBroadcastGameOver(EndGameEnum.EG_TIMEOUT);
                    }
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                    onBroadcastGameOver(EndGameEnum.EG_NONE);
                } catch (CancellationException e) {
                    onBroadcastGameOver(EndGameEnum.EG_TIMEOUT);
                }
            }
        };
        worker.execute();
    }

    private Motion findMotion(Board board, boolean isWhite) {

        //впихать таймер
        boolean hasTimeout = true;
        long timeout = 0;

        Future<Motion> future = executorService.submit(() -> brain.FindMotion(board, isWhite));

        try {
            if (!hasTimeout) {
                return future.get();
            } else {
                return future.get(timeout, TimeUnit.MILLISECONDS);
            }
        } catch (TimeoutException e) {
            future.cancel(true);
            return null;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void dispose() {
        if (executorService != null) {
            executorService.shutdownNow();
            try {
                if (!executorService.awaitTermination(1, TimeUnit.SECONDS)) {
                    executorService.shutdownNow();
                }
            } catch (InterruptedException e) {
                executorService.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }
}
