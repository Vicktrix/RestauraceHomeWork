package com.restaurace.orderManager;

import com.restaurace.DishManager.Dish;
import com.restaurace.DishManager.DishManager;
import com.restaurace.Menu;
import java.time.LocalDateTime;

public class Order{
    private int table;
    private Dish dish;
    private LocalDateTime orderedTime;
    private LocalDateTime fulfilmentTime;
    private boolean paid;

    public Order(int table,Dish dish){
        this.table=table;
        this.dish=dish;
        this.orderedTime = LocalDateTime.now();
    }
    public Order(){    }
    
    public Order(int table, Menu menu) {
        this.table=table;
        this.dish=menuMap(menu);
        this.orderedTime = LocalDateTime.now();
    }
    
    private Dish menuMap(Menu menu) {
        return switch(menu) {
            case BEER_LARGE_10 -> DishManager.getDishById(1);
            case BEER_SMALL_10 -> DishManager.getDishById(2);
            case BOLONSKE_SPAGETTI -> DishManager.getDishById(3);
            case HRANOLKY -> DishManager.getDishById(4);
            case KOFOLA_LARGE -> DishManager.getDishById(5);
            case KOFOLA_SMALL -> DishManager.getDishById(6);
            case KURECI_RIZEK -> DishManager.getDishById(7);
            case PIZZA_GRANDE -> DishManager.getDishById(8);
            case PIZZA_PEPPERONI -> DishManager.getDishById(9);
            case PIZZA_VEGAN -> DishManager.getDishById(10);
            case POLEVKA -> DishManager.getDishById(11);
            case PSTRUH_NA_VINE -> DishManager.getDishById(12);
        };
    }
    
    public void setTable(int table){
        this.table=table;
    }

    public void setDish(Dish dish){
        this.dish=dish;
    }

    public void setOrderedTime(LocalDateTime orderedTime){
        this.orderedTime=orderedTime;
    }

    public LocalDateTime getFulfilmentTime(){
        return fulfilmentTime;
    }

    public void setFulfilmentTime(LocalDateTime fulfilmentTime){
        this.fulfilmentTime=fulfilmentTime;
    }

    public boolean isPaid(){
        return paid;
    }

    public void setPaid(boolean paid){
        this.paid=paid;
    }

    public int getTable(){
        return table;
    }

    public Dish getDish(){
        return dish;
    }

    public LocalDateTime getOrderedTime(){
        return orderedTime;
    }

    @Override
    public String toString(){
        String myFulfilmentTime = fulfilmentTime==null? "null":fulfilmentTime.getHour()+":"+fulfilmentTime.getMinute();
        return "Order{"+"table="+table+", dish="+dish+", orderedTime = "+orderedTime.getHour()+":"+orderedTime.getMinute()
                +", fulfilmentTime = "+myFulfilmentTime+", paid="+paid+'}';
    }
}