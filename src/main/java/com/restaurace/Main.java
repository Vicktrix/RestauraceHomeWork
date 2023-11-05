package com.restaurace;

import com.restaurace.DishManager.Dish;
import com.restaurace.DishManager.DishManager;
import com.restaurace.orderManager.Order;
import com.restaurace.orderManager.OrderManager;
import java.util.Map;

public class Main{

    public static void main(String[] args){
        Map<Integer,Dish> myDishes=DishManager.getMyDishes();
        myDishes.forEach((k,v) -> System.out.println("key - "+k+"; val - "+v));
        
        Order order1 = new Order(12,myDishes.get(7));
        System.out.println("order = "+order1);
        System.out.println("My CookBook = \n");
//        DishManager.getMyCookBook().forEach(System.out::println);
        OrderManager.addOrder(new Order(3,myDishes.get(3)));
        OrderManager.addOrder(new Order(3,myDishes.get(4)));
        System.out.println("--- "+OrderManager.getAllOrder());
        OrderManager.addOrder(new Order(5,myDishes.get(7)));
        OrderManager.addOrder(new Order(5,myDishes.get(8)));
        OrderManager.getAllOrder().forEach(System.out::println);
    }

}
