package buildings;

import products.Products;

import java.util.ArrayList;

public class SewingWorkshop extends ProductiveBuilding{
    public SewingWorkshop(ArrayList<Products> neededProducts, Products product) {
        super(6, 400,Capacity.MAIN, neededProducts, product);
    }
}
