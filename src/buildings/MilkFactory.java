package buildings;

import products.Milk;
import products.PastorizedMilk;
import products.Products;

import java.util.ArrayList;

public class MilkFactory extends ProductiveBuilding{
    public MilkFactory() {
        super(6,400,Capacity.PRIMERY, new Milk());
    }

    @Override
    public boolean isBuilt() {
        return super.isBuilt();
    }

    @Override
    public void setBuilt(boolean built) {
        super.setBuilt(built);
    }

    @Override
    public boolean produce(int timeLeft, Products uniqeProduct) {
        return super.produce(timeLeft, uniqeProduct);
    }
}
