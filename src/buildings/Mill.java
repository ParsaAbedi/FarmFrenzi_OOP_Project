package buildings;

import products.Egg;
import products.Flour;
import products.Products;

import java.util.ArrayList;

public class Mill extends ProductiveBuilding {
    public Mill() {
        super(4, 150,Capacity.PRIMERY, new Egg());
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
