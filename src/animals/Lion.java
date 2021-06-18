package animals;

import others.FarmPosition;

public class Lion extends WildAnimal {

    private int cageTimes;

    public Lion(int enter ,FarmPosition farmPosition) {

        super(1, 3, 300, 0,Type.TIGER,farmPosition,enter);
    }


}
