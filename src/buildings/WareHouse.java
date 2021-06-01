package buildings;

import animals.WildAnimal;
import products.Products;

import java.util.ArrayList;

public class WareHouse extends Buildings{
    private static int capacity ;
    private static ArrayList<Products> storedProducts;
    ArrayList<WildAnimal> wildAnimals;
    private static WareHouse ourInstance;

    public static WareHouse getInstance() {
        if (ourInstance == null) {
            ourInstance = new WareHouse();
        }
        return ourInstance;
    }
    public WareHouse() {
        super(0);
        capacity=30;
        storedProducts=new ArrayList<>();
        this.wildAnimals=new ArrayList<>();
    }

    public static ArrayList<Products> getStoredProducts() {
        return storedProducts;
    }

    public static int getCapacity() {
        return capacity;
    }

    public static void setCapacity(int capacity) {
        WareHouse.capacity = capacity;
    }
}
