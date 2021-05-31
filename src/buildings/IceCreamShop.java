package buildings;

import products.IceCream;
import products.PastorizedMilk;
import products.Products;

import java.util.ArrayList;

public class IceCreamShop extends ProductiveBuilding{
    public IceCreamShop() {
        super(7,550,Capacity.MAIN,new PastorizedMilk());
    }

    @Override
    public boolean produce(int timeLeft, Products uniqeProduct) {
        return super.produce(timeLeft, uniqeProduct);
    }

    @Override
    public boolean isBuilt() {
        return super.isBuilt();
    }

    @Override
    public void setBuilt(boolean built) {
        super.setBuilt(built);
    }
}
