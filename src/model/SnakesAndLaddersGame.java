package model;


public class SnakesAndLaddersGame {
    private Box board;
    private Box lastBox;
    private int boardSize;
    private int currentSnakes;
    private int currentLadders;
    private int rows;
    private int columns;
    private Score rootScore;
    //TODO: hacer metodo para el avanze del juego - Camilo
    public SnakesAndLaddersGame() {

    }

    public void startGame(int n , int m, int snakes, int ladders ){
        setBoardSize(n*m);
        setCurrentSnakes(snakes);
        setCurrentLadders(ladders);
        int position =1;
        crateBoard(position, 0, 0);
        Box current = findLast(board);
        current = current.getPrevious();
        connectSnakes(snakes,current);
        connectLadders(ladders,board,current);

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
            last.setGameItem(GameItem.NONE);
            aux.setNext(last); //caso base
            last.setPrevious(aux);
        }else if(position == 1){
            board = new Box();
            crateBoard(position+1,createdSnakes,createdLadders);
        }else{
            if(board.getNext()==null){
                board.setNext(new Box(add,position));
            }else{
                Box aux = findLast(board);
                Box newBox =new Box(add,position);
                aux.setNext(newBox);
                newBox.setPrevious(aux);
            }
            crateBoard(position+1,createdSnakes,createdLadders);
        }
    }

    public void connectSnakes(int currentSnakes,Box current){
        if(currentSnakes!=0 && current!=null){
            if(current.getGameItem().equals(GameItem.HEAD)){
                Snake newSnake = new Snake();
                current.setSnake(newSnake);
                newSnake.setHead(current);
                newSnake.setTail(findTail(board));
                connectSnakes(currentSnakes-1,current.getPrevious());
            }else{
                connectSnakes(currentSnakes,current.getPrevious());
            }

        }

    }

    //encuentra la primera cola de serpiente
    private Box findTail(Box board){
        Box tail =null;
        if(board!=null){
            if (board.getGameItem().equals(GameItem.TAIL)){
                tail = board;

            }else{
                findTail(board.getNext());
            }
        }

        return tail;
    }

    public void connectLadders(int currentLadders , Box current, Box last){
        if(currentLadders!=0 && current!=null){
            if(current.getGameItem().equals(GameItem.BASE)){
                Ladder newLadder = new Ladder();
                newLadder.setBase(current);
                newLadder.setTop(findTop(last));
                current.setLadder(newLadder);
                connectLadders(currentLadders-1,current.getNext(),last);
            }else {
                connectLadders(currentLadders,current.getNext(),last);
            }
        }

    }

    private Box findTop(Box current){
        Box top = null;
        if(current!=null){
            if(current.getGameItem().equals(GameItem.TOP)){
                top=current;
            }else{
               findTop(current.getPrevious());
            }
        }
        return top;
    }


    public Box findLast(Box board){
        Box last = board;
        if(!(last.getNext()==null)){
            last = last.getNext();
            findLast(last);
        }
        return last;

    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getRows() {
        return rows;
    }


    public void setRows(int rows) {
        this.rows = rows;
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

    public Box getBoard() {
        return board;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public int getCurrentSnakes() {
        return currentSnakes;
    }

    public int getCurrentLadders() {
        return currentLadders;
    }

    public Box getLastBox() {
        return lastBox;
    }


    public Score getRootScore() {
        return rootScore;
    }

    public void setRootScore(Score rootScore) {
        this.rootScore = rootScore;
    }
}
