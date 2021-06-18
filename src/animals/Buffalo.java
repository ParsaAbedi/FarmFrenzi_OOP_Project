package animals;

import animals.DomesticAnimal;
import others.FarmPosition;

public class Buffalo extends DomesticAnimal {
    public Buffalo( FarmPosition farmPosition) {
        super(400 , 5,Type.BUFFALO,farmPosition);
    }


    Object produce(){
        return new Object();
    }
}
