package model;

public class Snake {

    //Relationship
    private Box head;
    private Box tail;

    /**
     * Constructor.
     * Instantiates a new Snake.
     *
     */
    public Snake() {

    }

    //Getters and Setters.

    public Box getHead() {

        return head;
    }

    public void setHead(Box head) {

        this.head = head;
    }

    public Box getTail() {
        return tail;
    }

    public void setTail(Box tail) {

        this.tail = tail;
    }

}

