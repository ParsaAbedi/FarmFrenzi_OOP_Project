package products;

import buildings.Capacity;

public class Products {
    private int timeLeft;
    private int cost;
    private Capacity capacity;

    public Products(int cost,Capacity capacity) {
        this.cost = cost;
        this.capacity=capacity;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public int getCapacity() {
        return capacity.getCapacity();
    }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }
}
