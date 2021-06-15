package main.menu;

import java.util.HashMap;

public class MainMenu extends Menu{
    public MainMenu(Menu parentMenu) {

        super(parentMenu,"MAIN MENU");
        HashMap <Integer , Menu> submenus = new HashMap<>();
        submenus.put(1, new Map(this));
        submenus.put(2,parentMenu);
        submenus.put(3,new SettingMenu(this));
        super.setSubmenus(submenus);
    }
    @Override
    public void show() {
        System.out.println(super.getName() + ": ");
        submenus.get(2).setName("LOGOUT");
        for (Integer integer : submenus.keySet()) {
            System.out.println(integer + ". " + submenus.get(integer).getName());
        }
        submenus.get(2).setName("START MENU");
        if (this.parentMenu == null) {
            System.out.println((submenus.size() + 1) + ". exit");
        } else {
            System.out.println((submenus.size() + 1) + ". back");
        }
    }

}
