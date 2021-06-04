package buildings;

import others.Farmland;
import products.Egg;
import products.Flour;
import products.Products;

import java.util.ArrayList;

public class Mill extends ProductiveBuilding {
    private static Mill ourInstance;

    public static Mill getInstance() {
        if (ourInstance == null) {
            ourInstance = new Mill();
        }
        return ourInstance;
    }
    public Mill() {
        super( 150, new Egg());
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
