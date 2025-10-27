package org.example.game.models.model;

public class BrainStats {
    private int wins;
    private int loses;
    private int draws;

    public BrainStats() {
        this(0, 0, 0);
    }

    public BrainStats(int wins, int loses, int draws) {
        this.wins = wins;
        this.loses = loses;
        this.draws = draws;
    }

    // Геттеры и сеттеры
    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLoses() {
        return loses;
    }

    public void setLoses(int loses) {
        this.loses = loses;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    // Вычисляемое свойство WinRate
    public String getWinRate() {
        double wins = (double) this.wins;
        double totalGames = (double) (this.wins + this.loses + this.draws);
        double winRate = totalGames > 0 ? wins / totalGames : 0;

        return String.format("%.2f%%", winRate * 100);
    }

    // Методы для увеличения счетчиков
    public void addWin() {
        wins++;
    }

    public void addLoss() {
        loses++;
    }

    public void addDraw() {
        draws++;
    }

    public void reset() {
        wins = 0;
        loses = 0;
        draws = 0;
    }

    public int getTotalGames() {
        return wins + loses + draws;
    }

    @Override
    public String toString() {
        return String.format("BrainStats{Wins: %d, Loses: %d, Draws: %d, WinRate: %s}",
                wins, loses, draws, getWinRate());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        BrainStats that = (BrainStats) obj;
        return wins == that.wins &&
                loses == that.loses &&
                draws == that.draws;
    }

    @Override
    public int hashCode() {
        int result = wins;
        result = 31 * result + loses;
        result = 31 * result + draws;
        return result;
    }
}
