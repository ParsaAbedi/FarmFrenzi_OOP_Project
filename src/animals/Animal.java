package animals;
import others.FarmPosition;

import java.util.Random;

import static others.FarmPosition.*;

public abstract class Animal {
    static final int MAX_LIVES = 100;
    protected FarmPosition farmPosition = new FarmPosition() ;
    protected int  lives , velocity ;
    private directions moveDirection;
    private Random random = new Random();
    private Type type;

    public Type getType() {
        return type;
    }

    public Animal(int velocity , Type type,FarmPosition farmPosition) {
        this.farmPosition=farmPosition;
        this.lives = MAX_LIVES;
        this.velocity = velocity;
        setRandomMoveDirections();
        this.type=type;
    }

    public boolean move ()
    {
        setRandomMoveDirections();
        switch (moveDirection.name())
        {
            case "LEFT":
                farmPosition.setX(farmPosition.getX()-1);
                break;
            case "RIGHT":
                farmPosition.setX(farmPosition.getX()+1);
                break;
            case "UP":
                farmPosition.setY(farmPosition.getY()-1);
                break;
            case "DOWN":
                farmPosition.setY(farmPosition.getY()+1);
                break;
        }
        return true;
    }
    boolean die(){
        return true;
    }
    boolean setInitialPosition (){
        farmPosition.setRandomX();
        farmPosition.setRandomY();
        return true;
    }
    public boolean move(FarmPosition target) {
        System.out.println("farmposition : {"+ moveSmart(farmPosition,target).getX()+","+moveSmart(farmPosition,target).getY()+"}");
        farmPosition.setX(moveSmart(farmPosition,target).getX());
        farmPosition.setY(moveSmart(farmPosition,target).getY());
        return true;
    }
    public Animal() {
    }

    public boolean setRandomMoveDirections(){
        int randomNum = random.nextInt(4);
        if(farmPosition.getY()==ROWS-1 && randomNum==directions.DOWN.ordinal())
            randomNum=directions.UP.ordinal();
        else if(farmPosition.getY()==0 && randomNum==directions.UP.ordinal())
            randomNum=directions.DOWN.ordinal();
        else if(farmPosition.getX()==COLUMNS-1 && randomNum==directions.RIGHT.ordinal())
            randomNum=directions.LEFT.ordinal();
        else if(farmPosition.getX()==0 && randomNum==directions.LEFT.ordinal())
            randomNum=directions.RIGHT.ordinal();
        for(directions dir : directions.values())
        {
            if(dir.ordinal()==randomNum)
                this.moveDirection = dir;
        }
        return true;
    }
    public int getSellPrice() {
        return this.getSellPrice();
    }

    public int getLives() {
        return lives;
    }

    public void setFarmPosition(FarmPosition farmPosition1) {
        farmPosition .setX(farmPosition1.getX());
        farmPosition .setY(farmPosition1.getY());
    }

    public FarmPosition getFarmPosition() {
        return farmPosition;
    }
    protected FarmPosition moveSmart (FarmPosition obj , FarmPosition target)
    {
        FarmPosition nxt = new FarmPosition();
        if(obj.getX()>target.getX())
            nxt.setX(target.getX()+1);
        else if(obj.getX()<target.getX())
            nxt.setX(target.getX()-1);
        else if(obj.getY()<target.getY())
            nxt.setY(target.getX()-1);
        else if(obj.getY()>target.getY())
            nxt.setY(target.getY()+1);
        else
            return obj;
        return nxt;
    }

}
