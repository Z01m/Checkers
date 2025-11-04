package org.example.game.brainBase;

public class BrainBaseExtensions {

    public static String getName(BrainBase brainBase) {
        if (brainBase == null) return "";

        BrainInfoAttribute brainInfoAttr = brainBase.getClass().getAnnotation(BrainInfoAttribute.class);
        if (brainInfoAttr == null) return "";

        return brainInfoAttr.brainName();
    }
}
