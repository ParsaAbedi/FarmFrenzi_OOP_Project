package animals;

import others.FarmPosition;
import others.Farmland;

import java.util.Map;

public abstract class DomesticAnimal extends Animal {
    private final int  DAMAGE_PER_TIME = 10;
    private int price;
    private final int TIME;
    private int timeToCreateProduct;
    private Type kind;



    public int getSellPrice() {
        return price;
    }

   public boolean itIsTheTime(){
        if (timeToCreateProduct==1){
            timeToCreateProduct=TIME;
            return true;
        }
        timeToCreateProduct--;
        return false;
   }

    public Type getKind() {
        return kind;
    }

    public DomesticAnimal(int price, int timeToCreateProduct, Type kind,FarmPosition farmPosition) {
        super(1,Type.DOMESTIC, farmPosition);
        this.price = price;
        this.TIME = timeToCreateProduct;
        this.timeToCreateProduct=timeToCreateProduct;
        this.kind=kind;
    }

    public boolean eat(Farmland farmland){

        for (Map.Entry<FarmPosition, Integer> entry : farmland.getFarmLandPlant().entrySet()) {

            if (entry.getKey().getX()==this.getFarmPosition().getX()
             && entry.getKey().getY()==this.getFarmPosition().getY()){
                if (entry.getValue()!=0){
                    entry.setValue(entry.getValue()-1);
                    lives=100;
                    return false;
                }else {
                    if (hurt()){
                        return true;
                    }
                    else return false;
                }
            }
        }
        if (hurt()) return true;
        return false;
    }
    Object produce  (){
        return new Object();
    }

    boolean takeDamage( int damage) {
        if(lives-DAMAGE_PER_TIME <0)
        {
            lives=0;
            if(!die())
                return false;
        }
        else
            lives-=DAMAGE_PER_TIME;
        return true;
    }
    boolean hurt ()
    {
        if (lives!=10) {
            lives -= 10 ;
            return false;
        }
        lives=0;
        return true;
    }
}
