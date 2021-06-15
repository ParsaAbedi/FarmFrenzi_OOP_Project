package animals;

public class Bear extends WildAnimal{

    private int cageTimes;

    public Bear(int enteranceTime) {
        super(enteranceTime);
    }

    public Bear(int velocity, int freedom, int cageTimes) {
        super(1, 4,400);
        this.cageTimes = 0;
    }

    public boolean caged(){
        if (this.cageTimes>=this.getFreedom())
            return true;
        else return false;
    }
    public boolean addCage(){
        this.cageTimes++;
        if (caged()) return true;
        else return false;
    }
}
