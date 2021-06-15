package animals;

import products.Products;

public abstract class DomesticAnimal extends Animal {
    private final int  DAMAGE_PER_TIME = 10;
    private int price;
    private final int TIME;
    private int timeToCreateProduct;
    private Type kind;



    public int getSellPrice() {
        return price;
    }

   public boolean itIsTheTime(){
        if (timeToCreateProduct==1){
            timeToCreateProduct=TIME;
            return true;
        }
        timeToCreateProduct--;
        return false;
   }

    public Type getKind() {
        return kind;
    }

    public DomesticAnimal(int price, int timeToCreateProduct, Type kind) {
        super(1,Type.DOMESTIC);
        this.price = price;
        this.TIME = timeToCreateProduct;
        this.timeToCreateProduct=timeToCreateProduct;
        this.kind=kind;
    }

    boolean eat(){
        return false;
    }
    Object produce  (){
        return new Object();
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
