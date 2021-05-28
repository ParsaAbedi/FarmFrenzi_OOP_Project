package buildings;

import products.Products;

import java.util.ArrayList;

public class MilkFactory extends ProductiveBuilding{
    public MilkFactory(ArrayList<Products> neededProducts, Products product) {
        super(6,400,Capacity.PRIMERY, neededProducts, product);
    }
}
