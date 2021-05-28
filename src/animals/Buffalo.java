package animals;

import animals.DomesticAnimal;

public class Buffalo extends DomesticAnimal {
    public Buffalo( int price, int timeToCreateProduct) {
        super(400 , 5);
    }

    public boolean startOver() {
        if (this.getTimeToCreateProduct()==0)
        {
            this.setTimeToCreateProduct(this.getTimeToCreateProduct());
            return true;
        }
        else return false;
    }
    Object produce(){
        return new Object();
    }
}
