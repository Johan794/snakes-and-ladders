package model;

public class Player {
    private int playerScore;
    private String player;

    public Player(int playerScore, String player) {
        this.playerScore = playerScore;
        this.player = player;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }
}
