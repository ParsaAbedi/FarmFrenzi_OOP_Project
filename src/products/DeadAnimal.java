package products;

import animals.Type;
import buildings.Capacity;

public class DeadAnimal extends Products{
    public DeadAnimal(int cost) {
        super(cost, Capacity.WILDANIMALS, Type.WILD);
    }
}
