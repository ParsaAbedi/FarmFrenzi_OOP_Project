package others;

import buildings.WareHouse;
import products.Bread;
import products.Egg;
import products.Milk;
import products.Products;

import java.util.ArrayList;
import java.util.HashMap;

import static main.Main.mission;

public class EggTask extends Task{
    public EggTask(String definition , int value) {
        super(definition,value);
        this.type = "Egg";
    }

    public boolean isCompleted(WareHouse wareHouse) {
        int num=0;
        ArrayList<Products> products = wareHouse.getStoredProducts();
        for(Products product : products)
        {
            if(product instanceof Egg)
                num++;
        }

            System.out.printf(definition+" : ["+num+"/"+value+"]\n");
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
