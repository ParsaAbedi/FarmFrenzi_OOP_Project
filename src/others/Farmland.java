package others;

import animals.Animal;
import buildings.ProductiveBuilding;
import buildings.Well;
import products.Products;
import javax.swing.text.Position;
import java.util.ArrayList;
import java.util.HashMap;

public class Farmland {
    private ArrayList<Animal> animals ;
    private ArrayList<ProductiveBuilding> buildings;
   // private ArrayList<Conveyance> conveyances;
    private ArrayList<Products> products;
    private Well well;
/*    private Shop shop;*/
    HashMap <FarmPosition, Animal> farmLandAnimal  = new HashMap<>();
    HashMap <FarmPosition, Products> farmLandProduct = new HashMap<>();
    HashMap <FarmPosition,Integer> farmLandPlant  = new HashMap<>();
    HashMap <FarmPosition,Boolean> farmLandGrass  = new HashMap<>();

}
