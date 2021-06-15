package buildings;

import animals.Type;
import others.FarmPosition;
import others.Farmland;
import products.Products;

public class CookieBakery extends ProductiveBuilding {
    private static CookieBakery ourInstance;

    public static CookieBakery getInstance() {
        if (ourInstance == null) {
            ourInstance = new CookieBakery();
        }
        return ourInstance;
    }
    public CookieBakery( ) {
        super(250, Type.FLOUR,Type.BREAD,5);

    }

    @Override
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

