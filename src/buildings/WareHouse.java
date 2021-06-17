package buildings;

import animals.WildAnimal;
import products.Products;

import java.util.ArrayList;

public class WareHouse extends Buildings{
    private static int capacity ;
    private static ArrayList<Products> storedProducts;

    private ArrayList<WildAnimal> wildAnimals;

    private static WareHouse ourInstance;
    public static WareHouse getInstance() {
        if (ourInstance == null) {
            ourInstance = new WareHouse();
        }
        return ourInstance;
    }

    public WareHouse() {
        super(0);
        capacity=0;
        storedProducts=new ArrayList<>();
        this.wildAnimals=new ArrayList<>();
    }
    public ArrayList<WildAnimal> getWildAnimals() {
        return wildAnimals;
    }

    public static ArrayList<Products> getStoredProducts() {
        return storedProducts;
    }

    public static int getCapacity() {
        return capacity;
    }

    public boolean canAdd(Products products){
        if (capacity+products.getCapacity()<=30) return true;
        return false;
    }
    public static void setCapacity(int capacity) {
        WareHouse.capacity = capacity;
    }
}
