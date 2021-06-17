package products;

import animals.Type;
import buildings.Capacity;

public class Products {
    private int timeToPick;
    private int timeLeft;
    private int cost;
    private Capacity capacity;
    private Type type;
    public Products(int cost,Capacity capacity,Type type)
    {
        this.cost = cost;
        this.capacity=capacity;
        switch (capacity){
            case PRIMERY :
                timeToPick =4;
                break;
            case MANUFACTURED:
                timeToPick =5;
                break;
            case MAIN :
                timeToPick =6;
                break;
            case WILDANIMALS:
                timeToPick = 5;
        }
        this.timeLeft= timeToPick;
        this.type=type;
    }

    public Type getType() {
        return type;
    }

    public int getCost() {
        return cost;
    }

    public boolean timeChecker(){
        if (timeLeft==1){
            timeLeft=timeToPick;
            return true;
        }
        timeLeft--;
        return false;
    }

    public int getCapacity() {
        return capacity.getCapacity();
    }


}
