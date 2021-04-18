package ui;
import model.SnakesAndLaddersGame;

import java.util.Scanner;

public class Main {
    private SnakesAndLaddersGame game;
    private Scanner sc;

    public Main(){
        sc = new Scanner(System.in);
        game = new SnakesAndLaddersGame();
    }
    public static void main(String[] args) {
        System.out.println("Este es el inicio de una nueva TI");
    }
}
