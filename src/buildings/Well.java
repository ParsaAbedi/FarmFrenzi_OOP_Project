package buildings;

public class Well implements time {
    private final int CAPACITY  = 5;
    private int currentCapacity;
    private static Well ourInstance;

    public static Well getInstance() {
        if (ourInstance == null) {
            ourInstance = new Well();
        }
        return ourInstance;
    }
    public Well(){
        this.currentCapacity =0;
        drainWell();
    }

    public boolean drainWell (){
        if (this.currentCapacity==0){
            this.currentCapacity=CAPACITY;
            return true;
        }
        else return false;
    }
    public boolean waterPlants(){
        if (this.currentCapacity>0)
        {
            this.currentCapacity--;
            return true;
        }
        else return false;
    }
}
