package buildings;

import animals.Type;
import others.FarmPosition;
import others.Farmland;
import products.Products;

public class MilkFactory extends ProductiveBuilding{
    private static MilkFactory ourInstance;

    public static MilkFactory getInstance() {
        if (ourInstance == null) {
            ourInstance = new MilkFactory();
        }
        return ourInstance;
    }
    public MilkFactory() {
        super(400, Type.MILK,Type.PASTORIZEDMILK,6);
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
    public boolean initiateProduce(int timeLeft, WareHouse wareHouse) {
        return super.initiateProduce(timeLeft, wareHouse);
    }

    @Override
    public boolean upgrade() {
        return super.upgrade();
    }
}
