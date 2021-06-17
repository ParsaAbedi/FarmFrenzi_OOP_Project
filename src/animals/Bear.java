package animals;

public class Bear extends WildAnimal{


    public Bear(int enteranceTime) {
        super(enteranceTime);
    }

    public Bear(int velocity, int freedom, int cageTimes) {
        super(1, 4, 400,0,Type.BEAR);
    }
}
