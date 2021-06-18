package animals;

import others.FarmPosition;
import others.Farmland;
import others.Logger;

import java.util.Map;

public abstract class WildAnimal extends Animal {
    private int sellPrice;
    private int freedom ;
    private int cageTimes;
    private int enteranceTime ;

    public int getEnteranceTime() {
        return enteranceTime;
    }

    public void setEnteranceTime(int enteranceTime) {
        this.enteranceTime = enteranceTime;
    }

    public WildAnimal(int velocity, int freedom, int sellPrice,
                      int cageTimes, Type type, FarmPosition farmPosition,int enteranceTime) {
        super(velocity,type, farmPosition);
        this.freedom = freedom;
        this.sellPrice=sellPrice;
        this.cageTimes=cageTimes;
        this.enteranceTime=enteranceTime;
    }
    public int getCageTimes() {
        return cageTimes;
    }

    public int getSellPrice() {
        return sellPrice;
    }


    boolean attack (Farmland farmland)
    {
        for (Map.Entry<FarmPosition, Animal> entry : farmland.getFarmLandAnimal().entrySet()){
            if ((entry.getValue() instanceof DomesticAnimal || entry.getValue() instanceof Cat )&& entry.getKey().getY()==this.getFarmPosition().getY()
                    && entry.getKey().getX()==this.getFarmPosition().getX()){
                farmland.getFarmLandAnimal().remove(entry.getKey(),entry.getValue());
                System.out.println("wildOne attaked -__-");
                Logger.writeInfo("wildOne attaked -__-");
                return true;
            }
            else if (entry.getValue() instanceof Hound && entry.getKey().getY()==this.getFarmPosition().getY()
                    && entry.getKey().getX()==this.getFarmPosition().getX()){
                farmland.getFarmLandAnimal().remove(entry.getKey(),entry.getValue());
                farmland.getFarmLandAnimal().remove(this.getFarmPosition(),this);
                System.out.println("RIP");
                Logger.writeInfo("RIP");
            }
        }
        return false;
    }

    public boolean caged(){
        if (this.cageTimes>=this.getFreedom()){
            this.cageTimes=0;
            return true;
        }
        return false;
    }

    public int data(){
        return this.freedom-this.cageTimes;
    }

    public int getFreedom() {
        return freedom;
    }


    public boolean addCage(){
        if (cageTimes<freedom){
            this.cageTimes++;
            return true;
        }
        return false;
    }
}
