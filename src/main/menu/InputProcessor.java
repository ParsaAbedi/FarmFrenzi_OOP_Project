package main.menu;
import others.Manager;

import java.util.regex.Pattern;

public class InputProcessor extends Menu{
    private Manager manager = new Manager();
    public  InputProcessor(Menu parent ){
        super(parent , "START");
    }
    @Override
    public void show() {
        //TODO
        System.out.printf("HERE IS YOUR FARM DUDE!\n");
    }

    @Override
    public void execute() {
        String command;
        boolean quit = false;
        while (!quit)
        {
            command = scanner.nextLine();
            command = command.trim();
            command = command.toLowerCase();
            if(Pattern.matches("buy\s+[a-zA-Z]+" , command))
            {
                buy(command.split("\\s"));
            }
            else if(Pattern.matches("pickup\s+[0-9]+\s+[0-9]+" , command))
            {
                pickup(command.split("\\s"));
            }
            else if(Pattern.matches("well" , command))
            {
                well();
            }
            else if(Pattern.matches("plant\s+[0-9]+\s+[0-9]+" , command))
            {
                plant(command.split("\\s"));
            }
            else if(Pattern.matches("work\s+[a-zA-Z]+" , command))
            {
                work(command.split("\\s"));
            }
            else if(Pattern.matches("cage\s+[0-9]+\s+[0-9]+" , command))
            {
                cage(command.split("\\s"));
            }
            else if(Pattern.matches("turn\s+[0-9]+" , command))
            {
                turn(command.split("\\s"));
            }
            else if(Pattern.matches("truck\s+load\s+[a-zA-Z]+" , command))
            {
                loadTruck(command.split("\\s"));
            }
            else if(Pattern.matches("truck\s+unload\s+[a-zA-Z]+" , command))
            {
                unloadTruck(command.split("\\s"));
            }
            else if(Pattern.matches("truck\s+go" , command))
            {
                truckGo();
            }
            else if(Pattern.matches("menu", command))
            {
                returnToMenu();
                quit = true;
            }
            else {
                System.err.println("Invalid input!");
            }
        }

        }

    private void returnToMenu() {
        //TODO
        parentMenu.parentMenu.show();
        parentMenu.parentMenu.execute();
    }

    private void truckGo() {
        //TODO
        if(manager.truckGo())
        {
            System.err.printf("COMPLETED!\n");
        }
        else
            System.err.printf("TASK FAILED!\n");
    }

    private void unloadTruck(String[] split) {
        //TODO
        if(manager.unloadTruck(split[2]))
        {
            System.err.printf("COMPLETED!\n");
        }
        else
            System.err.printf("TASK FAILED!\n");
    }

    private void loadTruck(String[] split) {
        //TODO
        if(manager.loadTruck(split[2]))
        {
            System.err.printf("COMPLETED!\n");
        }
        else
            System.err.printf("TASK FAILED!\n");
    }

    private void turn(String[] split) {
        //TODO
        if(manager.turn(split[1]))
        {
            System.err.printf("COMPLETED!\n");
        }
        else
            System.err.printf("TASK FAILED!\n");
    }

    private void cage(String[] split) {
        //TODO
        if(manager.cage(split[1], split[2]))
        {
            System.err.printf("COMPLETED!\n");
        }
        else
            System.err.printf("TASK FAILED!\n");
    }

    private void work(String[] split) {
        //TODO
        if(manager.work(split[1]))
        {
            System.err.printf("COMPLETED!\n");
        }
        else
            System.err.printf("TASK FAILED!\n");
    }

    private void plant(String[] split) {
        //TODO
        if(manager.plant(split[1], split[2]))
        {
            System.err.printf("COMPLETED!\n");
        }
        else
            System.err.printf("TASK FAILED!\n");
    }

    private void well() {
        //TODO
        if(manager.well())
        {
            System.err.printf("COMPLETED!\n");
        }
        else
            System.err.printf("TASK FAILED!\n");
    }

    private void pickup(String[] split) {
        //TODO
        if(manager.pickup(split[1], split[2]))
        {
            System.err.printf("COMPLETED!\n");
        }
        else
            System.err.printf("TASK FAILED!\n");
    }

    private void buy(String[] split) {
        //TODO
        if(manager.buy(split[1]))
        {
            System.err.printf("COMPLETED!\n");
        }
        else
            System.err.printf("TASK FAILED!\n");
    }
}

