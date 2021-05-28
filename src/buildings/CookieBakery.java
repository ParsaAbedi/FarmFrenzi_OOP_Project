package buildings;

import products.Products;

import java.util.ArrayList;

public class CookieBakery extends ProductiveBuilding {

    public CookieBakery( ArrayList<Products> neededProducts, Products product) {
        super(5,250,Capacity.MAIN, neededProducts, product);
    }
}
