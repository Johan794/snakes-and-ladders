package model;


/**
 * The type Snakes and ladders game.
 */
public class SnakesAndLaddersGame {
    private Box board;
    private Box lastBox;
    private int currentPlayers;
    private int boardSize;
    private int currentSnakes;
    private int currentLadders;
    private int rows;
    private int columns;
    public static final String SNAKES = "ABCDEFGHIJHKLNOPQRSRWXYZ";

    private Player rootPlayer;

    private Player firts;



    //TODO: hacer metodo para el avanze del juego - Camilo

    /**
     * Instantiates a new Snakes and ladders game.
     */
    public SnakesAndLaddersGame() {

    }

    /**
     * Add player.
     *
     * @param element the element
     */
    public void addPlayer(String element){
        Player p = new Player(element);
        if(firts == (null)){
            firts = p;
        }else{
            addPlayer(firts,p);
        }
    }

    private void addPlayer(Player x,Player y){
        if(x.getNext()==null){
            x.setNext(y);
        }else{
            addPlayer(x.getNext(),y);
        }

    }


    /**
     * Start game.
     *
     * @param n       the n
     * @param m       the m
     * @param snakes  the snakes
     * @param ladders the ladders
     */
    public void startGame(int n , int m, int snakes, int ladders){
        setBoardSize(n*m);
        setCurrentSnakes(snakes);
        setCurrentLadders(ladders);
        int position =1;
        createBoard(position);
        setSnakes(0);
        setLadders(0);
        printList();
        setLastBox(findLast(board));
        Box current = findLast(board);
        //current = current.getPrevious();
        connectSnakes(snakes,current,0);
        connectLadders(ladders,board,current,1);
        setRows(n);
        setColumns(m);
        setCurrentPLayers(firts);

    }

    public void setCurrentPLayers(Player firts){
        if(firts!=null){
            String add = firts.getPlayer();
            add+= board.getPlayer();
            board.setPlayer(add);
            setCurrentPLayers(firts.getNext());
        }
    }

    public boolean move(String player , int dice ){
        Box boxPlayer = search(player);
        if(moveTo(boxPlayer,dice,player)){
            addWinner(player);
            return true;
        }else {
            String players;
            String aux;
            int playerToMove;
            Box currentBoxPlayer = search(player);
            //System.out.println("El jugador "+currentBoxPlayer.getPlayer()+" se encuentra en la casilla "+currentBoxPlayer.getPosition());
            players = currentBoxPlayer.getPlayer();
            playerToMove = players.indexOf(player);
            if(currentBoxPlayer.getGameItem().equals(GameItem.HEAD)){
                //System.out.println("Se encontró una serpiente asi que se movio");
                Snake toSnake = currentBoxPlayer.getSnake();
                aux=toSnake.getTail().getPlayer();
                aux+=player;
                toSnake.getTail().setPlayer(aux);
                String replace = players.replace(players.charAt(playerToMove), ' ');
                currentBoxPlayer.setPlayer(replace);
            }else if(currentBoxPlayer.getGameItem().equals(GameItem.BASE)){
                //System.out.println("Se encontro una escalera asi que se movio ");
                Ladder toLadder = currentBoxPlayer.getLadder();
                aux=toLadder.getTop().getPlayer();
                aux+=player;
                toLadder.getTop().setPlayer(aux);
                currentBoxPlayer.setPlayer(players.replace(players.charAt(playerToMove),' '));
            }

            return false;
        }


    }

    private boolean moveTo(Box boxPlayer , int to, String currentPlayer){
        String players;
        String aux;
        int playerToMove;
        Box next = boxPlayer.getNext();
        if(to!=0){
            players = boxPlayer.getPlayer();
            playerToMove = players.indexOf(currentPlayer);
            // System.out.println("Jugador que se mueve: "+currentPlayer);
            // System.out.println("Jugadores en la casilla: "+players);
            aux= next.getPlayer();
            aux+=currentPlayer;
            next.setPlayer(aux);
            //System.out.println("Jugador en la casilla siguiente: "+next.getPlayer());
            // System.out.println("Jugador movido "+players.replace(players.charAt(playerToMove),' '));
            boxPlayer.setPlayer(players.replace(players.charAt(playerToMove),' '));
            return moveTo(next,to-1,currentPlayer);
        }else {
            return next.isIslast();
        }

    }

    //lista enlazada de jugadores
    public void addWinner(String player){
        int moves;
        Player newPLayer = new Player(player);
        moves = newPLayer.getPlayerScore();
        newPLayer.setPlayerScore(moves*boardSize);
        if (rootPlayer==null){
            rootPlayer = newPLayer;
        }else{
            addWinner(rootPlayer,newPLayer);
        }

    }

    private void addWinner(Player current , Player newPlayer){
        if(newPlayer.getPlayerScore()<=current.getPlayerScore()){
            if(current.getLeft()==null){
                current.setLeft(newPlayer);
                newPlayer.setParent(current);
            }else{
                addWinner(current.getLeft(),newPlayer);
            }
        }else {
            if(current.getRight()==null){
                current.setRight(newPlayer);
                newPlayer.setParent(current);
            }else {
                addWinner(current.getRight(),newPlayer);
            }
        }

    }

    /**
     * Create board.
     *
     * @param position the position
     */
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

