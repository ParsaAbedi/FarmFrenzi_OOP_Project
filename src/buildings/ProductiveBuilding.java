package buildings;

import products.Products;

import java.util.ArrayList;

public class ProductiveBuilding extends Buildings{
    private ArrayList<Products> neededProducts;
    private Products product ;
    boolean produce(){
        return false;
    }
    boolean upgrade(){
        return false;
    }
}
