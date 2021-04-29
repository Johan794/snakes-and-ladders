package model;

public class Player {
    private int playerScore;
    private String player;

    private Player left;
    private Player right;
    private Player parent;
    private Player next;


    public Player(String player) {
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


    public Player getLeft() {
        return left;
    }

    public void setLeft(Player left) {
        this.left = left;
    }

    public Player getRight() {
        return right;
    }

    public void setRight(Player right) {
        this.right = right;
    }

    public Player getParent() {
        return parent;
    }

    public void setParent(Player parent) {
        this.parent = parent;

    }

    public Player getNext(){
        return next;
    }

    public void setNext (Player next){
        this.next = next;

    }
}
