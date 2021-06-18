package main.menu;

import others.Manager;
import others.Mission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;
import static main.Main.mission;
public class Map extends Menu{
    private Manager manager = new Manager();
    public ArrayList<Mission> missions = new ArrayList<>(manager.loadMissions());
    public Map(Menu parent) {
        super("MAIN MENU");
        logger.start();
        HashMap<Integer, Menu> submenus = new HashMap<>();
        for (int i=0 ; i< manager.missionsNum ; i++)
        {
            submenus.put(i+1,new InputProcessor(this));
        }
        this.setSubmenus(submenus);
    }
    public void show() {
        System.out.println(super.getName() + ": ");
        for(int i=0 ; i< manager.missionsNum ; i++)
        {
            submenus.get(i+1).setName("MISSION "+(i+1));
        }
        for (Integer integer : submenus.keySet()) {
            System.out.println(integer + ". " + submenus.get(integer).getName());
        }
        if (this.parentMenu == null) {
            System.out.println((submenus.size() + 1) + ". exit");
        } else {
            System.out.println((submenus.size() + 1) + ". back");
        }
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
                        logger.writeInfo("System exit!");
                        System.exit(1);
                    }
                    else {
                        nextMenu = parentMenu;
                        quit = true;
                    }
                }
                else if (nextMenuNum < submenus.size() + 1 && nextMenuNum > 0) {
                    if(!missions.get(nextMenuNum-1).isLocked())
                    {
                        logger.writeInfo("Submenu number "+nextMenuNum+" selected!");
                        mission = missions.get(nextMenuNum-1);
                        System.out.println(mission.toString());
                        nextMenu = submenus.get(nextMenuNum);
                        quit = true;
                    }
                    else
                    {
                        logger.writeInfo("Mission "+nextMenuNum+" is locked!");
                        System.out.printf("Mission "+nextMenuNum+" is locked!");
                    }
                }
                else
                {
                    logger.writeError("Invalid input in "+this.name);
                    System.err.println("Invalid input!");
                }
            }
            else {
                logger.writeError("Invalid input in "+this.name);
                System.err.println("Invalid input!");
            }

        }
        nextMenu.show();
        nextMenu.execute();
    }
}
