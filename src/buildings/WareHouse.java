package buildings;

import animals.WildAnimal;
import products.Products;

import java.util.ArrayList;

public class WareHouse extends Buildings{
    private int capacity ;
    ArrayList<Products> storedProducts;
    ArrayList<WildAnimal> wildAnimals;
    public WareHouse(int loadingTime, int cost) {
        super(loadingTime, cost);
        this.capacity=30;
        this.storedProducts=new ArrayList<>();

    }
}
