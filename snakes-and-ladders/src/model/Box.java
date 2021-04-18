package model;

public class Box {
    private GameObject gameItem;
    private String player;
    private int position;
    private boolean islast;
    private boolean isFirst;

    private Box next;

    public Box(String item) {
        if(item.equals("LADDER")){
            gameItem = GameObject.NONE;
        }else{
            gameItem = GameObject.valueOf(item);
        }
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
        gameItem = GameObject.valueOf(item);
        isFirst = false;
        islast = false;
        this.position = position;

    }

    public GameObject getGameItem() {
        return gameItem;
    }

    public void setGameItem(GameObject gameItem) {
        this.gameItem = gameItem;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
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


}
