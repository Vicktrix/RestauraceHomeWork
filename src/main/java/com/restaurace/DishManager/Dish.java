package com.restaurace.DishManager;

import java.math.BigDecimal;

public class Dish{
    private String title;
    private BigDecimal price;
    private int preparationTime;
    private String image;
    private String description;
    private int id = 0;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    public String getImage(){
        return image;
    }

    public void setImage(String image){
        this.image=image;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title=title;
    }

    public BigDecimal getPrice(){
        return price;
    }

    public void setPrice(BigDecimal price){
        this.price=price;
    }

    public int getPreparationTime(){
        return preparationTime;
    }

    public void setPreparationTime(int preparationTime) throws DishPreparationTimeException{
        this.preparationTime=preparationTime;
    //Doba přípravy musí být kladné číslo — systém nepovolí zadat záporné číslo či nulu.
        if(preparationTime<=0) throw new DishPreparationTimeException("Preparation time can`t be less than zero!");
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description=description;
    }
    @Override
    public String toString(){
        return title+", "+price+"Kc, preparationTime="+preparationTime+", description="+description+", id="+id;
    }

}