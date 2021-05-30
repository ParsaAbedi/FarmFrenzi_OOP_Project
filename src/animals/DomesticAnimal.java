package animals;

import products.Products;

public abstract class DomesticAnimal extends Animal {
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
