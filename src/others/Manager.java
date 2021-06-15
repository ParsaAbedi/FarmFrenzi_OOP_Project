package others;

import animals.Animal;
import animals.DomesticAnimal;
import animals.Type;
import animals.WildAnimal;
import buildings.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import products.*;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

public class Manager {
    private Random random = new Random();
    private Farmland farmland ;
    private SewingWorkshop sewingWorkshop;
    private CookieBakery cookieBakery;
    private IceCreamShop iceCreamShop;
    private MilkFactory milkFactory;
    private Mill mill;
    private WeavingFactory weavingFactory;
    public Authentication authentication = new Authentication();
     ObjectMapper mapper = new ObjectMapper();
    private User player ;
    private String json ;
    private Gson gson = new Gson();

    private Truck truck;
    private WareHouse wareHouse;

    public Manager() {
        this.farmland = new Farmland();
        this.sewingWorkshop = SewingWorkshop.getInstance();
        this.cookieBakery = CookieBakery.getInstance();
        this.iceCreamShop = IceCreamShop.getInstance();
        this.milkFactory = MilkFactory.getInstance();
        this.mill = Mill.getInstance();
        this.weavingFactory = WeavingFactory.getInstance();
        this.truck=Truck.getInstance();
        this.wareHouse= WareHouse.getInstance();
    }

    public boolean signup(String username , String password)
    {
        readGson();
        User user = new User(username,password);
        authentication.addUser(user);
        json = gson.toJson(authentication);
        if(writeGson(json))
            return true;
        if(!login(username))
            return false;
        return false;
    }//DONE
    public boolean writeGson (String json)
    {
        try {
            PrintWriter pw = new PrintWriter("users.json");
            pw.write(json);
            pw.flush();
            pw.close();
            return true;
        }catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }//DONE
    public boolean readGson()
    {
        try {
            authentication  = gson.fromJson(new FileReader("users.json"),Authentication.class);
            return true;
        }catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }//DONE

    public boolean login(String username) {
        if(!readGson())
            return false;
        player=authentication.findUser(username);
        if(player != null)
            return true;
        return false;
    }//DONE



    public boolean checkUsername(String username) {
        if(!readGson())
            return false;
        if(authentication.checkUsername(username))
          return true;
        else
            return false;
    }//DONE

    public boolean checkPassword(String username , String password) {
        if(!readGson())
            return false;
        if(authentication.checkPassword(username,password))
            return true;
        else
            return false;
    }//DONE

    public boolean truckGo() {
        if (!truck.isOnTheMove()){
            truck.setOnTheMove(true);
            Logger.writeInfo("truck started its own path");
            return true;
        }
        Logger.writeInfo("truck is already on the roads!");
        return false;
    }//DONE

    public boolean unloadTruck(String productName) {
        if (!truck.isOnTheMove()){
            for (Products storedProduct : truck.getProducts()) {
                if (storedProduct.equals(productName)){
                    if (wareHouse.getCapacity()+storedProduct.getCapacity()>30){
                        Logger.writeError("the product space is more than capacity and we can not unload it");
                        return false;
                    }
                    else {
                        truck.getProducts().remove(storedProduct);
                        truck.setCAPACITY(truck.getCAPACITY()-storedProduct.getCapacity());
                        wareHouse.getStoredProducts().remove(storedProduct);
                        wareHouse.setCapacity(wareHouse.getCapacity()+storedProduct.getCapacity());
                        Logger.writeInfo("unload product successful ");
                        return true;
                    }
                }
            }
            Logger.writeError("you do not have this product in your truck");
            return false;
        }
        Logger.writeError("truck is on the move");
        return false;

    }//DONE

    public boolean loadTruck(String productName) {
        if (!truck.isOnTheMove()){
            for (Products storedProduct : wareHouse.getStoredProducts()) {
                if (storedProduct.equals(productName)){
                    if (truck.getCAPACITY()+storedProduct.getCapacity()>15){
                        Logger.writeError("the product space is more than capacity");
                        return false;
                    }
                    else {
                        truck.getProducts().add(storedProduct);
                        truck.setCAPACITY(truck.getCAPACITY()+storedProduct.getCapacity());
                        wareHouse.getStoredProducts().remove(storedProduct);
                        wareHouse.setCapacity(wareHouse.getCapacity()-storedProduct.getCapacity());
                        Logger.writeInfo("loading product successful ");
                        return true;
                    }
                }
            }
            Logger.writeError("you do not have this product in your warehouse");
            return false;
        }
        Logger.writeError("truck is on the move");
        return false;
    }//DONE

