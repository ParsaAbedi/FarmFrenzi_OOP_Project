package buildings;

import products.Products;

import java.util.ArrayList;

public class Mill extends ProductiveBuilding {
    public Mill( ArrayList<Products> neededProducts, Products product) {
        super(4, 150,Capacity.PRIMERY, neededProducts);
    }

}
