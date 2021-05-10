package ui;

import model.Box;
import model.Player;
import model.SnakesAndLaddersGame;
import java.util.Scanner;

/**
 * The type Menu.
 */
public class Menu {
     private Scanner sc;
     private SnakesAndLaddersGame game;
     public static final String ANSI_RED = "\u001B[31m"; //Para las serpientes
     public static final String ANSI_GREEN = "\u001B[32m"; //Para las escaleras
    public static final String ANSI_RESET = "\u001B[0m"; //Para que solo el char quede con el color
    public static final String CARACTER = "*!OX%$#+&";

    /**
     * Constructor.
     * Instantiates a new Menu.
     */
    public Menu() {
        game = new SnakesAndLaddersGame();
        sc = new Scanner(System.in);
        myMenu();
    }

    /**
     * My menu.
     */
    public void myMenu(){
        System.out.println("Please enter a option: \n\n(1) Play Game \n(2) See scores \n(3) Exit.");
        String option = sc.nextLine();
        switch (option){
            case "1":
                System.out.println("Please type the following format for starting the game: ");
                System.out.println("rows columns snakes ladders amount of player");
                System.out.println("At the same line: ");
                System.out.println("Example: 5 5 4 3 6");
                playGame();
                game.setFirts(null);
                game.setBoard(null);
                myMenu();
                break;
            case "2":
                System.out.println(">>>>>>>>>>>>>>>> Leader board:");
                game.inOrder();
                myMenu();
                break;
            case "3":
                System.out.println("Bye!");
                sc.close();
                break;
        }
    }

    /**
     * Play game.
     */
    public void playGame(){
        //5 4 3 2 #%* (tablero 5x4, 3 serpientes, 2 escaleras y 3 jugadores)

        String data = sc.nextLine();
        String[] parts = data.split(" ");
        int n = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);
        int snakes = Integer.parseInt(parts[2]);
        int ladders = Integer.parseInt(parts[3]);
        int players = Integer.parseInt(parts[4]); //Numero de jugadores

        game.setCurrentPlayers(players);
        playersGame(players,0);

        //int n , int m, int snakes, int ladders, String players