    public boolean turn(String num) {
        for (int i = 0; i < Integer.parseInt(num); i++) {

            for (Map.Entry<FarmPosition, Animal> entry : farmland.getFarmLandAnimal().entrySet()) {
                if (entry.getValue().getType()==Type.DOMESTIC){
                    if (((DomesticAnimal)entry.getValue()).itIsTheTime()){
                        switch (((DomesticAnimal)entry.getValue()).getKind()){
                            case OSTRICH:
                                farmland.getFarmLandProduct().put(positionMaker(-1,-1),new Feather());
                                System.out.println("a feather produced");
                                Logger.writeInfo("a feather produced");
                                break;
                            case BUFFALO:
                                farmland.getFarmLandProduct().put(positionMaker(-1,-1),new Milk());
                                System.out.println("a milk produced");
                                Logger.writeInfo("a milk produced");
                                break;
                            case HEN:
                                farmland.getFarmLandProduct().put(positionMaker(-1,-1),new Egg());
                                System.out.println("a egg produced");
                                Logger.writeInfo("a egg produced");
                                break;
                        }
                    }
                }
            }
            for (Map.Entry<FarmPosition, Products> entry : farmland.getFarmLandProduct().entrySet()) {
                    if (entry.getValue().timeChecker()){
                        farmland.getFarmLandProduct().remove(entry.getValue());
                        System.out.println("a product is gone");
                        Logger.writeInfo("a product is gone");
                    }
            }
            if (farmland.getBuildings().contains(sewingWorkshop)){
                if (sewingWorkshop.turner()){
                    sewingWorkshop.letsProduce(farmland,positionMaker(-1,-1));
                    System.out.println("your product are on the farm land");
                    Logger.writeInfo("your product are on the farm land");
                }
            }
            if (farmland.getBuildings().contains(cookieBakery)){
                if (cookieBakery.turner()){
                    cookieBakery.letsProduce(farmland,positionMaker(-1,-1));
                    System.out.println("your product are on the farm land");
                    Logger.writeInfo("your product are on the farm land");
                }
            }
            if (farmland.getBuildings().contains(iceCreamShop)){
                if (iceCreamShop.turner()){
                    iceCreamShop.letsProduce(farmland,positionMaker(-1,-1));
                    System.out.println("your product are on the farm land");
                    Logger.writeInfo("your product are on the farm land");
                }
            }
            if (farmland.getBuildings().contains(milkFactory)){
                if (milkFactory.turner()){
                    milkFactory.letsProduce(farmland,positionMaker(-1,-1));
                    System.out.println("your product are on the farm land");
                    Logger.writeInfo("your product are on the farm land");
                }

            }
            if (farmland.getBuildings().contains(mill)){
                if (mill.turner()){
                    mill.letsProduce(farmland,positionMaker(-1,-1));
                    System.out.println("your product are on the farm land");
                    Logger.writeInfo("your product are on the farm land");
                }

            }
            if (farmland.getBuildings().contains(weavingFactory)){
                if (weavingFactory.turner()){
                    weavingFactory.letsProduce(farmland,positionMaker(-1,-1));
                    System.out.println("your product are on the farm land");
                    Logger.writeInfo("your product are on the farm land");
                }

            }


        }
        return true;
    }//KIND OF

    public boolean cage(String x, String y) {
        for (Map.Entry<FarmPosition, Animal> entry : farmland.getFarmLandAnimal().entrySet()) {
            if (entry.getKey().getX()==Integer.parseInt(x) &&entry.getKey().getY()==Integer.parseInt(y)){
             if (entry.getValue().getType()== Type.WILD ){
                 if (((WildAnimal)entry.getValue()).addCage() && ((WildAnimal)entry.getValue()).caged()){
                   farmland.getFarmLandAnimal().remove(entry.getKey(),entry.getValue());
                     System.out.println("it is trapped");
                     Logger.writeInfo("it is trapped");
                     return true;
                 }
                 else if (((WildAnimal)entry.getValue()).addCage()){
                     System.out.println("Caged installed successfully");
                     Logger.writeInfo("Caged installed successfully");
                     return true;
                 }
             }
            }
        }
        System.err.println("there is not a wild one in this place");
        Logger.writeError("there is not a wild one in this place");
        return false;
    }//DONE

