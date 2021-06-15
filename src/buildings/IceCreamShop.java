package buildings;

import animals.Type;
import others.FarmPosition;
import others.Farmland;
import products.Products;

public class IceCreamShop extends ProductiveBuilding{
    private static IceCreamShop ourInstance;

    public static IceCreamShop getInstance() {
        if (ourInstance == null) {
            ourInstance = new IceCreamShop();
        }
        return ourInstance;
    }
    public IceCreamShop() {
        super(550, Type.PASTORIZEDMILK,Type.ICECREAM,7);
    }

    @Override
    public boolean initiateProduce(int timeLeft, WareHouse wareHouse) {
        return super.initiateProduce(timeLeft,wareHouse);
    }
    public void letsProduce(Farmland farmland, FarmPosition farmPosition) {
        super.letsProduce(farmland, farmPosition);
    }

    @Override
    public boolean turner() {
        return super.turner();
    }

    @Override
    public Products giveMeANewOne(Type type) {
        return super.giveMeANewOne(type);
    }

    @Override
    public boolean upgrade() {
        return super.upgrade();
    }
}
