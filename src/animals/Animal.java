package animals;
import others.FarmPosition;
public abstract class Animal {
    private FarmPosition farmPosition ;
    private int  lives , velocity ;
    private directions moveDirection ;


    public boolean move ()
    {
        return true;
    }
    boolean die(){
        return true;
    }

}
