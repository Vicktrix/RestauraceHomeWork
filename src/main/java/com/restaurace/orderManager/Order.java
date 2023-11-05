package com.restaurace.orderManager;

import com.restaurace.DishManager.Dish;
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

    public Order(){
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
        return "Order{"+"table="+table+", dish="+dish+", orderedTime="+orderedTime
                +", fulfilmentTime="+fulfilmentTime+", paid="+paid+'}';
    }
}