package buildings;

import animals.WildAnimal;
import products.Products;

import java.util.ArrayList;

public class Store extends Buildings {
    ArrayList<ProductiveBuilding> buildings ;


    public Store() {
        super( 0);
        this.buildings = new ArrayList<>();

    }

    boolean buy(Products product){
        //TODO
        return false;
    }
    boolean upgrade(Products product){
        //TODO
        return false;
    }
}
