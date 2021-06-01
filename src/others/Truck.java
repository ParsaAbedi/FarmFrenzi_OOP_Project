package others;

import buildings.SewingWorkshop;
import products.Products;

import java.util.ArrayList;

public class Truck {
    private int CAPACITY=0;
    private int timeLeft;
    private ArrayList<Products> products;
    boolean onTheMove;
    private static Truck ourInstance;

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

    public int getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }
}
