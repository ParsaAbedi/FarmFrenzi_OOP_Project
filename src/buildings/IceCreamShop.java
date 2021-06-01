package buildings;

import others.Farmland;
import products.IceCream;
import products.PastorizedMilk;
import products.Products;

import java.util.ArrayList;

public class IceCreamShop extends ProductiveBuilding{
    private static IceCreamShop ourInstance;

    public static IceCreamShop getInstance() {
        if (ourInstance == null) {
            ourInstance = new IceCreamShop();
        }
        return ourInstance;
    }
    public IceCreamShop() {
        super(550,new PastorizedMilk());
    }

    @Override
    public boolean produce(int timeLeft, Products uniqeProduct, WareHouse wareHouse, Farmland farmland) {
        return super.produce(timeLeft, uniqeProduct, wareHouse, farmland);
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
    public boolean upgrade() {
        return super.upgrade();
    }
}