    public boolean work(String workShopName) {
        workShopName = workShopName.trim();
        workShopName= workShopName.toLowerCase();
        if (Pattern.matches("^cookie\\s*bakery$" , workShopName)
                && farmland.getBuildings().contains(cookieBakery)){
            if (this.cookieBakery.initiateProduce(5,wareHouse)){
                Logger.writeInfo("cookie bakery is initilized");
                return true;
            }
        }
        else if (Pattern.matches("^icecream\\s*shop$" , workShopName)&&
                farmland.getBuildings().contains(iceCreamShop)){
            if (this.iceCreamShop.initiateProduce(7,wareHouse)){
                Logger.writeInfo("ice cream shop is initilized");
                return true;
            }
        }
        else if (Pattern.matches("^milk\\s*factory$" , workShopName)&&
                farmland.getBuildings().contains(milkFactory)){
            if (this.milkFactory.initiateProduce(6,wareHouse)){
                Logger.writeInfo("milk factory is initilized");
                return true;
            }
        }
        else if (Pattern.matches("^mill$" , workShopName)&&
                farmland.getBuildings().contains(mill)){
            if (this.mill.initiateProduce(4,wareHouse)){
                Logger.writeInfo("mill is initilized");
                return true;
            }
        }
        else if (Pattern.matches("^sewing\\s*workshop$" , workShopName) &&
                farmland.getBuildings().contains(sewingWorkshop)){
            if (this.sewingWorkshop.initiateProduce(6,wareHouse)){
                Logger.writeInfo("sewing workshop is initilized");
                return true;
            }
        }
        else if (Pattern.matches("^weaving\\s*factory$" , workShopName)&&
                farmland.getBuildings().contains(weavingFactory)){
            if (this.weavingFactory.initiateProduce(5,wareHouse)){
                Logger.writeInfo("weaving factory is initilized");
                return true;
            }
        }
        Logger.writeError("you ve got the wrong name or this building is not build yet");
        return false;
    }//DONE

    public boolean upgrade(String workShopName) {
        workShopName = workShopName.trim();
        workShopName= workShopName.toLowerCase();
        if (Pattern.matches("^cookie\\s*bakery$" , workShopName) &&
                farmland.getBuildings().contains(cookieBakery)){
            if (this.cookieBakery.upgrade()){
                Logger.writeInfo("cookie bakery upgraded");
                return true;
            }
        }
        else if (Pattern.matches("^icecream\\s*shop$" , workShopName)&&
                farmland.getBuildings().contains(iceCreamShop)){
            if (this.iceCreamShop.upgrade()){
                Logger.writeInfo("ice cream shop upgraded");
                return true;
            }
        }
        else if (Pattern.matches("^milk\\s*factory$" , workShopName)&&
                farmland.getBuildings().contains(milkFactory)){
            if (this.milkFactory.upgrade()){
                Logger.writeInfo("milk factory upgraded");
                return true;
            }
        }
        else if (Pattern.matches("^mill$" , workShopName)&&
                farmland.getBuildings().contains(mill)){
            if (this.mill.upgrade()){
                Logger.writeInfo("mill upgraded");
                return true;
            }
        }
        else if (Pattern.matches("^sewing\\s*workshop$" , workShopName) &&
                farmland.getBuildings().contains(sewingWorkshop)){
            if (this.sewingWorkshop.upgrade()){
                Logger.writeInfo("sewing workshop upgraded");
                return true;
            }
        }
        else if (Pattern.matches("^weaving\\s*factory$" , workShopName)&&
                farmland.getBuildings().contains(weavingFactory)){
            if (this.weavingFactory.upgrade()){
                Logger.writeInfo("weaving factory upgraded");
                return true;
            }
        }
        Logger.writeError("you ve got the wrong name or this building is not build yet or your biulding level is MAX");
        return false;
    }//DONE

