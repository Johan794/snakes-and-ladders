package ui;

import model.Box;
import model.SnakesAndLaddersGame;
import java.util.Scanner;

public class Menu {
     private Scanner sc;
     private SnakesAndLaddersGame game;
     public static final String ANSI_RED = "\u001B[31m"; //para las serpientes
     public static final String ANSI_GREEN = "\u001B[32m"; //para las escaleras
    public static final String ANSI_RESET = "\u001B[0m"; //para que solo el char quede con el color
    public static final String CARACTER = "*!OX%$#+&";

    public Menu() {
        game = new SnakesAndLaddersGame();
        sc = new Scanner(System.in);
        myMenu();
    }

    public void myMenu(){
        System.out.println("Please enter a option: \n\n(1) Play Game \n(2) See Board \n(3) Exit.");
        String option = sc.nextLine();
        switch (option){
            case "1":
                playGame();
                myMenu();
                break;
            case "2":
               game.printListPLayer();
               System.out.println(seeBoard(game.getRows(),game.getColumns()));
                myMenu();
                break;
            case "3":
                System.out.println("Bye!");
                sc.close();
                break;
        }
    }

    public void playGame(){
        //5 4 3 2 #%* (tablero 5x4, 3 serpientes, 2 escaleras y 3 jugadores)
        //sc.nextLine();
        String data = sc.nextLine();


        String[] parts = data.split(" ");
        int n = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);
        int snakes = Integer.parseInt(parts[2]);
        int ladders = Integer.parseInt(parts[3]);
        int players = Integer.parseInt(parts[4]); //Numero de jugadores

        playersGame(players,0);

        //int n , int m, int snakes, int ladders, String players

        game.startGame(n,m,snakes,ladders);



    }

    public String seeBoard(int rows , int columns){
      return seeBoard(columns,rows*columns,1,"", game.getLastBox());
    }

    private String seeBoard(int cols, int i, int lineBreak,String out, Box last){
        //System.out.println("Imprimiendo la casilla #"+i);
        if(i==1){
            out+= "[\t"+i+last.getItemSymbol()+' '+last.getPlayer()+"\t]";
            return out;
        }else{
            if(lineBreak!=cols){
                    if (last.isnumeric()) {

                        //System.out.println(i+" tiene parte de una escalera");
                        out += "[\t" + i + ANSI_GREEN + last.getItemSymbol() + ANSI_RESET + ' ' + last.getPlayer() + "]";
                      //System.out.println(i);
                        //out+="\n";
                        return seeBoard(cols, i - 1, lineBreak + 1, out, last.getPrevious());

                    } else if (!last.getItemSymbol().equals(" ")) {
                        out += "[\t" + i + ANSI_RED + last.getItemSymbol() + ANSI_RESET + ' ' + last.getPlayer() + "]";
                        //out+="\n";
                        return seeBoard(cols, i - 1, lineBreak + 1, out, last.getPrevious());
                    } else {
                        out += "[\t" + i + last.getItemSymbol() + ' ' + last.getPlayer() + "]";
                        //out+="\n";
                       // System.out.println(i);
                        return seeBoard(cols, i - 1, lineBreak + 1, out, last.getPrevious());
                    }
            }else{
                if(last.isnumeric()){
                    //System.out.println(i+" tiene parte de una escalera");
                    out+= "[\t"+i+ANSI_GREEN+last.getItemSymbol()+ANSI_RESET+' '+last.getPlayer()+"]"+"\n";
                    lineBreak =1;
                    //System.out.println(i);
                    return seeBoard(cols,i-1,lineBreak,out,last.getPrevious());
                }else if(!last.getItemSymbol().equals(" ")){
                    out+= "[\t"+i+ANSI_RED+last.getItemSymbol()+ANSI_RESET+' '+last.getPlayer()+"]"+"\n";
                    lineBreak =1;
                    //System.out.println(i);
                    return seeBoard(cols,i-1,lineBreak,out,last.getPrevious());
                }else {
                    out+= "[\t"+i+last.getItemSymbol()+' '+last.getPlayer()+"\t]"+"\n";
                    lineBreak =1;
                    //System.out.println(i);
                    return seeBoard(cols,i-1,lineBreak,out,last.getPrevious());
                }

            }
        }
    }

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
