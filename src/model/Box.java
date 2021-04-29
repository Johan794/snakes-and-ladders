package model;

/**
 * The type Box.
 */
public class Box {

    //Attributes.
    private char player;
    private int position;
    private boolean islast;
    private boolean isFirst;
    private String itemSymbol;

    //Relationship
    private Snake snake;
    private Ladder ladder;
    private GameItem gameItem;

    private Box next;
    private Box previous;


    /**
     * Constructor.
     * Instantiates a new Box.
     *
     */
    public Box() {
        gameItem = GameItem.NONE;
        isFirst = true;
        islast = false;
        position = 1;
        itemSymbol =" ";
        player = ' ';
    }

    /**
     * Constructor.
     * Instantiates a new Box.
     *
     * @param position the position date is int type.
     */
    public Box(int position){
        isFirst = false;
        islast = false;
        this.position = position;
        itemSymbol =" ";
        player = ' ';
        //El ultimo cuadro se le debe hacer set al item
    }

    //Getters and Setters.

    public Snake getSnake() {
        return snake;
    }


    public void setSnake(Snake snake) {
        this.snake = snake;
    }


    public Ladder getLadder() {
        return ladder;
    }


    public void setLadder(Ladder newLadder) {
        ladder = newLadder;
    }


    public char getPlayer() {
        return player;
    }


    public void setPlayer(char player) {
        this.player = player;
    }


    public int getPosition() {
        return position;
    }


    public void setPosition(int position) {
        this.position = position;
    }


    public boolean isIslast() {
        return islast;
    }


    public void setIslast(boolean islast) {
        this.islast = islast;
    }


    public boolean isFirst() {
        return isFirst;
    }


    public void setFirst(boolean first) {
        isFirst = first;
    }


    public Box getNext() {
        return next;
    }


    public void setNext(Box next) {
        this.next = next;
    }

    public Box getPrevious() {
        return previous;
    }

    public void setPrevious(Box previous) {
        this.previous = previous;
    }

    public GameItem getGameItem() {
        return gameItem;
    }

    public void setGameItem(GameItem gameItem) {
        this.gameItem = gameItem;
    }

    public String getItemSymbol() {
        return itemSymbol;
    }

    public void setItemSymbol(String itemSymbol) {
        this.itemSymbol = itemSymbol;
    }

    /**
     * Isnumeric boolean.
     *
     * @return the boolean
     */
    public boolean isnumeric(){
        boolean out;
        try {
            Integer.parseInt(itemSymbol);
            out = true;
        }catch (NumberFormatException nm){
          out = false;
        }

        return out;
    }
}
