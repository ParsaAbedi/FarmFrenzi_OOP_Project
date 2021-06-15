package animals;

import animals.DomesticAnimal;

public class Buffalo extends DomesticAnimal {
    public Buffalo( int price, int timeToCreateProduct) {
        super(400 , 5,Type.BUFFALO);
    }


    Object produce(){
        return new Object();
    }
}
