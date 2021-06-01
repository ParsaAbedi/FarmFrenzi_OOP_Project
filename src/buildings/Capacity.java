package buildings;

public enum Capacity {
    PRIMERY(1),
    MANUFACTURED(2),
    MAIN(4);
    private int capacity;

    Capacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }
}
