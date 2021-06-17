package others;

import animals.Type;
import animals.WildAnimal;
import buildings.SewingWorkshop;
import products.Products;

import java.util.ArrayList;

public class Truck {
    private int CAPACITY=0;
    private int timeLeft;
    private ArrayList<Products> products;
    boolean onTheMove;
    private static Truck ourInstance;
    private int valueOfGoods;
    public static Truck getInstance() {
        if (ourInstance == null) {
            ourInstance = new Truck();
        }
        return ourInstance;
    }


    public Truck() {
        this.products =new ArrayList<>();
        this.onTheMove=false;
    }

    public void setOnTheMove(boolean onTheMove) {
        this.onTheMove = onTheMove;
    }

    public boolean isOnTheMove() {
        return onTheMove;
    }

    public int getCAPACITY() {
        return CAPACITY;
    }

    public ArrayList<Products> getProducts() {
        return products;
    }

    public void setCAPACITY(int CAPACITY) {
        this.CAPACITY = CAPACITY;
    }

    public boolean timePass() {
        if (this.timeLeft==1){
            this.onTheMove=false;
            this.timeLeft=0;
            return true;
        }
        this.timeLeft--;
        return false;
    }

    public int getValueOfGoods() {
        return valueOfGoods;
    }

    public void startThePath() {
        valueOfGoods=0;
        for (Products product : products) {
            valueOfGoods+=product.getCost();
        }
        this.onTheMove=true;
        this.timeLeft = 10;
    }
}
