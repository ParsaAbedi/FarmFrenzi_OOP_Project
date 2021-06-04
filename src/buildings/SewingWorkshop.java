package buildings;

import others.Farmland;
import products.Clothes;
import products.Piece;
import products.Products;

import java.util.ArrayList;

public class SewingWorkshop extends ProductiveBuilding{
    private static SewingWorkshop ourInstance;

    public static SewingWorkshop getInstance() {
        if (ourInstance == null) {
            ourInstance = new SewingWorkshop();
        }
        return ourInstance;
    }
    public SewingWorkshop() {
        super(400, new Piece());
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
