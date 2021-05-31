package buildings;

import products.Products;

import java.util.ArrayList;

public abstract class ProductiveBuilding extends Buildings{
    private ArrayList<Products> neededProducts;
    private Products product ;
    private Capacity capacity;

    public ProductiveBuilding(int loadingTime, int cost,Capacity capacity, ArrayList<Products> neededProducts) {
        super(loadingTime, cost);
        this.neededProducts = neededProducts;
        this.capacity=capacity;
    }

    boolean produce(){
        return false;
    }
    boolean upgrade(){
        return false;
    }
}
