package animals;
import others.FarmPosition;
public abstract class Animal {
    private FarmPosition farmPosition ;
    private int  lives , velocity ;
    private directions moveDirection ;

    public Animal(int velocity) {
        //this.farmPosition = TODO;
        this.lives = 100;
        this.velocity = velocity;
        //this.moveDirection = TODO;
    }

    public boolean move ()
    {
        return true;
    }
    boolean die(){
        return true;
    }

}
