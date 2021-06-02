package main.menu;

import java.util.regex.Pattern;

public class SignupMenu extends Menu{
    public SignupMenu(Menu parentMenu) {
        super(parentMenu , "SIGN UP MENU");
    }
    public void show() {
        System.out.printf("PLEASE ENTER YOUR USERNAME OR ENTER BACK TO RETURN TO START MENU\n");
    }
    private String username , password;
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
                else if (!manager.checkUsername(command)) {
                    username = new String(command);
                    quit = true;
                }
                else
                {
                    System.err.println("THIS USERNAME IS NOT AVAILABLE!\n");
                    logger.writeError("THIS USERNAME IS NOT AVAILABLE!");
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
                    password = new String(command);
                    if(manager.signup(username,command))
                        logger.writeInfo("Signed up\n\tusername: "+username+"\tpassword: "+password);
                    else
                        logger.writeError("Sign up failed!");
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
