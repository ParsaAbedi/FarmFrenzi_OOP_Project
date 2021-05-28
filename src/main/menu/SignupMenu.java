package main.menu;

import java.util.regex.Pattern;

public class SignupMenu extends Menu{
    public SignupMenu(Menu parentMenu) {
        super(parentMenu , "SIGN UP MENU");
    }
    public void show() {
        System.out.printf("PLEASE ENTER YOUR USERNAME OR ENTER BACK TO RETURN TO START MENU\n");
    }

    @Override
    public void execute() {
        Menu nextMenu ;
        String command;
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
                    quit = true;
                }
                else
                {
                    System.err.println("THIS USERNAME IS NOT AVAILABLE!\n");
                }
            }
            else {
                System.err.println("INVALID INPUT!");
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
                else{
                    nextMenu = new MainMenu(parentMenu);
                    nextMenu.show();
                    nextMenu.execute();
                    quit = true;
                }
            }
            else {
                System.err.println("INVALID INPUT!");
            }

        }
        super.execute();
    }
}
