package animals;

public class Lion extends WildAnimal{

    private int cageTimes;
    public Lion() {
        super(1,3,300);
        cageTimes=0;
    }

    public Lion(int enteranceTime) {
        super(enteranceTime);
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
