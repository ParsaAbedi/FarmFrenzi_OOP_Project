package main;

import main.menu.Menu;
import main.menu.StartMenu;
import java.util.Scanner;

public class Main {
    public static void main(String[]args){
        StartMenu startMenu = new StartMenu();
        Menu.setScanner(new Scanner(System.in));
        startMenu.show();
        startMenu.execute();
    }
}

