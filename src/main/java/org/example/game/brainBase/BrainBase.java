package org.example.game.brainBase;

import org.example.game.Board.Board;
import org.example.game.motion.Motion;

public abstract class BrainBase {
    public abstract Motion FindMotion(Board board, boolean isWhite);
}
