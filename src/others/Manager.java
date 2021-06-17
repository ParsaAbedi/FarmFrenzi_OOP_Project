package others;

import animals.Animal;
import animals.DomesticAnimal;
import animals.Type;
import animals.WildAnimal;
import animals.*;
import buildings.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import products.*;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Random;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Manager {
    private Random random = new Random();
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
    private Coin coin;
    private Truck truck;
    private WareHouse wareHouse;
    private int numOfTurns;
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

    public void setCoin(Coin coin) {
        this.coin = coin;
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
    }//DONE

    public boolean login(String username) {
        if(!readGsonUser())
            return false;
        player=authentication.findUser(username);
        if(player != null)
            return true;
        return false;
    }//DONE



    public boolean checkUsername(String username) {
        if(!readGsonUser())
            return false;
        if(authentication.checkUsername(username))
          return true;
        else
            return false;
    }//DONE

    public boolean checkPassword(String username , String password) {
        if(!readGsonUser())
            return false;
        if(authentication.checkPassword(username,password))
            return true;
        else
            return false;
    }//DONE


    public boolean truckGo() {
        if (!truck.isOnTheMove()){
            truck.startThePath();
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
        if (!truck.isOnTheMove()) {
            for (Products storedProduct : wareHouse.getStoredProducts()) {
                if (storedProduct.equals(productName)) {
                    if (truck.getCAPACITY() + storedProduct.getCapacity() > 15) {
                        Logger.writeError("the product space is more than capacity");
                        return false;
                    } else {
                        truck.getProducts().add(storedProduct);
                        truck.setCAPACITY(truck.getCAPACITY() + storedProduct.getCapacity());
                        wareHouse.getStoredProducts().remove(storedProduct);
                        wareHouse.setCapacity(wareHouse.getCapacity() - storedProduct.getCapacity());
                        Logger.writeInfo("loading product successful ");
                        return true;
                    }
                }
            }
        }
        Logger.writeError("truck is on the move");
        return false;
    }

    public boolean turn(String num) {
        for (int i = 0; i < Integer.parseInt(num); i++) {
            numOfTurns++;
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
            if (!sewingWorkshop.equals(null)){
                if (sewingWorkshop.turner()){
                    sewingWorkshop.letsProduce(farmland,positionMaker(-1,-1));
                    System.out.println("your product are on the farm land");
                    Logger.writeInfo("your product are on the farm land");
                }
            }
            if (!cookieBakery.equals(null)){
                if (cookieBakery.turner()){
                    cookieBakery.letsProduce(farmland,positionMaker(-1,-1));
                    System.out.println("your product are on the farm land");
                    Logger.writeInfo("your product are on the farm land");
                }
            }
            if (!iceCreamShop.equals(null)){
                if (iceCreamShop.turner()){
                    iceCreamShop.letsProduce(farmland,positionMaker(-1,-1));
                    System.out.println("your product are on the farm land");
                    Logger.writeInfo("your product are on the farm land");
                }
            }
            if (!milkFactory.equals(null)){
                if (milkFactory.turner()){
                    milkFactory.letsProduce(farmland,positionMaker(-1,-1));
                    System.out.println("your product are on the farm land");
                    Logger.writeInfo("your product are on the farm land");
                }

            }
            if (!mill.equals(null)){
                if (mill.turner()){
                    mill.letsProduce(farmland,positionMaker(-1,-1));
                    System.out.println("your product are on the farm land");
                    Logger.writeInfo("your product are on the farm land");
                }

            }
            if (!weavingFactory.equals(null)){
                if (weavingFactory.turner()){
                    weavingFactory.letsProduce(farmland,positionMaker(-1,-1));
                    System.out.println("your product are on the farm land");
                    Logger.writeInfo("your product are on the farm land");
                }

            }
            if (truck.timePass()){
                coin.addCoins(truck.getValueOfGoods());
            }


        }
        inquiry();
        return true;
    }//KIND OF

    public boolean cage(String x, String y) {
        for (Map.Entry<FarmPosition, Animal> entry : farmland.getFarmLandAnimal().entrySet()) {
            if (entry.getKey().getX()==Integer.parseInt(x) &&entry.getKey().getY()==Integer.parseInt(y)){
             if (entry.getValue().getType()== Type.TIGER ||entry.getValue().getType()== Type.LION
                     ||entry.getValue().getType()== Type.BEAR  ){
                 if (((WildAnimal)entry.getValue()).addCage() && ((WildAnimal)entry.getValue()).caged()){
                   farmland.getFarmLandAnimal().remove(entry.getKey(),entry.getValue());
                   if (entry.getValue().getType()== Type.TIGER)
                    farmland.getFarmLandProduct().put(positionMaker(-1,-1),new DeadAnimal(500));
                   else if (entry.getValue().getType()== Type.BEAR)
                       farmland.getFarmLandProduct().put(positionMaker(-1,-1),new DeadAnimal(400));
                   else if (entry.getValue().getType()== Type.LION)
                       farmland.getFarmLandProduct().put(positionMaker(-1,-1),new DeadAnimal(300));
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

    public void inquiry() {
        System.out.println(numOfTurns);
        farmland.getFarmLandPlant().toString();
        int num = 1;
        for (Map.Entry<FarmPosition, Animal> entry : farmland.getFarmLandAnimal().entrySet()) {
            if (entry.getValue().getType() == Type.DOMESTIC) {
                if (entry.getValue().getType() == Type.HEN) {
                    System.out.println("hen " + num + " %" + entry.getValue().getLives()+
                            " ["+entry.getKey().getX()+" "+entry.getKey().getY()+" ]");
                }
                else if (entry.getValue().getType() == Type.OSTRICH) {
                    System.out.println("ostrich " + num + " %" + entry.getValue().getLives()+
                            " ["+entry.getKey().getX()+" "+entry.getKey().getY()+" ]");
                }
                else if (entry.getValue().getType() == Type.BUFFALO) {
                    System.out.println("buffalo " + num + " %" + entry.getValue().getLives()+
                            " ["+entry.getKey().getX()+" "+entry.getKey().getY()+" ]");
                }
                num++;
            }
            else if (entry.getValue().getType() == Type.HOUND) {
                System.out.println("hound " + num + " ["+entry.getKey().getX()+" "+entry.getKey().getY()+" ]");
                num++;
            }
            else if (entry.getValue().getType() == Type.CAT) {
                System.out.println("cat " + num + " ["+entry.getKey().getX()+" "+entry.getKey().getY()+" ]");
                num++;
            }
            else{
                if (entry.getValue().getType() == Type.BEAR) {
                    System.out.println("bear "  + ((WildAnimal)entry.getValue()).getFreedom()+
                            " ["+entry.getKey().getX()+" "+entry.getKey().getY()+" ]");
                }
                else if (entry.getValue().getType() == Type.LION) {
                    System.out.println("lion "  + ((WildAnimal)entry.getValue()).getFreedom()+
                            " ["+entry.getKey().getX()+" "+entry.getKey().getY()+" ]");
                }
                else if (entry.getValue().getType() == Type.TIGER) {
                    System.out.println("tiger "  + ((WildAnimal)entry.getValue()).getFreedom()+
                            " ["+entry.getKey().getX()+" "+entry.getKey().getY()+" ]");
                }
            }

        }
        for (Map.Entry<FarmPosition, Products> entry : farmland.getFarmLandProduct().entrySet()){
            switch (entry.getValue().getType()){
                case EGG:
                    System.out.println("egg ["+ entry.getKey().getX()+" "+entry.getKey().getY()+"] ");
                    break;
                case FEATHER:
                    System.out.println("feather ["+ entry.getKey().getX()+" "+entry.getKey().getY()+"] ");
                    break;
                case MILK:
                    System.out.println("milk ["+ entry.getKey().getX()+" "+entry.getKey().getY()+"] ");
                    break;
                case PASTORIZEDMILK:
                    System.out.println("pastorized milk ["+ entry.getKey().getX()+" "+entry.getKey().getY()+"] ");
                    break;
                case PIECE:
                    System.out.println("fabric ["+ entry.getKey().getX()+" "+entry.getKey().getY()+"] ");
                    break;
                case FLOUR:
                    System.out.println("flour ["+ entry.getKey().getX()+" "+entry.getKey().getY()+"] ");
                    break;
                case WILD:
                    System.out.println("dead animal ["+ entry.getKey().getX()+" "+entry.getKey().getY()+"] ");

            }
        }
        System.out.println(coin.getCoins());

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
                if (coin.buy(150)){
                    coin.setCoins(coin.getCoins()-150);
                    System.out.println("cat bought!");
                    Logger.writeInfo("cat bought!");
                    return true;
                }
                else {
                    System.err.println("insufficient amount of money");
                    Logger.writeError("insufficient amount of money");
                    return false;
                }
                case "hound":
                    if (coin.buy(100)){
                        coin.setCoins(coin.getCoins()-100);
                        System.out.println("hound bought!");
                        Logger.writeInfo("hound bought!");
                        return true;
                    }
                    else {
                        System.err.println("insufficient amount of money");
                        Logger.writeError("insufficient amount of money");
                        return false;
                    }
            case "hen":
                if (coin.buy(100)){
                    coin.setCoins(coin.getCoins()-100);
                    System.out.println("hen bought!");
                    Logger.writeInfo("hen bought!");
                    return true;
                }
                else {
                    System.err.println("insufficient amount of money");
                    Logger.writeError("insufficient amount of money");
                    return false;
                }
            case "buffalo":
                if (coin.buy(400)){
                    coin.setCoins(coin.getCoins()-400);
                    System.out.println("buffalo bought!");
                    Logger.writeInfo("buffalo bought!");
                    return true;
                }
                else {
                    System.err.println("insufficient amount of money");
                    Logger.writeError("insufficient amount of money");
                    return false;
                }
            case "ostrich":
                if (coin.buy(200)){
                    coin.setCoins(coin.getCoins()-200);
                    System.out.println("ostrich bought!");
                    Logger.writeInfo("ostrich bought!");
                    return true;
                }
                else {
                    System.err.println("insufficient amount of money");
                    Logger.writeError("insufficient amount of money");
                    return false;
                }
        }
        System.err.println("invalid input");
        Logger.writeError("invalid input");
        return false;
    }//DONE

    private FarmPosition positionMaker(int x,int y){
        if (x!=-1)return new FarmPosition(x,y);
        else return new FarmPosition(random.nextInt(6),random.nextInt(6));
    }//done



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
                coin.setCoins(initialCoins);
                missions.add(new Mission(missionNumber,initialCoins,wildAnimals,tasks,maxTime,price));
            }
        }
        numOfTurns=0;
        return missions;
    }
}
