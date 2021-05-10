package ui;
/**
 *This is a software about the famous game snakes and ladders.
 * @author Camilo González.
 * @author Johan Ricardo.
 * @since April 2021
 * @version 1.0
 */
public class Main {
    private Menu theMenu;
    public Main(){
        welcome();
        theMenu = new Menu();
    }

    public void welcome(){
        System.out.println("-----------------------------------------");
        System.out.println("               Welcome To                ");
        System.out.println("         Snakes And Ladders Game         ");
        System.out.println("-----------------------------------------");


    }

    public static void main(String[] args) {
        Main ppal= new Main();
    }

    /**
     * ¿Qué falta por hacer?:
     *  TODO: ESTE ES EL MÁS IMPORTANTE , PROBAR LAS COSAS Y SABER SI FUNCIONAN Y SI CUMPLEN LAS CONDICIONES DEL ENUNCIADO
     */



}
