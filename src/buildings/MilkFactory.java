package buildings;

import others.Farmland;
import products.Milk;
import products.PastorizedMilk;
import products.Products;

import java.util.ArrayList;

public class MilkFactory extends ProductiveBuilding{
    private static MilkFactory ourInstance;

    public static MilkFactory getInstance() {
        if (ourInstance == null) {
            ourInstance = new MilkFactory();
        }
        return ourInstance;
    }
    public MilkFactory() {
        super(400, new Milk());
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
    public boolean produce(int timeLeft, Products uniqeProduct, WareHouse wareHouse, Farmland farmland) {
        return super.produce(timeLeft, uniqeProduct, wareHouse, farmland);
    }

    @Override
    public boolean upgrade() {
        return super.upgrade();
    }
}
