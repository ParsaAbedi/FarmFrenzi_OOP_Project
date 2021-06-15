package main.menu;
import animals.Bear;
import animals.WildAnimal;
import others.Logger;
import others.Manager;
import others.Mission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

public class InputProcessor extends Menu{
    static int n = 1;
    public  InputProcessor(Menu parent ){
        super(parent , "START");
    }
    @Override
    public void show() {
        //TODO
        System.out.printf("HERE IS YOUR FARM DUDE!\n");
    }
    ArrayList<Mission> missions = new ArrayList<>();
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
            else if(Pattern.matches("build\s+[a-zA-Z]+" , command))
            {
                build(command.split("\\s"));
            }
            else if(Pattern.matches("upgrade\s+[a-zA-Z]+" , command))
            {
                build(command.split("\\s"));
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
                logger.writeError("Invalid input!");
            }
        }

    }

    private void build(String[] strings){
        if(manager.build(strings[1]))
        {
            done();
        }
        else
            error();
    }

    private void upgrade(String[] strings){
        if(manager.upgrade(strings[1]))
        {
            done();
        }
        else
            error();
    }

    private void returnToMenu() {
        //TODO
        parentMenu.parentMenu.show();
        parentMenu.parentMenu.execute();
    }

    private void truckGo() {
        //TODO
        missions= new ArrayList<>(manager.loadMissions());
        for(Mission i :missions)
        {
            System.out.printf(i.toString());
        }
        if(manager.truckGo())
        {
            done();
        }
        else
            error();
    }

    private void unloadTruck(String[] split) {

        if(manager.unloadTruck(split[2]))
        {
            done();
        }
        else error();
    }

    private void loadTruck(String[] split) {
        if(manager.loadTruck(split[2]))
        {
            done();
        }
        else
            error();
    }

    private void done(){
        System.out.printf("COMPLETED!\n");
        Logger.writeInfo("Task Done!\n");
    }

    private void error(){
        System.err.printf("TASK FAILED!\n");
        Logger.writeError("Task Failed!\n");
    }

    private void turn(String[] split) {
        //TODO
        if(manager.turn(split[1]))
        {
            done();
        }
        else
            error();
    }

    private void cage(String[] split) {
        //TODO
        if(manager.cage(split[1], split[2]))
        {
            done();
        }
        else
            error();
    }

    private void work(String[] split) {

        if(manager.work(split[1]))
        {
            done();
        }
        else
            error();
    }

    private void plant(String[] split) {
        //TODO
        if(manager.plant(split[1], split[2]))
        {
            done();
        }
        else
            error();
    }

    private void well() {
        if(manager.well())
        {
            done();
        }
        else
            error();
    }

    private void pickup(String[] split) {
        //TODO
        if(manager.pickup(split[1], split[2]))
        {
            done();
        }
        else
            error();
    }

    private void buy(String[] split) {
        //TODO
        if(manager.buy(split[1]))
        {
            done();
        }
        else
            error();
    }
}

