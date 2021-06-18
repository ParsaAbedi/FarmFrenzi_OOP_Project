package animals;

import others.FarmPosition;

public class Cat extends Animal {
    private final int PRICE=150;
    private directions directions;

    public Cat(FarmPosition farmPosition) {
        super(1, Type.CAT, farmPosition);
    }


}
