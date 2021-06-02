package others;

import buildings.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.*;
import com.google.gson.Gson;
import products.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
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

    public Manager() {
        this.farmland = new Farmland();
        this.sewingWorkshop = new SewingWorkshop();
        this.cookieBakery = new CookieBakery();
        this.iceCreamShop = new IceCreamShop();
        this.milkFactory = new MilkFactory();
        this.mill = new Mill();
        this.weavingFactory = new WeavingFactory();
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
        //TODO
        return true;
    }

    public boolean unloadTruck(String s) {
        //TODO
        return true;
    }

    public boolean loadTruck(String s) {
        //TODO
        return true;
    }

    public boolean turn(String s) {
        //TODO
        return true;
    }

    public boolean cage(String s, String s1) {
        //TODO
        return true;
    }
    private void done(){
        System.out.println("done");
    }
    private void error(){
        System.err.println("insufficient supply MAN!!");
    }
    public boolean work(String workShopName) {
        workShopName = workShopName.trim();
        workShopName= workShopName.toLowerCase();
        if (Pattern.matches("^cookie\\s*bakery$" , workShopName)){
            if (this.cookieBakery.produce(5,new Bread())){
                done();
            }else error();
        }else if (Pattern.matches("^icecream\\s*shop$" , workShopName)){
            if (this.iceCreamShop.produce(7,new IceCream())){
                done();
            }else error();
        }else if (Pattern.matches("^milk\\s*factory$" , workShopName)){
            if (this.milkFactory.produce(6,new PastorizedMilk())){
                done();
            }else error();
        }else if (Pattern.matches("^mill$" , workShopName)){
            if (this.mill.produce(4,new Flour())){
                done();
            }else error();
        }else if (Pattern.matches("^sewing\\s*workshop$" , workShopName)){
            if (this.sewingWorkshop.produce(6,new Clothes())){
                done();
            }else error();
        }else if (Pattern.matches("^weaving\\s*factory$" , workShopName)){
            if (this.weavingFactory.produce(5,new Piece())){
                done();
            }else error();
        }
        return true;
    }

    public boolean plant(String s, String s1) {
        //TODO
        return true;
    }

    public boolean well() {
        //TODO
        return true;
    }

    public boolean pickup(String s, String s1) {
        //TODO
        return true;
    }

    public boolean buy(String s) {
        //TODO
        return true;
    }
}
