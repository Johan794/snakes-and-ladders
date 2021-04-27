package model;


public class SnakesAndLaddersGame {
    private Box board;
    private Box lastBox;
    private int boardSize;
    private int currentSnakes;
    private int currentLadders;
    private int rows;
    private int columns;
    public static final String SNAKES = "ABCDEFGHIJHKLNOPQRSRWXYZ";
    //public static final String LADDERS = "1234567890";

    private Player rootPlayer;
    //TODO: hacer metodo para el avanze del juego - Camilo
    public SnakesAndLaddersGame() {

    }

    public void startGame(int n , int m, int snakes, int ladders){
        setBoardSize(n*m);
        setCurrentSnakes(snakes);
        setCurrentLadders(ladders);
        int position =1;
        createBoard(position);
        setLastBox(findLast(board));
        Box current = lastBox;
        //current = current.getPrevious();
        setSnakes(0);
        setLadders(0);
        connectSnakes(snakes,current,0);
        connectLadders(ladders,board,current,1);
        setRows(n);
        setColumns(m);


    }

    public void createBoard(int position){
        if (position == boardSize){
            Box aux = findLast(board);
            Box last = new Box(position);
            last.setGameItem(GameItem.NONE);
            aux.setNext(last); //caso base
            last.setPrevious(aux);
            last.setIslast(true);
        }else if(position == 1){
            board = new Box();
            createBoard(position+1);
        }else{
            if(board.getNext()==null){
                board.setNext(new Box(position));
                board.getNext().setGameItem(GameItem.NONE);
                board.getNext().setPrevious(board);

            }else{
                Box aux = findLast(board);
                Box newBox =new Box(position);
                aux.setNext(newBox);
                newBox.setPrevious(aux);
                newBox.setGameItem(GameItem.NONE);

            }
            createBoard(position+1);
        }
    }

    public void setSnakes(int createdSnakes){
        if(currentSnakes!=createdSnakes){
            setHead(false);
            setTail(false);
            setSnakes(createdSnakes+1);

        }

    }

    private void setHead(boolean added){
        if(!added){
            int position = (int)((Math.random())*boardSize)+1;
            Box newBox = search(position);
            if(!(newBox.isFirst()) && !(newBox.isIslast())){
                if(newBox.getGameItem().equals(GameItem.NONE)){
                    //System.out.println(newBox.getPosition()+" beberia tener "+add);
                    newBox.setGameItem(GameItem.HEAD);
                    setHead(true);
                    //System.out.println(newBox.getPosition()+" Tiene "+newBox.getGameItem());
                }else {
                    setHead(false);
                }
            }else {
                setHead(false);
            }
        }


    }

    private void setTail(boolean added){
        if(!added){
            int position = (int)((Math.random())*boardSize)+1;
            Box newBox = search(position);
            if(!(newBox.isFirst()) && !(newBox.isIslast())){
                if(newBox.getGameItem().equals(GameItem.NONE)){
                    //System.out.println(newBox.getPosition()+" beberia tener "+add);
                    newBox.setGameItem(GameItem.TAIL);
                    setHead(true);
                    //System.out.println(newBox.getPosition()+" Tiene "+newBox.getGameItem());
                }else {
                    setHead(false);
                }
            }else {
                setHead(false);
            }
        }


    }

    //separar el agregado de etiquetas
    public void setLadders(int createdLadders){
        if((currentLadders)!=createdLadders){
            setBase(false);
            setTop(false);
            setLadders(createdLadders+1);
        }

    }

    private void setBase(boolean added){
        if(!added){
            int position = (int)((Math.random())*boardSize)+1;
            //System.out.println("Numero generado: "+position);
            Box newBox = search(position);
            if(!(newBox.isFirst()) && !(newBox.isIslast())){
                if(newBox.getGameItem().equals(GameItem.NONE)){
                    newBox.setGameItem(GameItem.BASE);
                    //System.out.println(newBox.getPosition()+" y Tiene "+newBox.getGameItem());
                    setBase(true);
                }else {
                    setBase(false);
                }
            }else {
                setBase(false);
            }
        }
    }

