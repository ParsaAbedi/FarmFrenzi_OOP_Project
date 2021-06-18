package animals;

import others.FarmPosition;

public class Hound extends Animal {
    private final int PRICE=100;
    private FarmPosition farmPosition;
    private directions directions;

    public Hound(FarmPosition farmPosition) {
        super(1, Type.HOUND,farmPosition );
    }

    public void hunt(){
        //TODO
    }
}
