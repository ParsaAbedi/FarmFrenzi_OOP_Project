package animals;

import others.FarmPosition;

public class Cat extends Animal {
    private final int PRICE=150;
    private FarmPosition farmPosition = new FarmPosition();
    private directions directions;

    public Cat(FarmPosition farmPosition) {
        super(1, Type.CAT, farmPosition);
    }

    public boolean move(FarmPosition target) {
        System.out.println("farmposition : {"+ moveSmart(farmPosition,target).getX()+","+moveSmart(farmPosition,target).getY()+"}");
        farmPosition.setX(moveSmart(farmPosition,target).getX());
        farmPosition.setY(moveSmart(farmPosition,target).getY());
        return true;
    }
}
