package buildings;

import animals.Type;
import others.FarmPosition;
import others.Farmland;
import products.Products;

public class WeavingFactory extends ProductiveBuilding {
    private static WeavingFactory ourInstance;

    public static WeavingFactory getInstance() {
        if (ourInstance == null) {
            ourInstance = new WeavingFactory();
        }
        return ourInstance;
    }
    public WeavingFactory() {
        super(250, Type.FEATHER , Type.PIECE,5);
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
        return super.initiateProduce(timeLeft,wareHouse);
    }

    @Override
    public boolean upgrade() {
        return super.upgrade();
    }
}
