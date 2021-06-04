package buildings;

import others.Farmland;
import products.Products;

import java.util.ArrayList;

public class ProductiveBuilding extends Buildings{
    private Products neededProducts;
    private Products mainProduct ;
    private int level;
    private int numOfProductForSingleProduce;
    private int timeLeft;


    public ProductiveBuilding( int cost,Products neededProducts) {
        super(cost);
        this.neededProducts = neededProducts;
        this.level=1;

        this.numOfProductForSingleProduce=1;
        this.timeLeft=-1;
    }

    boolean produce(int timeLeft, Products uniqeProduct, WareHouse wareHouse, Farmland farmland){
        int num=0;
        for (Products storedProduct : wareHouse.getStoredProducts()) {
            if (storedProduct.equals(this.neededProducts)){
                if (this.level==1){
                    this.timeLeft=timeLeft;
                    this.mainProduct=uniqeProduct;
                    wareHouse.getStoredProducts().remove(this.neededProducts);
                    farmland.getProducts().add(this.mainProduct);
                }
                else if (this.level==2){
                    for (Products product : wareHouse.getStoredProducts()) {
                        if (storedProduct.equals(this.neededProducts))num++;
                    }
                    if (num==1){
                        if (timeLeft%2==0)this.timeLeft=timeLeft/2;
                        else this.timeLeft=(timeLeft/2)+1;
                        this.mainProduct=uniqeProduct;
                        wareHouse.getStoredProducts().remove(this.neededProducts);
                        farmland.getProducts().add(this.mainProduct);
                    }
                    else if (num>1){
                        this.timeLeft=timeLeft;
                        this.mainProduct=uniqeProduct;
                        wareHouse.getStoredProducts().remove(this.neededProducts);
                        wareHouse.getStoredProducts().remove(this.neededProducts);
                        farmland.getProducts().add(this.mainProduct);
                        farmland.getProducts().add(this.mainProduct);
                    }
                }
                return true;
            }
        }
        return false;
    }
    boolean upgrade(){
        if (this.level==1){
            this.level++;
            this.numOfProductForSingleProduce++;
            return true;
        }
        return false;
    }

}
