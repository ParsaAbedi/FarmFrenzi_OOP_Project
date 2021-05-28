package animals;

import products.Products;

public class DomesticAnimal extends Animal {
    private int   price , productionDelay;
    boolean eat(){
        return false;
    }
    Object produce  (){
        return new Object();
    }

    boolean takeDamage() {
        //TODO
        return true;
    }
    boolean heal ()
    {
        //TODO
        return true;
    }
}
