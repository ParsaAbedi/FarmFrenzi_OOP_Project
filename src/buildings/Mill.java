package buildings;

import animals.Type;
import others.FarmPosition;
import others.Farmland;
import products.Products;

public class Mill extends ProductiveBuilding {
    private static Mill ourInstance;

    public static Mill getInstance() {
        if (ourInstance == null) {
            ourInstance = new Mill();
        }
        return ourInstance;
    }
    public Mill() {
        super( 150, Type.EGG,Type.FLOUR,4);
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
