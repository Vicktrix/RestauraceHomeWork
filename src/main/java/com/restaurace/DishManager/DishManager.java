package com.restaurace.DishManager;

import java.util.HashMap;
import java.util.Map;

public class DishManager{
 //Mnohem lepší nápad je přiřadit jednotlivým jídlům čísla a do objednávek ukládat vždy jen číslo jídla.
// map Dish.ID -> Dish
    private static final Map<Integer,Dish> myDishes = new HashMap<>();
    static {
        myDishes.put(1,DishBuilder.BeerLarge());
        myDishes.put(2,DishBuilder.BeerSmall());
        myDishes.put(3,DishBuilder.BolonskeSpagetti());
        myDishes.put(4,DishBuilder.Hranolky());
        myDishes.put(5,DishBuilder.KofolaLarge());
        myDishes.put(6,DishBuilder.KofolaSmall());
        myDishes.put(7,DishBuilder.KureciRizek());
        myDishes.put(8,DishBuilder.PizzaGrande());
        myDishes.put(9,DishBuilder.PizzaPepperoni());
        myDishes.put(10,DishBuilder.PizzaVegan());
        myDishes.put(11,DishBuilder.Polevka());
        myDishes.put(12,DishBuilder.Pstruh());
        myDishes.put(13,DishBuilder.WrongTimePreparationDish());
    }
    public static Map<Integer,Dish> getMyDishes(){
        return myDishes;
    }
    
}
