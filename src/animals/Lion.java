package animals;

public class Lion extends WildAnimal {

    private int cageTimes;

    public Lion() {
        super(1, 3, 300, 0);
    }

    public Lion(int enteranceTime) {
        super(enteranceTime);
    }
}
