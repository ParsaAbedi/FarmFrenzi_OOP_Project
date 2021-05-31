package buildings;

import products.Products;

import java.util.ArrayList;

public class ProductiveBuilding extends Buildings{
    private Products neededProducts;
    private Products mainProduct ;
    private Capacity capacity;
    private int level;
    private int numOfProductForSingleProduce;
    private int timeLeft;
    private boolean built;

    public ProductiveBuilding(int loadingTime, int cost,Capacity capacity,
                              Products neededProducts) {
        super(loadingTime, cost);
        this.neededProducts = neededProducts;
        this.capacity=capacity;
        this.level=1;
        this.built=false;
        this.numOfProductForSingleProduce=1;
        this.timeLeft=0;
    }

    public boolean isBuilt() {
        return built;
    }

    public void setBuilt(boolean built) {
        this.built = built;
    }

    boolean produce(int timeLeft, Products uniqeProduct){
        int c=0;
        for (Products storedProduct : WareHouse.getStoredProducts()) {
            if (storedProduct.equals(this.neededProducts)){
                c++;
                if (c==this.level) {
                    this.timeLeft=timeLeft;
                    this.mainProduct=uniqeProduct;
                    while (c!=0){
                        WareHouse.getStoredProducts().remove(this.neededProducts);
                        WareHouse.getStoredProducts().add(this.mainProduct);
                        c--;
                    }
                    return true;
                }
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
