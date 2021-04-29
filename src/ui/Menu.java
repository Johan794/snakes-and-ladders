package ui;

import model.Box;
import model.Player;
import model.SnakesAndLaddersGame;
import java.util.Scanner;

public class Menu {
     private Scanner sc;
     private SnakesAndLaddersGame game;
     public static final String ANSI_RED = "\u001B[31m"; //para las serpientes
     public static final String ANSI_GREEN = "\u001B[32m"; //para las escaleras
     public static final String ANSI_RESET = "\u001B[0m"; //para que solo el char quede con el color

    public Menu() {
        game = new SnakesAndLaddersGame();
        sc = new Scanner(System.in);
        myMenu();
    }

    public void myMenu(){
        System.out.println("Please enter a option: \n\n(1) Play Game \n(2) See Board \n(3) Exit.");
        int option = sc.nextInt();
        switch (option){
            case 1:
                playGame();
                myMenu();
                break;
            case 2:
               System.out.println(seeBoard(game.getRows(),game.getColumns()));
                myMenu();
                break;
            case 3:
                System.out.println("Bye!");
                sc.close();
                break;
        }
    }

    public void playGame(){
        //5 4 3 2 #%* (tablero 5x4, 3 serpientes, 2 escaleras y 3 jugadores)
        sc.nextLine();
        String data = sc.nextLine();
        String[] parts = data.split(" ");
        int n = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);
        int snakes = Integer.parseInt(parts[2]);
        int ladders = Integer.parseInt(parts[3]);
        //String players = parts[4];
        //int n , int m, int snakes, int ladders, String players
        game.startGame(n,m,snakes,ladders);
        //move(players , game.getFirstPlayer());




    }

    public void move(int players, Player current){
        if(players!=0 && current!=null){
            int dice;
            System.out.println("Turno del jugador: "+current.getPlayer());
            System.out.println("Presione enter");
            String responce = sc.nextLine();
            if(responce.equals("")){
                char nickname = current.getPlayer().charAt(0);
                dice =(int)(Math.random()*6)+1;
                current.setPlayerScore(dice);
                if(game.move(nickname,dice)){
                    System.out.println("El jugador: "+current.getPlayer()+" ha ganado!!");
                }else{
                    //current.getNext();
                    move(players-1,current);
                }
            }else {
                System.out.println("Se supone que es otra accion pero estamos probando el move");
            }
        }
    }

    public String seeBoard(int rows , int columns){
      return seeBoard(columns,rows*columns,1,"", game.getLastBox());
    }

    private String seeBoard(int cols, int i, int lineBreak,String out, Box last){
        //System.out.println("Imprimiendo la casilla #"+i);
        if(i==1){
            out+= "["+i+last.getItemSymbol()+' '+last.getPlayer()+"]";
            return out;
        }else{
            if(lineBreak!=cols){
                    if (last.isnumeric()) {
                        //System.out.println(i+" tiene parte de una escalera");
                        out += "[" + i + ANSI_GREEN + last.getItemSymbol() + ANSI_RESET + ' ' + last.getPlayer() + "]";
                        //System.out.println(i);
                        //out+="\n";
                        return seeBoard(cols, i - 1, lineBreak + 1, out, last.getPrevious());
                    } else if (!last.getItemSymbol().equals(" ")) {
                        out += "[" + i + ANSI_RED + last.getItemSymbol() + ANSI_RESET + ' ' + last.getPlayer() + "]";
                        //out+="\n";
                        return seeBoard(cols, i - 1, lineBreak + 1, out, last.getPrevious());
                    } else {
                        out += "[" + i + last.getItemSymbol() + ' ' + last.getPlayer() + "]";
                        //out+="\n";
                       // System.out.println(i);
                        return seeBoard(cols, i - 1, lineBreak + 1, out, last.getPrevious());
                    }
            }else{
                if(last.isnumeric()){
                    //System.out.println(i+" tiene parte de una escalera");
                    out+= "["+i+ANSI_GREEN+last.getItemSymbol()+ANSI_RESET+' '+last.getPlayer()+"]"+"\n";
                    lineBreak =1;
                    //System.out.println(i);
                    return seeBoard(cols,i-1,lineBreak,out,last.getPrevious());
                }else if(!last.getItemSymbol().equals(" ")){
                    out+= "["+i+ANSI_RED+last.getItemSymbol()+ANSI_RESET+' '+last.getPlayer()+"]"+"\n";
                    lineBreak =1;
                    //System.out.println(i);
                    return seeBoard(cols,i-1,lineBreak,out,last.getPrevious());
                }else {
                    out+= "["+i+last.getItemSymbol()+' '+last.getPlayer()+"]"+"\n";
                    lineBreak =1;
                    //System.out.println(i);
                    return seeBoard(cols,i-1,lineBreak,out,last.getPrevious());
                }

            }
        }
    }


}
