package others;

import buildings.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.*;
import com.google.gson.Gson;
import products.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.regex.Pattern;

public class Manager {
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
    }
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
    }
    public boolean readGson()
    {
        try {
            authentication  = gson.fromJson(new FileReader("users.json"),Authentication.class);
            return true;
        }catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean login(String username) {
        if(!readGson())
            return false;
        player=authentication.findUser(username);
        if(player != null)
            return true;
        return false;
    }



    public boolean checkUsername(String username) {
        if(!readGson())
            return false;
        if(authentication.checkUsername(username))
          return true;
        else
            return false;
    }

    public boolean checkPassword(String username , String password) {
        if(!readGson())
            return false;
        if(authentication.checkPassword(username,password))
            return true;
        else
            return false;
    }

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

    public boolean turn(String s) {
        //TODO
        return true;
    }

    public boolean cage(String s, String s1) {
        //TODO
        return true;
    }

    public boolean work(String workShopName) {
        workShopName = workShopName.trim();
        workShopName= workShopName.toLowerCase();
        if (Pattern.matches("^cookie\\s*bakery$" , workShopName)
                && farmland.getBuildings().contains(cookieBakery)){
            if (this.cookieBakery.produce(5,new Bread(),wareHouse,farmland)){
                Logger.writeInfo("cookie bakery is initilized");
                return true;
            }
        }
        else if (Pattern.matches("^icecream\\s*shop$" , workShopName)&&
                farmland.getBuildings().contains(iceCreamShop)){
            if (this.iceCreamShop.produce(7,new IceCream(),wareHouse,farmland)){
                Logger.writeInfo("ice cream shop is initilized");
                return true;
            }
        }
        else if (Pattern.matches("^milk\\s*factory$" , workShopName)&&
                farmland.getBuildings().contains(milkFactory)){
            if (this.milkFactory.produce(6,new PastorizedMilk(),wareHouse,farmland)){
                Logger.writeInfo("milk factory is initilized");
                return true;
            }
        }
        else if (Pattern.matches("^mill$" , workShopName)&&
                farmland.getBuildings().contains(mill)){
            if (this.mill.produce(4,new Flour(),wareHouse,farmland)){
                Logger.writeInfo("mill is initilized");
                return true;
            }
        }
        else if (Pattern.matches("^sewing\\s*workshop$" , workShopName) &&
                farmland.getBuildings().contains(sewingWorkshop)){
            if (this.sewingWorkshop.produce(6,new Clothes(),wareHouse,farmland)){
                Logger.writeInfo("sewing workshop is initilized");
                return true;
            }
        }
        else if (Pattern.matches("^weaving\\s*factory$" , workShopName)&&
                farmland.getBuildings().contains(weavingFactory)){
            if (this.weavingFactory.produce(5,new Piece(),wareHouse,farmland)){
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



    public boolean plant(String s, String s1) {
        //TODO
        return true;
    }

    public boolean well() {
        if (farmland.getWell().drainWell())return true;
        return false;
    }//DONE

    public boolean pickup(String s, String s1) {
        return false;
    }

    public boolean buy(String s) {
        //TODO
        return true;
    }
}
