package main.menu;

import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.*;
import others.*;
public abstract class Menu {
    static Manager manager = new Manager();
    protected Menu parentMenu ;
    protected HashMap<Integer , Menu> submenus  ;
    private String name ;
    public static Scanner scanner ;

    public Menu(Menu parentMenu, String name) {
        this.parentMenu = parentMenu;
        this.name = name;
    }


    public Menu(String name) {
        this.parentMenu = null;
        this.name = name;
    }

    public void execute (){
        Menu nextMenu = null;
        String command;
        boolean quit = false;
        int nextMenuNum ;
        while (!quit)
        {
            command = scanner.nextLine();
            command = command.trim();
            if(Pattern.matches("[1-9]" , command))
            {
                nextMenuNum = Integer.parseInt(command);
                if (nextMenuNum == submenus.size() + 1) {
                    if (this.parentMenu == null) {
                        System.exit(1);
                    }
                    else {
                        nextMenu = parentMenu;
                        quit = true;
                    }
                }
                else if (nextMenuNum < submenus.size() + 1 && nextMenuNum > 0) {
                    nextMenu = submenus.get(nextMenuNum);
                    quit = true;
                }
                else
                {
                    System.err.println("Invalid input!");
                }
            }
            else {
                System.err.println("Invalid input!");
            }

        }
        nextMenu.show();
        nextMenu.execute();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void show() {
        System.out.println(this.name + ": ");
        for (Integer integer : submenus.keySet()) {
            System.out.println(integer + ". " + submenus.get(integer).getName());
        }
        if (this.parentMenu == null) {
            System.out.println((submenus.size() + 1) + ". exit");
        } else {
            System.out.println((submenus.size() + 1) + ". back");
        }
    }

    protected String getName()
    {
        return this.name;
    }

    public static void setScanner(Scanner scanner) {
        Menu.scanner = scanner;
    }

    public void setSubmenus(HashMap<Integer, Menu> submenus) {
        this.submenus = submenus;
    }

}
