package animals;

public abstract class WildAnimal extends Animal {
    public int getFreedom() {
        return freedom;
    }
    private int sellPrice;
    private int freedom ;
    private int cageTimes;
    private int enteranceTime ;
    public WildAnimal(int velocity, int freedom, int sellPrice, int cageTimes) {
        super(velocity,Type.WILD);
        this.freedom = freedom;
        this.sellPrice=sellPrice;
        this.cageTimes=cageTimes;
    }

    public int getCageTimes() {
        return cageTimes;
    }

    public int getSellPrice() {
        return sellPrice;
    }


    public WildAnimal(int enteranceTime) {
        this.enteranceTime = enteranceTime;
    }

    boolean attack (DomesticAnimal domesticAnimal)
    {
        return false;
    }

    public boolean caged(){
        if (this.cageTimes>=this.getFreedom())
            return true;
        return false;
    }
    public boolean addCage(){
        if (cageTimes<freedom){
            this.cageTimes++;
            return true;
        }
}
