package buildings;

import animals.Type;
import others.FarmPosition;
import others.Farmland;
import products.Products;

public class SewingWorkshop extends ProductiveBuilding{
    private static SewingWorkshop ourInstance;

    public static SewingWorkshop getInstance() {
        if (ourInstance == null) {
            ourInstance = new SewingWorkshop();
        }
        return ourInstance;
    }
    public SewingWorkshop() {
        super(400, Type.PIECE,Type.CLOTHES,6);
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
