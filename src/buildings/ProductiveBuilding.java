package buildings;

import animals.Type;
import others.FarmPosition;
import others.Farmland;
import products.*;

public class ProductiveBuilding extends Buildings {
    private Type neededProducts;
    private Type mainProductType;
    private Products mainProduct;
    private int level;
    private boolean duo;
    //private int numOfProductForSingleProduce;
    private int timeLeft;

    public ProductiveBuilding(int cost, Type neededProducts, Type mainProduct, int timeLeft) {
        super(cost);
        this.neededProducts = neededProducts;
        this.mainProductType = mainProduct;
        this.level = 1;
        this.duo=false;
        // this.numOfProductForSingleProduce=1;
        this.timeLeft = -1;

    }

    public void letsProduce(Farmland farmland, FarmPosition farmPosition){
       farmland.getFarmLandProduct().put(farmPosition,giveMeANewOne(this.mainProductType));
       if (duo){
           farmland.getFarmLandProduct().put(farmPosition,giveMeANewOne(this.mainProductType));
           duo=false;
           return;
       }
       return ;
    }

    public boolean turner(){
        if (timeLeft==1){
            timeLeft=-1;
            return true;
        }
        timeLeft--;
        return false;
    }

    boolean initiateProduce(int timeLeft, WareHouse wareHouse) {
        int num = 0;
        for (Products storedProduct : wareHouse.getStoredProducts()) {
            if (storedProduct.getType() == neededProducts) {
                if (this.level == 1) {
                    this.timeLeft = timeLeft;
                    this.mainProduct = giveMeANewOne(this.mainProductType);
                    wareHouse.getStoredProducts().remove(storedProduct);

                }
                else if (this.level == 2) {
                    for (Products product : wareHouse.getStoredProducts()) {
                        if (storedProduct.equals(this.neededProducts)) num++;
                    }
                    if (num == 1) {
                        if (timeLeft % 2 == 0) this.timeLeft = timeLeft / 2;
                        else this.timeLeft = (timeLeft / 2) + 1;
                        this.mainProduct = giveMeANewOne(mainProductType);
                        wareHouse.getStoredProducts().remove(storedProduct);

                    }
                    else {
                        duo=true;
                        this.timeLeft = timeLeft;
                        this.mainProduct = giveMeANewOne(this.mainProductType);
                        wareHouse.getStoredProducts().remove(storedProduct);
                        this.mainProduct = giveMeANewOne(this.mainProductType);
                        for (Products storedProduct1 : wareHouse.getStoredProducts()) {
                            if (storedProduct1.getType() == neededProducts) {
                                wareHouse.getStoredProducts().remove(storedProduct1);
                                break;
                            }
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    boolean upgrade() {
        if (this.level == 1) {
            this.level++;
            //  this.numOfProductForSingleProduce++;
            return true;
        }
        return false;
    }

    public Products giveMeANewOne(Type type) {
        switch (type) {
            case BREAD:
                return new Bread();
            case ICECREAM:
                return new IceCream();
            case CLOTHES:
                return new Clothes();
            case FLOUR:
                return new Flour();
            case PASTORIZEDMILK:
                return new PastorizedMilk();
            case PIECE:
                return new Piece();
        }
        return null;
    }
}
