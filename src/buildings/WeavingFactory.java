package buildings;

import products.Products;

import java.util.ArrayList;

public class WeavingFactory extends ProductiveBuilding {
    public WeavingFactory(ArrayList<Products> neededProducts, Products product) {
        super(5, 250,Capacity.PRIMERY, neededProducts);
    }
}
