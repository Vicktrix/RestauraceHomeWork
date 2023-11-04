package com.restaurace.DishManager;

import java.math.BigDecimal;
import java.net.URL;
import static java.math.MathContext.DECIMAL32;

public class Dish{
    private String title;
    private BigDecimal price;
    private int preparationTime;
    private URL image;
    private String description;

    public URL getImage(){
        return image;
    }

    public void setImage(URL image){
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

    public void setPreparationTime(int preparationTime){
        this.preparationTime=preparationTime;
    //Doba přípravy musí být kladné číslo — systém nepovolí zadat záporné číslo či nulu.
        if(preparationTime<=0) createDefaultDish();
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description=description;
    }
    private void createDefaultDish() {
        this.setTitle("Nepovedene jidlo ("+this.title+")");
        this.setDescription("Nepovedene jidlo ("+this.description+")");
        this.setPrice(BigDecimal.ZERO);
        this.setPreparationTime(1);
    }
}