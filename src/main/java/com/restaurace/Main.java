package com.restaurace;

import com.restaurace.DishManager.Dish;
import com.restaurace.DishManager.DishManager;
import java.util.Map;
import java.util.Map.Entry;

public class Main{

    public static void main(String[] args){
        Map<Integer,Dish> myDishes=DishManager.getMyDishes();
        for(Entry<Integer,Dish> e : myDishes.entrySet()) {
            System.out.println("key - "+e.getKey()+"; val -"+e.getValue().getDescription());
        }
    }

}
