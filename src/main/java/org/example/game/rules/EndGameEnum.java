package org.example.game.rules;

public enum EndGameEnum {
    EG_NONE(""),
    EG_TIMEOUT("Время на ход вышло"),
    EG_INVALID_MOTION("Ход не соответствует правилам"),
    EG_DRAW("Ничья"),
    EG_WIN_BLACK("Черные победили"),
    EG_WIN_WHITE("Белые победили");

    private final String description;

    EndGameEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}
