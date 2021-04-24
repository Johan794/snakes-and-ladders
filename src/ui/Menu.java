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
        //game.startGame();
        //TODO Un submenu dado que el jugador tendra opciones. -Camilo


    }

    public String seeBoard(int rows , int columns){
        return seeBoard(columns,rows*columns,1,"",game.getLastBox());
    }

    private String seeBoard(int cols, int i, int lineBreak,String out, Box last){
        if(i==1){
            out+= "["+i+last.getItemSymbol()+' '+last.getPlayer()+"]";
            return out;
        }else{
            if(lineBreak!=cols){
                if(Character.isDigit(last.getItemSymbol())){
                    out+= "["+i+ANSI_GREEN+last.getItemSymbol()+ANSI_RESET+' '+last.getPlayer()+"]"+" ";
                    return seeBoard(cols,i-1,lineBreak+1,out,last.getPrevious());
                }else if(last.getItemSymbol()!=' '){
                    out+= "["+i+ANSI_RED+last.getItemSymbol()+ANSI_RESET+' '+last.getPlayer()+"]"+" ";
                    return seeBoard(cols,i-1,lineBreak+1,out,last.getPrevious());
                }else {
                    out+= "["+i+last.getItemSymbol()+' '+last.getPlayer()+"]"+" ";
                    return seeBoard(cols,i-1,lineBreak+1,out,last.getPrevious());
                }

            }else{
                if(Character.isDigit(last.getItemSymbol())){
                    out+= "["+i+ANSI_GREEN+last.getItemSymbol()+ANSI_RESET+' '+last.getPlayer()+"]"+"\n";
                    lineBreak =1;
                    return seeBoard(cols,i-1,lineBreak,out,last.getPrevious());
                }else if(last.getItemSymbol()!=' '){
                    out+= "["+i+ANSI_RED+last.getItemSymbol()+ANSI_RESET+' '+last.getPlayer()+"]"+"\n";
                    lineBreak =1;
                    return seeBoard(cols,i-1,lineBreak,out,last.getPrevious());
                }else {
                    out+= "["+i+last.getItemSymbol()+' '+last.getPlayer()+"]"+"\n";
                    lineBreak =1;
                    return seeBoard(cols,i-1,lineBreak,out,last.getPrevious());
                }

            }
        }
    }


}
