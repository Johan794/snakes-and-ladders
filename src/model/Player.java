package model;

public class Player {
    private int playerScore;
    private String player;

    //Attributes para el arbol
    private Player left;
    private Player right;
    private Player parent;

    //Attributes para lista.
    private Player next;

    //Constructor para insertar
    public Player(String p){
        player = p;
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

    public Player getNext() {
        return next;
    }

    public void setNext(Player next) {
        this.next = next;
    }
}
