package ui;
public class Main {

    Menu theMenu;

    public Main(){
        welcome();
        theMenu = new Menu();
    }

    public void welcome(){
        System.out.println("-----------------------------------------");
        System.out.println("               Welcome To              ");
        System.out.println("        Snakes And Ladders Game        ");
        System.out.println("-----------------------------------------");
    }

    public static void main(String[] args) {
        Main ppal= new Main();
    }


}
