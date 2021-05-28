package buildings;

import products.Products;

import java.util.ArrayList;

public class IceCreamShop extends ProductiveBuilding{
    public IceCreamShop(ArrayList<Products> neededProducts, Products product) {
        super(7,550,Capacity.MAIN, neededProducts, product);
    }
}