    /**
     * Set snakes.
     *
     * @param createdSnakes the created snakes
     */
    public void setSnakes(int createdSnakes){
        if(currentSnakes!=createdSnakes){
            setHead(false,(boardSize / 2));
            setTail(false, (boardSize / 2));
            setSnakes(createdSnakes + 1);

        }

    }

    private void setHead(boolean added, int headBorder){
        if(!added){
            int position = (int)((Math.random())*boardSize)+1;
            if(position<=headBorder){
                setHead(false,headBorder);
            }else {
                Box newBox = search(position);
                if(!(newBox.isFirst()) && !(newBox.isIslast())){
                    if(newBox.getGameItem().equals(GameItem.NONE)){
                        //System.out.println(newBox.getPosition()+" beberia tener "+add);
                        newBox.setGameItem(GameItem.HEAD);
                        setHead(true,headBorder);
                        //System.out.println(newBox.getPosition()+" Tiene "+newBox.getGameItem());
                    }else {
                        setHead(false,headBorder);
                    }
                }else {
                    setHead(false,headBorder);
                }
            }

        }


    }

    private void setTail(boolean added, int border){
        if(!added){
            int position = (int)((Math.random())*border)+1;
            if(position>=border){
                setTail(false,border);
            }else{
                Box newBox = search(position);
                if(!(newBox.isFirst()) && !(newBox.isIslast())){
                    if(newBox.getGameItem().equals(GameItem.NONE)){
                        //System.out.println(newBox.getPosition()+" beberia tener "+add);
                        newBox.setGameItem(GameItem.TAIL);
                        setTail(true, border);
                        //System.out.println(newBox.getPosition()+" Tiene "+newBox.getGameItem());
                    }else {
                        setTail(false, border);
                    }
                }else {
                    setTail(false, border);
                }
            }

        }


    }

    /**
     * Set ladders.
     *
     * @param createdLadders the created ladders
     */
//separar el agregado de etiquetas
    public void setLadders(int createdLadders){
        if((currentLadders)!=createdLadders){
            setBase(false,(boardSize/2));
            setTop(false, (boardSize/2));
            setLadders(createdLadders+1);

        }

    }

    private void setBase(boolean added, int border){
        if(!added){
            int position = (int)((Math.random())*border)+1;
            //System.out.println("Numero generado: "+position);
            if(position>=border){
                setBase(false,border);
            }else {
                Box newBox = search(position);
                if(!(newBox.isFirst()) && !(newBox.isIslast())){
                    if(newBox.getGameItem().equals(GameItem.NONE)){
                        newBox.setGameItem(GameItem.BASE);
                        //System.out.println(newBox.getPosition()+" y Tiene "+newBox.getGameItem());
                        setBase(true,border);
                    }else {
                        setBase(false,border);
                    }
                }else {
                    setBase(false,border);
                }
            }

        }
    }

    private void setTop(boolean added, int head){
        if(!added){
            int position = (int)((Math.random())*boardSize)+1;
            if(position<=head){
                setTop(false,head);
            }else {
                //System.out.println("Numero generado: "+position);
                Box newBox = search(position);
                if(!(newBox.isFirst()) && !(newBox.isIslast())){
                    if(newBox.getGameItem().equals(GameItem.NONE)){
                        newBox.setGameItem(GameItem.TOP);
                        //System.out.println(newBox.getPosition()+" y Tiene "+newBox.getGameItem());
                        setTop(true,head);
                    }else{
                        setTop(false,head);
                    }
                }else {
                    setTop(false,head);
                }
            }

        }
    }

    /**
     * Search box.
     *
     * @param position the position
     * @return the box
     */
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
    public Box search(String player){
        return seacrh(board,player);
    }

    private  Box seacrh(Box current , String player){
        if((current != null) && (current.getPlayer().contains(player))){
            //System.out.println("numero 1 "+current.getPosition());
            return current;
        } else{
            //System.out.println("numero 2: "+current.getPosition());
            return seacrh(current.getNext(),player);
        }

    }

    /**
     * Connect snakes.
     *
     * @param currentSnakes the current snakes
     * @param current       the current
     * @param symbolIndex   the symbol index
     */
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

    /**
     * Connect ladders.
     *
     * @param currentLadders the current ladders
     * @param current        the current
     * @param last           the last
     * @param symbolIndex    the symbol index
     */
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
                //System.out.println("posicion de la base: "+newLadder.getBase().getPosition()+" posicion de la parte de arriba: "+newLadder.getTop().getPosition());
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

    /**
     * Find last box.
     *
     * @param board the board
     * @return the box
     */
    public Box findLast(Box board){
        if (board.getNext() != null) {
            //System.out.println(board.getPosition());
            return findLast(board.getNext());
        } else{
           // System.out.println(board.getPosition());
            return board;
        }
    }

    //Getters and Setters.

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

    public void printListPLayer(){
       Player recorrer = firts;
        System.out.println();
        while(recorrer!=null){
            System.out.print("["+recorrer.getPlayer()+"]--->");
            recorrer = recorrer.getNext();
        }

    }

    public int getCurrentPlayers() {
        return currentPlayers;
    }

    public void setCurrentPlayers(int currentPlayers) {
        this.currentPlayers = currentPlayers;
    }

    public Player getFirts() {
        return firts;
    }

    public void setFirts(Player firts) {
        this.firts = firts;
    }
}
