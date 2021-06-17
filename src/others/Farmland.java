package others;

import animals.Animal;
import buildings.ProductiveBuilding;
import buildings.Well;
import products.Products;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Farmland {
    private ArrayList<Animal> animals;
    private ArrayList<ProductiveBuilding> buildings;
    // private ArrayList<Conveyance> conveyances;
    private ArrayList<Products> products;
    private Well well;
    /*    private Shop shop;*/
    private HashMap<FarmPosition, Animal> farmLandAnimal = new HashMap<>();
    private HashMap<FarmPosition, Products> farmLandProduct = new HashMap<>();
    private HashMap<FarmPosition, Integer> farmLandPlant = new HashMap<>();


    public ArrayList<ProductiveBuilding> getBuildings() {
        return buildings;
    }

    public ArrayList<Products> getProducts() {
        return products;
    }

    public Well getWell() {
        return well;
    }

    public HashMap<FarmPosition, Animal> getFarmLandAnimal() {
        return farmLandAnimal;
    }

    public HashMap<FarmPosition, Products> getFarmLandProduct() {
        return farmLandProduct;
    }

    public HashMap<FarmPosition, Integer> getFarmLandPlant() {
        return farmLandPlant;
    }

    public Farmland() {
        this.well = Well.getInstance();
    }

    public int giveTheNumberOfPlants(FarmPosition farmPosition) {
        for (Map.Entry<FarmPosition, Integer> entry : farmLandPlant.entrySet()) {
            if (entry.getKey().equals(farmPosition)) return entry.getValue();
        }
        return 0;
    }
}