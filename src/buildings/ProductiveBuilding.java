package buildings;

import products.Products;

import java.util.ArrayList;

public class ProductiveBuilding extends Buildings{
    private ArrayList<Products> neededProducts;
    private Products product ;
    private Capacity capacity;
    public ProductiveBuilding(int loadingTime, int cost,Capacity capacity, ArrayList<Products> neededProducts, Products product) {
        super(loadingTime, cost);
        this.neededProducts = neededProducts;
        this.product = product;
        this.capacity=capacity;
    }

    boolean produce(){
        return false;
    }
    boolean upgrade(){
        return false;
    }
}
