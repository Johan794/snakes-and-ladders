package ui;

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
                //TODO: Crear el metodo para mostrar el tablero
                myMenu();
                break;
            case 3:
                System.out.println("Bye!");
                sc.close();
                break;
        }
    }

    public void playGame(){
        //TODO: Hacer el metodo, unirlo con model.
        //Un submenu dado que el jugador tendra opciones.
    }
}
