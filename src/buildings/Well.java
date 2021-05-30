package buildings;

public class Well implements time {
    private final int CAPACITY  = 5;
    private int currentCapacity;

    public Well(int currentCapacity) {
        this.currentCapacity =0;
    }

    boolean drainWell (){
        //TODO
        return false;
    }
    public boolean waterPlants(){
        if (currentCapacity>0)
        {
            this.currentCapacity--;
            return true;
        }
        else return false;
    }
    @Override
    public boolean order() {
        return false;
    }
}
