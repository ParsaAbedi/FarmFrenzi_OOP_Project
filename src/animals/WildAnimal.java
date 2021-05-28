package animals;

public abstract class WildAnimal extends Animal {
    public int getFreedom() {
        return freedom;
    }

    private int freedom ;

    public WildAnimal(int velocity, int freedom) {
        super(velocity);
        this.freedom = freedom;
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

    @Override
    public boolean move() {
        return false;
    }
}
