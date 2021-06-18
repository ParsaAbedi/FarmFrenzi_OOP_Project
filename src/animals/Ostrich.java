package animals;

import others.FarmPosition;

public class Ostrich extends DomesticAnimal{
    public Ostrich( FarmPosition farmPosition) {
        super(200 , 3,Type.OSTRICH,farmPosition);
    }

    Object produce(){
        return new Object();
    }
}