    public boolean build(String workShopName) {
        workShopName = workShopName.trim();
        workShopName= workShopName.toLowerCase();
        if (Pattern.matches("^cookie\\s*bakery$" , workShopName) &&
                !farmland.getBuildings().contains(cookieBakery)){
            Logger.writeInfo("cookie bakery built");
            farmland.getBuildings().add(cookieBakery);
            return true;
        }
        else if (Pattern.matches("^icecream\\s*shop$" , workShopName)&&
                !farmland.getBuildings().contains(iceCreamShop)){
            Logger.writeInfo("ice cream shop built");
            farmland.getBuildings().add(iceCreamShop);
            return true;
        }
        else if (Pattern.matches("^milk\\s*factory$" , workShopName)&&
                !farmland.getBuildings().contains(milkFactory)){
            Logger.writeInfo("milk factory built");
            farmland.getBuildings().add(milkFactory);
            return true;
        }
        else if (Pattern.matches("^mill$" , workShopName)&&
                !farmland.getBuildings().contains(mill)){
            Logger.writeInfo("mill built");
            farmland.getBuildings().add(mill);
            return true;
        }
        else if (Pattern.matches("^sewing\\s*workshop$" , workShopName) &&
                !farmland.getBuildings().contains(sewingWorkshop)){
            Logger.writeInfo("sewing workshop built");
            farmland.getBuildings().add(sewingWorkshop);
            return true;
        }
        else if (Pattern.matches("^weaving\\s*factory$" , workShopName)&&
                !farmland.getBuildings().contains(sewingWorkshop)){
            Logger.writeInfo("weaving factory built");
            farmland.getBuildings().add(weavingFactory);
            return true;
        }
        Logger.writeError("you ve got the wrong name or this building is built yet");
        return false;
    }//DONE

    public boolean plant(String x, String y) {
        if (farmland.getWell().waterPlants()){
            int num = farmland.giveTheNumberOfPlants(positionMaker(Integer.parseInt(x),Integer.parseInt(y)));
            farmland.getFarmLandPlant().remove(positionMaker(Integer.parseInt(x),Integer.parseInt(y)),num);
            farmland.getFarmLandPlant().put(positionMaker(Integer.parseInt(x),Integer.parseInt(y)), num+1);
            System.out.println("planted");
            Logger.writeInfo("planted");
            return true;
        }
        System.err.println("well does not have water");
        Logger.writeError("well does not have water");
        return false;
    }//DONE

    public boolean well() {
        if (farmland.getWell().drainWell()){
            Logger.writeInfo("drain from well done");
            return true;
        }
        Logger.writeError("well is not empty");
        return false;
    }//DONE

    public boolean pickup(String x, String y) {
        for (Map.Entry<FarmPosition, Products> entry : farmland.getFarmLandProduct().entrySet()) {
            if (entry.getKey().equals(positionMaker(Integer.parseInt(x), Integer.parseInt(y)))) {
                if (!entry.getValue().equals(null)) {
                    if (wareHouse.canAdd(entry.getValue())) {
                        wareHouse.getStoredProducts().add(entry.getValue());
                        farmland.getFarmLandProduct().remove(entry.getKey(), entry.getValue());
                        System.out.println("picked");
                        Logger.writeInfo("picked");
                        return true;
                    }
                    else {
                        System.err.println("warehouse does not have capacity");
                        Logger.writeError("warehouse does not have capacity");
                        return false;
                    }
                }
                else {
                    System.err.println("there is no product here");
                    Logger.writeError("there is no product here");
                    return false;
                }
            }
        }
        return false;
    }//DONE

    public boolean buy(String name) {
        name=name.toLowerCase();
        switch (name){
            case "cat":
            
                break;
            case "hound":

                break;
            case "hen":

                break;
            case "buffalo":

                break;
            case "ostrich":

                break;
        }
        return true;
    }

    private FarmPosition positionMaker(int x,int y){
        if (x!=-1)return new FarmPosition(x,y);
        else return new FarmPosition(random.nextInt(6),random.nextInt(6));
    }



}
