package model;

public class SnakesAndLaddersGame {
    private Box board;
    private int boardSize;
    private int currentSnakes;
    private int currentLadders;
     //TODO: verificar condiciones para inicio o fin de serpiente y escalera
    //TODO: verificar que no hayan inicio-fin en la misma fila
    //TODO: hacer metodo para el avanze del juego
    public SnakesAndLaddersGame() {

    }

    public void startGame(int n , int m, int snakes, int ladders ){
        setBoardSize(n*m);
        setCurrentSnakes(snakes);
        setCurrentLadders(ladders);
        int position =1;
        crateBoard(position, 0, 0);

    }



    public void crateBoard(int position, int createdSnakes , int createdLadders){
        int itemToadd= (int) (Math.random() * 4)+1;
        String add="NONE";
        if(!(createdLadders==currentLadders) || !(createdSnakes==currentSnakes)){
            switch (itemToadd){
                case 1 : if(!(createdLadders==currentLadders)){
                                 add = "HEAD";
                                createdSnakes+=1;
                } break;
                case 2: if(!(createdLadders==currentLadders)) {
                    add = "TAIL";
                    createdLadders +=1;
                } break;
                case 3: if(!(createdLadders==currentLadders)){
                    add = "BASE";
                    createdSnakes+=1;
                } break;
                case 4: if(!(createdLadders==currentLadders)) {
                    add = "TOP";
                    createdLadders +=1;
                }break;
                default: add = "NONE";
            }
        }

        if (position == boardSize){
            Box aux = findLast(board);
            Box last = new Box(position);
            last.setGameItem(GameObject.NONE);
            aux.setNext(last); //caso base
        }else if(position == 1){
            board = new Box(add);
            crateBoard(position+1,createdSnakes,createdLadders);
        }else{
            if(board.getNext()==null){
                board.setNext(new Box(add,position));
            }else{
                Box aux = findLast(board);
                aux.setNext(new Box(add,position));
            }
            crateBoard(position+1,createdSnakes,createdLadders);
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

    public void setCurrentSnakes(int currentSnakes) {
        this.currentSnakes = currentSnakes;
    }

    public void setCurrentLadders(int currentLadders) {
        this.currentLadders = currentLadders;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }
}
