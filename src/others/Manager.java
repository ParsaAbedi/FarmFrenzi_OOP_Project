package others;

import buildings.*;
import products.*;

import java.util.regex.Pattern;

public class Manager {
    private Farmland farmland ;
    private SewingWorkshop sewingWorkshop;
    private CookieBakery cookieBakery;
    private IceCreamShop iceCreamShop;
    private MilkFactory milkFactory;
    private Mill mill;
    private WeavingFactory weavingFactory;

    public Manager() {
        this.farmland = new Farmland();
        this.sewingWorkshop = new SewingWorkshop();
        this.cookieBakery = new CookieBakery();
        this.iceCreamShop = new IceCreamShop();
        this.milkFactory = new MilkFactory();
        this.mill = new Mill();
        this.weavingFactory = new WeavingFactory();
    }

    public boolean Login(String command) {
        return true;
    }

    public boolean checkUsername(String command) {
        return true;
    }

    public boolean checkPassword(String username , String command) {
        return true;
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
