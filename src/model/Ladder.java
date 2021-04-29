package model;

public class Ladder {
    //Relationship.
    private  Box base;
    private  Box top;

    /**
     * Instantiates a new Ladder.
     */
    public Ladder() {

    }

    //Getters and Setters

    public Box getBase() {
        return base;
    }

    public void setBase(Box base) {
        this.base = base;
    }

    public Box getTop() {
        return top;
    }

    public void setTop(Box top) {
        this.top = top;
    }
}

