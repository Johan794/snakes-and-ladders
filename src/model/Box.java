package model;

public class Box {
    private GameItem gameItem;
    private char player;
    private int position;
    private boolean islast;
    private boolean isFirst;
    private Snake snake;
    private Ladder ladder;

    private Box next;
    private Box previous;

    public Box() {
        gameItem = GameItem.NONE;
        isFirst = true;
        islast = false;
        position = 1;
    }
    //el ultimo cuadro se le debe hacer set al item
    public Box(int position){
        isFirst = false;
        islast = true;
        this.position = position;
    }

    public Box(String item , int position){
        gameItem = GameItem.valueOf(item);
        isFirst = false;
        islast = false;
        this.position = position;

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

    public void setLadder(Ladder ladder) {
        this.ladder = ladder;
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
}
