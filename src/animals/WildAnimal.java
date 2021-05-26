package animals;

public class WildAnimal extends Animal {
    private int freedom;
    boolean attack (DomesticAnimal domesticAnimal)
    {
        return false;
    }

    @Override
    public boolean move() {
        return false;
    }
}