    private void setTop(boolean added){
        if(!added){
            int position = (int)((Math.random())*boardSize)+1;
            //System.out.println("Numero generado: "+position);
            Box newBox = search(position);
            if(!(newBox.isFirst()) && !(newBox.isIslast())){
                if(newBox.getGameItem().equals(GameItem.NONE)){
                    newBox.setGameItem(GameItem.TOP);
                    //System.out.println(newBox.getPosition()+" y Tiene "+newBox.getGameItem());
                    setTop(true);
                }else{
                    setTop(false);
                }
            }else {
                setTop(false);
            }
        }
    }

    public Box search(int position){
        //System.out.println("la que se busca pri "+position);
          return search(board,position);
    }

    private Box search(Box current,int position){
        if((current != null) && (current.getPosition() == position)){
            //System.out.println("numero 1 "+current.getPosition());
            return current;
        } else{
            //System.out.println("numero 2: "+current.getPosition());
            return search(current.getNext(),position);
        }
    }

    public void connectSnakes(int currentSnakes,Box current, int symbolIndex){
        if(currentSnakes!=0 && current!=null){
            if(current.getGameItem().equals(GameItem.HEAD) && current.getSnake()==null){
                Snake newSnake = new Snake();
                current.setSnake(newSnake);
                current.setItemSymbol(Character.toString(SNAKES.charAt(symbolIndex)));
                newSnake.setHead(current);
                Box tail = findTail(board,false);
                tail.setItemSymbol(Character.toString(SNAKES.charAt(symbolIndex)));
                newSnake.setTail(tail);
                tail.setSnake(newSnake);
                connectSnakes(currentSnakes-1,current.getPrevious(), symbolIndex+1);
            }else{
                connectSnakes(currentSnakes,current.getPrevious(),symbolIndex);
            }

        }

    }

    //encuentra la primera cola de serpiente
    private Box findTail(Box board, boolean out){
        if(out!=true){
            if(board.getGameItem().equals(GameItem.TAIL) && board.getSnake()==null){
                return findTail(board,true);
            }else{
                return findTail(board.getNext(), false);
            }
        }
        return board;
    }

    public void connectLadders(int currentLadders , Box current, Box last, int symbolIndex){
        if(currentLadders!=0 && current!=null){
            if(current.getGameItem().equals(GameItem.BASE) && current.getLadder()==null){
                Ladder newLadder = new Ladder();
                newLadder.setBase(current);
                Box top = findTop(last,false);
                newLadder.setTop(top);
                current.setLadder(newLadder);
                top.setLadder(newLadder);
                current.setItemSymbol(String.valueOf(symbolIndex) );
                top.setItemSymbol(String.valueOf(symbolIndex));
                System.out.println("posicion de la base: "+newLadder.getBase().getPosition()+" posicion de la parte de arriba: "+newLadder.getTop().getPosition());
                connectLadders(currentLadders-1,current.getNext(),last, symbolIndex+1);
            }else {
                connectLadders(currentLadders,current.getNext(),last, symbolIndex);
            }
        }

    }

    private Box findTop(Box current, boolean out){
        if(out!=true){
            if((current != null) && (current.getGameItem().equals(GameItem.TOP)) && current.getLadder()==null ){
                return findTop(current,true);
            }else if(current!=null){
                return findTop(current.getPrevious(), out);
            }
        }
        return current;
    }

    public Box findLast(Box board){
        if (board.getNext() != null) {
            //System.out.println(board.getPosition());
            return findLast(board.getNext());
        } else{
           // System.out.println(board.getPosition());
            return board;
        }
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
      //  System.out.println(lastBox.getPosition());
        return lastBox;
    }

    public void setLastBox(Box lastBox){
        this.lastBox = lastBox;
    }

    public Player getRootScore() {
        return rootPlayer;
    }

    public void setRootScore(Player rootPlayer) {
        this.rootPlayer = rootPlayer;
    }

    public void printList(){
        System.out.println("Tamaño del tablero: "+boardSize);
        if(board!=null){
            if(board.getNext()!=null){
                System.out.println("Numero: "+board.getPosition()+" Item: "+board.getGameItem());
                Box next = board.getNext();
                int i=1;
                while (i<boardSize && next !=null){
                    System.out.println("Numero: "+next.getPosition()+" Item: "+next.getGameItem());
                    next = next.getNext();
                }
            }else{
                System.out.println("Numero: "+board.getPosition()+" Item: "+board.getGameItem());
            }

        }

    }


   // public String getPlayers(){ return  }

}