        game.startGame(n,m,snakes,ladders);
        System.out.println(seeBoard(game.getRows(), game.getColumns() , ""));
        System.out.println("\n");
        move(players,game.getFirts());

    }

    /**
     * Move.
     *
     * @param players the players
     * @param current the current
     */
    public void move(int players , Player current){
        if(players!=0 && current!=null){
            int dice =(int) (Math.random()*6)+1;
            System.out.println("It is "+current.getPlayer()+"'s turn");
            System.out.println("Please press intro:");
            String response = sc.nextLine();
            if(response.equals("")){
                int releases =0;
                System.out.println("The player "+current.getPlayer()+" has rolled the dice and got "+dice);
                releases += current.getPlayerScore();
                releases+=1;
                current.setPlayerScore(releases);
                if(game.move(current.getPlayer(),dice)){
                    System.out.println("\n");
                    System.out.println(seeBoard(game.getRows(), game.getColumns() , "2"));
                    System.out.println("\n");
                    System.out.println("The player "+current.getPlayer()+" has won the game with "+current.getPlayerScore()+" moves!!");
                    System.out.print("Please type your name: ");
                    String name = sc.nextLine();
                    game.addWinner(current.getPlayer(),name);
                    System.out.println("GG ;)");
                    System.out.println("\033[H\033[2J");
                }else {
                    System.out.println("\n");
                    System.out.println(seeBoard(game.getRows(), game.getColumns(),"2" ));
                    System.out.println("\n");
                    move(players-1,current.getNext());
                }
            }else if(response.equals("num")){
                System.out.println(seeBoard(game.getRows(),game.getColumns(), "3"));
                System.out.println("\n");
                move(players,current);
            }else if(response.equals("simul")){
                moveSimul(players,current);
            }
        }else{
            move(game.getCurrentPlayers(), game.getFirts());
        }

    }

    /**
     * Move simul.
     *
     * @param currentPlayers the current players
     * @param current        the current
     */
    public void moveSimul(int currentPlayers , Player current){
        if(currentPlayers!=0 && current!=null) {
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                System.out.println(e);
            }
            int dice = (int) (Math.random() * 6) + 1;
            System.out.println("It is " + current.getPlayer() + "'s turn");
            int releases =0;
            System.out.println("The player "+current.getPlayer()+" has rolled the dice and got "+dice);
            releases += current.getPlayerScore();
            releases+=1;
            current.setPlayerScore(releases);
            if(game.move(current.getPlayer(),dice)){
                System.out.println();
                System.out.println(seeBoard(game.getRows(), game.getColumns() , "2"));
                System.out.println("\n");
                System.out.println("The player "+current.getPlayer()+" has won the game with "+current.getPlayerScore()+" moves!!");
                System.out.print("Please type your name: ");
                String name = sc.nextLine();
                game.addWinner(current.getPlayer(),name);
                System.out.println("GG ;)");
                System.out.println("\033[H\033[2J");
            }else {
                System.out.println("\n");
                System.out.println(seeBoard(game.getRows(), game.getColumns(),"2" ));
                System.out.println("\n");
                moveSimul(currentPlayers-1,current.getNext());
            }
        }else{
            moveSimul(game.getCurrentPlayers(), game.getFirts());
        }
    }

    /**
     * See board string.
     *
     * @param rows    the rows
     * @param columns the columns
     * @return the string
     */
    public String seeBoard(int rows , int columns, String which){
        if(which.equals("")){
            return seeBoard(columns,rows*columns,1,"", game.getLastBox());
        }else if(which.equals("2")){
            return seeBoard2(columns,rows*columns,1,"", game.getLastBox());
        }else {
            return seeBoard3(columns,rows*columns,1,"", game.getLastBox());
        }

    }

    private String seeBoard(int cols, int i, int lineBreak,String out, Box last){
        if(i==1){
            out+= "[\t"+i+last.getItemSymbol()+"\t]";
            return out;
        }else{
            if(lineBreak!=cols){
                    if (last.isnumeric()) {
                        //System.out.println(i+" tiene parte de una escalera");
                        out += "[\t" + i + ANSI_GREEN + last.getItemSymbol() + ANSI_RESET + "\t]";
                      //System.out.println(i);
                        //out+="\n";
                        return seeBoard(cols, i - 1, lineBreak + 1, out, last.getPrevious());

                    } else if (!last.getItemSymbol().equals("")) {
                        out += "[\t" + i + ANSI_RED + last.getItemSymbol() + ANSI_RESET + "\t]";
                        //out+="\n";
                        return seeBoard(cols, i - 1, lineBreak + 1, out, last.getPrevious());
                    } else {
                        out += "[\t" + i + last.getItemSymbol()+ "\t]";
                        //out+="\n";
                       // System.out.println(i);
                        return seeBoard(cols, i - 1, lineBreak + 1, out, last.getPrevious());
                    }
            }else{
                if(last.isnumeric()){
                    //System.out.println(i+" tiene parte de una escalera");
                    out+= "[\t"+i+ANSI_GREEN+last.getItemSymbol()+ANSI_RESET+"\t]"+"\n";
                    lineBreak =1;
                    //System.out.println(i);
                    return seeBoard(cols,i-1,lineBreak,out,last.getPrevious());
                }else if(!last.getItemSymbol().equals("")){
                    out+= "[\t"+i+ANSI_RED+last.getItemSymbol()+ANSI_RESET+"\t]"+"\n";
                    lineBreak =1;
                    //System.out.println(i);
                    return seeBoard(cols,i-1,lineBreak,out,last.getPrevious());
                }else {
                    out+= "[\t"+i+last.getItemSymbol()+"\t]"+"\n";
                    lineBreak =1;
                    //System.out.println(i);
                    return seeBoard(cols,i-1,lineBreak,out,last.getPrevious());
                }

            }
        }
    }

    private String seeBoard2(int cols, int i, int lineBreak,String out, Box last){
        if(i==1){
            out+= "[\t"+last.getItemSymbol()+last.getPlayer()+"\t]";
            return out;
        }else{
            if(lineBreak!=cols){
                if (last.isnumeric()) {
                    out += "[\t" + ANSI_GREEN + last.getItemSymbol() + ANSI_RESET +last.getPlayer()+ "\t]";
                    return seeBoard2(cols, i - 1, lineBreak + 1, out, last.getPrevious());

                } else if (!last.getItemSymbol().equals("")) {
                    out += "[\t" + ANSI_RED + last.getItemSymbol() + ANSI_RESET +last.getPlayer()+"\t]";
                    return seeBoard2(cols, i - 1, lineBreak + 1, out, last.getPrevious());
                } else {
                    out += "[\t" +last.getItemSymbol()+last.getPlayer()+ "\t]";
                    return seeBoard2(cols, i - 1, lineBreak + 1, out, last.getPrevious());
                }
            }else{
                if(last.isnumeric()){
                    out+= "[\t"+ANSI_GREEN+last.getItemSymbol()+ANSI_RESET+last.getPlayer()+"\t]"+"\n";
                    lineBreak =1;
                    return seeBoard2(cols,i-1,lineBreak,out,last.getPrevious());
                }else if(!last.getItemSymbol().equals("")){
                    out+= "[\t"+ANSI_RED+last.getItemSymbol()+ANSI_RESET+last.getPlayer()+"\t]"+"\n";
                    lineBreak =1;
                    return seeBoard2(cols,i-1,lineBreak,out,last.getPrevious());
                }else {
                    out+= "[\t"+last.getItemSymbol()+last.getPlayer()+"\t]"+"\n";
                    lineBreak =1;
                    return seeBoard2(cols,i-1,lineBreak,out,last.getPrevious());
                }

            }
        }
    }
    private String seeBoard3(int cols, int i, int lineBreak,String out, Box last){
        if(i==1){
            out+= "[\t"+i+last.getItemSymbol()+last.getPlayer()+"\t]";
            return out;
        }else{
            if(lineBreak!=cols){
                if (last.isnumeric()) {
                    out += "[\t"+i+ANSI_GREEN + last.getItemSymbol()+ANSI_RESET+last.getPlayer()+"\t]";
                    return seeBoard3(cols, i - 1, lineBreak + 1, out, last.getPrevious());

                } else if (!last.getItemSymbol().equals("")) {
                    out += "[\t"+i+ANSI_RED + last.getItemSymbol() + ANSI_RESET + last.getPlayer()+"\t]";
                    return seeBoard3(cols, i - 1, lineBreak + 1, out, last.getPrevious());
                } else {
                    out += "[\t"+i+last.getItemSymbol()+last.getPlayer()+ "\t]";
                    return seeBoard3(cols, i - 1, lineBreak + 1, out, last.getPrevious());
                }
            }else{
                if(last.isnumeric()){
                    out+= "[\t"+i+ANSI_GREEN+last.getItemSymbol()+ANSI_RESET+last.getPlayer()+"\t]"+"\n";
                    lineBreak =1;
                    return seeBoard3(cols,i-1,lineBreak,out,last.getPrevious());
                }else if(!last.getItemSymbol().equals("")){
                    out+= "[\t"+i+ANSI_RED+last.getItemSymbol()+ANSI_RESET+last.getPlayer()+"\t]"+"\n";
                    lineBreak =1;
                    return seeBoard3(cols,i-1,lineBreak,out,last.getPrevious());
                }else {
                    out+= "[\t"+i+last.getItemSymbol()+last.getPlayer()+"\t]"+"\n";
                    lineBreak =1;
                    return seeBoard3(cols,i-1,lineBreak,out,last.getPrevious());
                }

            }
        }
    }

    /**
     * Players game.
     *
     * @param numPlayers the num players
     * @param k          the k
     */
    public void playersGame(int numPlayers , int k){
        char x ;
        String y;
        if(numPlayers != 0){
            x = CARACTER.charAt(k);
            y = Character.toString(x);
            game.addPlayer(y);
            playersGame(numPlayers-1,k+1);
        }
    }


}
