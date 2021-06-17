package others;

import buildings.WareHouse;
import products.Bread;
import products.Products;

import java.util.ArrayList;

public class BreadTask extends Task{
    public BreadTask(String definition , int value) {
        super(definition,value);
        this.type = "Bread";
    }
    @Override
    public boolean isCompleted() {
        int num=0;
        ArrayList<Products> products = WareHouse.getStoredProducts();
        for(Products product : products)
        {
            if(product instanceof Bread)
                num++;
        }
        if(num<value)
            return false;
        else
        {
            isDone = true;
            return true;
        }
    }
    @Override
    public String showTask() {
        return super.showTask();
    }
}
