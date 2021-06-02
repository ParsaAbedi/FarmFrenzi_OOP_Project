package main.menu;

import java.util.HashMap;

public class StartMenu extends Menu{
    public StartMenu() {
        super("START MENU");
        logger.start();
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1,new LoginMenu(this));
        submenus.put(2,new SignupMenu(this));
        this.setSubmenus(submenus);
    }
}
