package main;

import animals.Bear;
import main.menu.Menu;
import main.menu.StartMenu;
import others.Manager;
import others.Mission;

import java.util.Scanner;

public class Main {
    public static Mission mission;
    public static void main(String[]args){
        StartMenu startMenu = new StartMenu();
       Menu.setScanner(new Scanner(System.in));
        startMenu.show();
        startMenu.execute();
/*        Manager manager = new Manager();
        manager.loadMissions();*/
    }
}

