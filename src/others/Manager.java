package others;

import animals.*;
import buildings.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import products.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Manager {
    public int missionsNum = 0;
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
        readGsonUser();
        User user = new User(username,password);
        authentication.addUser(user);
        json = gson.toJson(authentication);
        if(writeGsonUser(json))
            return true;
        if(!login(username))
            return false;
        return false;
    }
    public boolean writeGsonUser(String json)
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
    public boolean readGsonUser()
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
        if(!readGsonUser())
            return false;
        player=authentication.findUser(username);
        if(player != null)
            return true;
        return false;
    }



    public boolean checkUsername(String username) {
        if(!readGsonUser())
            return false;
        if(authentication.checkUsername(username))
          return true;
        else
            return false;
    }

    public boolean checkPassword(String username , String password) {
        if(!readGsonUser())
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
        if (farmland.getWell().drainWell()){
            Logger.writeInfo("drain from well done");
            return true;
        }
        Logger.writeError("well is not empty");
        return false;
    }//DONE

    public boolean pickup(String s, String s1) {
        return false;
    }

    public boolean buy(String s) {
        //TODO
        return true;
    }
    public ArrayList<Mission> loadMissions()
    {
        ArrayList<Mission> missions = new ArrayList<>();
        String filePath = "missions.txt";
        String text = "";
        try {
            File myFile = new File(filePath);
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine())
                text+=myReader.nextLine();

        } catch (FileNotFoundException e)
        {
            e.getStackTrace();
        }
        Pattern p = Pattern.compile("[0-9]*");
        Matcher m = p.matcher(text);
        if(m.find())
        missionsNum = Integer.parseInt(m.group(0));
        p = Pattern.compile("#");
        m= p.matcher(text);
        String[] lines = text.split("@");
        for(int cnt =1 ; cnt < lines.length ; cnt ++)
        {
            if(lines[cnt].equals("#"))
            {
                cnt++;
                int missionNumber ;
                int initialCoins;
                int maxTime;
                int price;
                int wildAnimalNumber;
                int tasksNumber ;
                boolean isLocked;
               ArrayList<WildAnimal> wildAnimals = new ArrayList<>();
                HashMap<Task,Boolean> tasks = new HashMap<>();
                missionNumber = Integer.parseInt(lines[cnt]);
                //System.out.printf("mission number: %d \n", missionNumber);
                if(lines[cnt+1].equals("1"))
                    isLocked =true;
                else
                    isLocked = false;
                initialCoins = Integer.parseInt(lines[cnt+2]);
                maxTime = Integer.parseInt(lines[cnt+3]);
                price = Integer.parseInt(lines[cnt+4]);
                wildAnimalNumber = Integer.parseInt(lines[cnt+5]);
                tasksNumber = Integer.parseInt(lines[cnt+6]);
                //System.out.printf("isLocked : %b \n initial coins : %d \n maxTime : %d \n prize : %d \nwildAnimalNumber : %d \ntasks number : %d \n",isLocked,initialCoins,maxTime,price,wildAnimalNumber,tasksNumber);
                int i;
                for( i=cnt+7 ; i<cnt+7+missionNumber ; i++)
                {
                    char ch = lines[i].toCharArray()[0];
                    switch (ch){
                        case'T':
                            wildAnimals.add(new Tiger(Integer.parseInt(lines[++i])));
                            break;
                        case 'L':
                            wildAnimals.add(new Lion(Integer.parseInt(lines[++i])));
                            break;
                        case 'B':
                            wildAnimals.add(new Bear(Integer.parseInt(lines[++i])));
                            break;
                    }
                }
/*                for(WildAnimal w: wildAnimals)
                    System.out.println(w.toString());*/
                for( int j=i ; j<i+tasksNumber ; j+=2)
                {
                    boolean bool = true;
                    if(lines[j+1].equals(0))
                        bool = false;
                    tasks.put(new Task(lines[j]),bool);
                }
/*                for (HashMap.Entry<Task, Boolean> set : tasks.entrySet()) {
                    System.out.println(set.getKey().definition + " = " + set.getValue());
                }*/
                missions.add(new Mission(missionNumber,initialCoins,wildAnimals,tasks,maxTime,price));
            }
        }
        return missions;
    }
}
