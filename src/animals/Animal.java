package animals;
public abstract class Animal {
    private int x , y , lives , velocity ;
    private directions moveDirection ;


    public boolean move ()
    {
        return true;
    }
    boolean takeDamage(){
        return true;
    }
}
