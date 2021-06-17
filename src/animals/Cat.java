package animals;

import others.FarmPosition;

public class Cat extends Animal {
    private final int PRICE=150;
    private FarmPosition farmPosition;
    private directions directions;

    public Cat(int velocity, Type type) {
        super(1, Type.CAT);
    }

    public void collect(){

        //TODO
    }
}
