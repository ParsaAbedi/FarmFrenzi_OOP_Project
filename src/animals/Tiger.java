package animals;

import others.FarmPosition;

public class Tiger extends WildAnimal {
    private int cageTimes;

    public Tiger(int enter ,FarmPosition farmPosition) {
        super(2,4,500,0,Type.TIGER,farmPosition,enter);
    }

}
