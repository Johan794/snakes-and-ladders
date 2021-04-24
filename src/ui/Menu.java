package ui;

import model.Box;
import model.SnakesAndLaddersGame;

import java.util.Scanner;

public class Menu {
    Scanner sc = new Scanner(System.in);
    SnakesAndLaddersGame game = new SnakesAndLaddersGame();

    public Menu() {
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
        //TODO: Hacer el metodo, unirlo con model - Camilo
        //TODO Un submenu dado que el jugador tendra opciones. -Camilo


    }

    public String seeBoard(int rows , int columns){
        return seeBoard(columns,rows*columns,0,"",game.getLastBox());
    }

    //TODO: alta que imprima las serpientes y escaleras - Johan
    private String seeBoard(int cols, int i, int lineBreak,String out, Box last){
        if(i==1){
            out+= "["+i+"]";
            return out;
        }else{
            if(lineBreak!=cols){
                out+= "["+i+"]"+" ";
                return seeBoard(cols,i-1,lineBreak+1,out,last);
            }else{
                out+="\n";
                lineBreak =0;
                return seeBoard(cols,i-1,lineBreak,out,last);
            }
        }
    }


}
