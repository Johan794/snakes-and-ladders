package model;

public class Box {
    private char player;
    private int position;
    private boolean islast;
    private boolean isFirst;
    private char itemSymbol;

    private Snake snake;
    private Ladder ladder;
    private GameItem gameItem;

    private Box next;
    private Box previous;

    public Box() {
        gameItem = GameItem.NONE;
        isFirst = true;
        islast = false;
        position = 1;
        itemSymbol =' ';
        player = ' ';
    }
    //el ultimo cuadro se le debe hacer set al item
    public Box(int position){
        isFirst = false;
        islast = true;
        this.position = position;
        itemSymbol =' ';
        player = ' ';
    }

    public Box(String item , int position){
        gameItem = GameItem.valueOf(item);
        isFirst = false;
        islast = false;
        this.position = position;
        itemSymbol =' ';
        player = ' ';

    }

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

    public char getItemSymbol() {
        return itemSymbol;
    }

    public void setItemSymbol(char itemSymbol) {
        this.itemSymbol = itemSymbol;
    }
}
