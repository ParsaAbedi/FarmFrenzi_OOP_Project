package others;

import animals.Animal;
import animals.WildAnimal;

import java.util.ArrayList;
import java.util.HashMap;

public class Mission {
    private int missionNumber ;
    private boolean isLocked = true;
    private int initialCoins ;
   ArrayList<WildAnimal> wildAnimals = new ArrayList<>();
    HashMap<Task,Boolean> tasksCheckBoard = new HashMap<>();
    private int maxTime ;

    public void setInitialCoins(int initialCoins) {
        this.initialCoins = initialCoins;
    }

    private int prize ;

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public Mission() {
    }

    public Mission(int missionNumber,int initialCoins, ArrayList<WildAnimal> wildAnimals, HashMap<Task, Boolean> tasksCheckBoard, int maxTime, int prize) {
        this.missionNumber = missionNumber;
        this.initialCoins = initialCoins;
        this.wildAnimals = wildAnimals;
        this.tasksCheckBoard = tasksCheckBoard;
        this.maxTime = maxTime;
        this.prize = prize;
    }
    @Override
    public String toString() {
        return "Mission{" +
                "mission number='" + missionNumber + '\'' +
                ", isLocked='" + isLocked + '\'' +
                ", initialCoins='" + initialCoins + '\'' +
                ", maxTime='" + maxTime + '\'' +
                ", prize='" + prize + '\'' +
                '}';
    }

    public int getMissionNumber() {
        return missionNumber;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public int getInitialCoins() {
        return initialCoins;
    }

    public ArrayList<WildAnimal> getWildAnimals() {
        return wildAnimals;
    }

    public HashMap<Task, Boolean> getTasksCheckBoard() {
        return tasksCheckBoard;
    }

    public int getMaxTime() {
        return maxTime;
    }

    public int getPrize() {
        return prize;
    }
}
