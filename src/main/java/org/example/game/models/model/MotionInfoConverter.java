package org.example.game.models.model;

import org.example.game.motion.Motion;
import org.example.game.motion.Point;

public class MotionInfoConverter {

    private static final String[] LETTERS = {"A", "B", "C", "D", "E", "F", "G", "H"};

    public static String toString(Boolean isWhite, Motion mtn, String gameOverMessage) {
        StringBuilder ret = new StringBuilder(getColorPart(isWhite));

        if (gameOverMessage != null && !gameOverMessage.isEmpty()) {
            ret.append(gameOverMessage);
        }

        ret.append(getMotionPart(mtn));

        return ret.toString();
    }

    private static String getColorPart(Boolean isWhite) {
        if (isWhite == null) {
            return "";
        }
        return isWhite ? "Белые: " : "Черные: ";
    }

    private static String getMotionPart(Motion mtn) {
        if (mtn == null) {
            return "";
        }

        StringBuilder ret = new StringBuilder();
        for (Point m : mtn.getMoves()) {
            ret.append(String.format(" %s%d ", LETTERS[m.X], m.Y + 1));
        }
        return ret.toString();
    }

}
