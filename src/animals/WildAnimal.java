package animals;

public abstract class WildAnimal extends Animal {
    public int getFreedom() {
        return freedom;
    }
    private int sellPrice;
    private int freedom ;

    public WildAnimal(int velocity, int freedom,int sellPrice) {
        super(velocity);
        this.freedom = freedom;
        this.sellPrice=sellPrice;
    }

    boolean attack (DomesticAnimal domesticAnimal)
    {
        return false;
    }
    boolean buildCage ()
    {
        //TODO
        return false;
    }
}
