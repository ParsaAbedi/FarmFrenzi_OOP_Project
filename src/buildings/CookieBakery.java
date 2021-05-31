package buildings;

import products.Bread;
import products.Flour;
import products.Products;

import java.util.ArrayList;

public class CookieBakery extends ProductiveBuilding {

    public CookieBakery( ) {
        super(5,250,Capacity.MAIN, new Flour());

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

