package model;

/**
 * The type Player.
 */
public class Player {

    //Attributes.
    private int playerScore;
    private String player;

    //Attributes para el arbol
    private Player left;
    private Player right;
    private Player parent;


    //Attributes para lista.
    private Player next;

    /**
     * Constructor.
     * Instantiates a new Player.
     *
     * @param player the player date is String type.
     */
    public Player(String player){
        this.player = player;
    }

    //Getters ans Setters


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


}
