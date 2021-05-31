package buildings;

import products.Clothes;
import products.Piece;
import products.Products;

import java.util.ArrayList;

public class SewingWorkshop extends ProductiveBuilding{
    public SewingWorkshop() {
        super(6, 400,Capacity.MAIN, new Piece());
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
