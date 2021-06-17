package animals;

import others.FarmPosition;

public class Hound extends Animal {
    private final int PRICE=100;
    private FarmPosition farmPosition;
    private directions directions;

    public Hound() {
        super(1, Type.HOUND);
    }

    public void hunt(){
        //TODO
    }
}
