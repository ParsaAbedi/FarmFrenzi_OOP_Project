package buildings;

import others.Farmland;
import products.Flour;
import products.Products;

import java.util.ArrayList;

public class CookieBakery extends ProductiveBuilding {
    private static CookieBakery ourInstance;

    public static CookieBakery getInstance() {
        if (ourInstance == null) {
            ourInstance = new CookieBakery();
        }
        return ourInstance;
    }
    public CookieBakery( ) {
        super(250, new Flour());

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

