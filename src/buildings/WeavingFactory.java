package buildings;

import others.Farmland;
import products.Feather;
import products.Piece;
import products.Products;

import java.util.ArrayList;

public class WeavingFactory extends ProductiveBuilding {
    private static WeavingFactory ourInstance;

    public static WeavingFactory getInstance() {
        if (ourInstance == null) {
            ourInstance = new WeavingFactory();
        }
        return ourInstance;
    }
    public WeavingFactory() {
        super(250, new Feather());
    }


    @Override
    public boolean produce(int timeLeft, Products uniqeProduct, WareHouse wareHouse, Farmland farmland) {
        return super.produce(timeLeft, uniqeProduct, wareHouse, farmland);
    }

    @Override
    public boolean upgrade() {
        return super.upgrade();
    }
}
