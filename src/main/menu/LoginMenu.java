package main.menu;

import java.util.HashMap;
import java.util.regex.Pattern;

public class LoginMenu extends Menu{
    public LoginMenu (Menu parentMenu) {
        super(parentMenu , "LOG IN MENU");
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1,new MainMenu(parentMenu));
        this.setSubmenus(submenus);
    }

    @Override
    public void show() {
        System.out.printf("PLEASE ENTER YOUR USERNAME OR ENTER BACK TO RETURN TO START MENU\n");
    }

    @Override
    public void execute() {
        Menu nextMenu ;
        String command;
        String username = "USER";
        boolean quit = false;
        // RECEIVING USERNAME
        while (!quit)
        {
            command = scanner.nextLine();
            command = command.trim();
            if(Pattern.matches("[a-zA-Z0-9\s]+" , command))
            {
                if(command.toUpperCase().equals("BACK"))
                {
                    nextMenu = parentMenu;
                    nextMenu.show();
                    nextMenu.execute();
                    return;
                }
                else if (manager.checkUsername(command)) {
                    username = command ;
                    quit = true;
                }
                else
                {
                    System.err.println("NO USER WITH SUCH USERNAME WAS FOUND!\n");
                }
            }
            else {
                System.err.println("Invalid input!");
            }
        }
        // RECEIVING PASSWORD
        System.out.printf("PLEASE ENTER YOUR PASSWORD OR ENTER BACK TO RETURN TO START MENU\n");
        quit = false;
        while (!quit)
        {
            command = scanner.nextLine();
            command = command.trim();
            if(Pattern.matches("[a-zA-Z0-9]+" , command))
            {
                if(command.toUpperCase().equals("BACK"))
                {
                    nextMenu = parentMenu;
                    nextMenu.show();
                    nextMenu.execute();
                    quit = true;
                }
                else if (manager.checkPassword(username,command)) {
                    nextMenu = new MainMenu(parentMenu);
                    nextMenu.show();
                    nextMenu.execute();
                    quit = true;
                }
                else
                {
                    System.err.println("PASSWORD IS WRONG!\n");
                }
            }
            else {
                System.err.println("Invalid input!");
            }

        }
        super.execute();
    }
}
