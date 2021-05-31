package animals;

import products.Products;

public abstract class DomesticAnimal extends Animal {
    private final int  DAMAGE_PER_TIME = 10;
    private int price;
    private int timeToCreateProduct;

    public int getPrice() {
        return price;
    }

    public int getTimeToCreateProduct() {
        return timeToCreateProduct;
    }

    public DomesticAnimal(int price, int timeToCreateProduct) {
        super(1);
        this.price = price;
        this.timeToCreateProduct = timeToCreateProduct;
    }

    boolean eat(){
        return false;
    }
    Object produce  (){
        return new Object();
    }

    public void setTimeToCreateProduct(int timeToCreateProduct) {
        this.timeToCreateProduct = timeToCreateProduct;
    }

    boolean takeDamage( int damage) {
        if(lives-DAMAGE_PER_TIME <0)
        {
            lives=0;
            if(!die())
                return false;
        }
        else
            lives-=DAMAGE_PER_TIME;
        return true;
    }
    boolean heal ()
    {
        lives = MAX_LIVES;
        return true;
    }
}
