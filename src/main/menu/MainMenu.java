package main.menu;

import java.util.HashMap;

public class MainMenu extends Menu{
    public MainMenu(Menu parentMenu) {
        super(parentMenu,"MAIN MENU");
    }
    @Override
    public void show() {
        System.out.println("I'm Main Menu");
    }

    @Override
    public void execute() {
        System.out.println("execute in Main Menu");
        this.parentMenu.show();
        this.parentMenu.execute();
    }
}
