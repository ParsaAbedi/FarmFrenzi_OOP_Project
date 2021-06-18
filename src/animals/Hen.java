package animals;

import others.FarmPosition;

public class Hen extends DomesticAnimal{
    public Hen (FarmPosition farmPosition) {
        super(100 , 2,Type.HEN,farmPosition);
    }


    Object produce(){
        return new Object();
    }

}
