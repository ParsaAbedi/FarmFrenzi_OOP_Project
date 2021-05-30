package animals;

public class Tiger extends WildAnimal {
    private int cageTimes;
    public Tiger() {
        super(2,4,500);
        cageTimes=0;
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
