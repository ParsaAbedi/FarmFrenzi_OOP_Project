package products;

public class Products {
    private int timeLeft;
    private int cost;

    public Products(int cost) {
        this.cost = cost;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }
}
