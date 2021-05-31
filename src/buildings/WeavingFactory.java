package buildings;

import products.Feather;
import products.Piece;
import products.Products;

import java.util.ArrayList;

public class WeavingFactory extends ProductiveBuilding {
    public WeavingFactory() {
        super(5, 250,Capacity.PRIMERY, new Feather());
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
