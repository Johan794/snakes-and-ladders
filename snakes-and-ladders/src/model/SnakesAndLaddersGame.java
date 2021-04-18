package model;

public class SnakesAndLaddersGame {
    private Box board;
    private int players;
    private int boardSize;
     //TODO: verificar las condieciones de inicio o fin de una serpiente o escalera
    public SnakesAndLaddersGame() {


    }

    public void startGame(int n , int m, String inputs){
        setPlayers(inputs.length());
        setBoardSize(n*m);
        int position =1;
        crateBoard(position);

    }


    public void crateBoard(int position){
        int itemToadd= (int) (Math.random() * 3)+1;
        String add;
        switch (itemToadd){
            case 1 : add = "SNAKE";
                break;
            case 2: add = "LADDER";
                break;
            default: add = "NONE";
        }
        if (position == boardSize){
            Box aux = findLast(board);
            Box last = new Box(position);
            aux.setNext(last); //caso base
        }else if(position == 1){
            board = new Box(add);
            crateBoard(position+1);
        }else{
            if(board.getNext()==null){
                board.setNext(new Box(add,position));
            }else{
                Box aux = findLast(board);
                aux.setNext(new Box(add,position));
            }
            crateBoard(position+1);
        }
    }

    public Box findLast(Box board){
        Box last = board;
        if(!(last.getNext()==null)){
            last = last.getNext();
            findLast(last);
        }

        return last;

    }






    public void setPlayers(int players) {
        this.players = players;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }
}
