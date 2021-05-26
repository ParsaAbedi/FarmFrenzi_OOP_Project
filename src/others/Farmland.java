package others;

import animals.Animal;
import buildings.ProductiveBuilding;
import buildings.Well;
import products.Products;

import java.util.ArrayList;
import java.util.HashMap;

public class Farmland {
    private ArrayList<Animal> animals ;
    private ArrayList<ProductiveBuilding> buildings;
   // private ArrayList<Conveyance> conveyances;
    private ArrayList<Products> products;
    private Well well;
/*    private Shop shop;*/
    HashMap <Position, Animal> farmLandAnimal  = new HashMap<Position , Animal >();
    HashMap <Position, Products> farmLandProduct = new HashMap<Position , Products>();
    HashMap <Position,Integer> farmLandPlant  = new HashMap<Position , Integer >();
}
