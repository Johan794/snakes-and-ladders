package ui;
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
     * ¿Que falta por hacer?:
     *  TODO: Conectar Menu -> con estradas y acciones
     *  TODO: Hacer recursion para las entradas
     *  TODO: Hacer la dinamica de avanza del juego (con los atributos de la clase box)
     *  TODO: Hacer acciones con los saltos de linea por consola
     *  TODO: Hacer acciones de la entrada 'num' como dice el enunciado
     *  TODO: Hacer acciones de la entrada 'simul'
     *  TODO: Hacer el arbol binario ordenado segun el enunciado
     *  TODO: Un submenu dado que el jugador tendra opciones
     *  TODO: ESTE ES EL MÁS IMPORTANTE , PROBAR LAS COSAS Y SABER SI FUNCIONAN Y SI CUMPLEN LAS CONDICIONES DEL ENUNCIADO
     */



}
