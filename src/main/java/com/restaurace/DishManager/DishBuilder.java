package com.restaurace.DishManager;

import java.math.BigDecimal;

public class DishBuilder{
    private static String img(String name) {
        return name.trim().replace(" ","-");
    };
    public static Dish KureciRizek() {
        Dish dish = new Dish();
        dish.setTitle("Kureci Rizek");
        dish.setDescription("Kureci rizek obalovany 150 g");
        preparationTimeWrapException(dish,35);
        dish.setPrice(BigDecimal.valueOf(150));
        //Ulož pouze název souboru bez přípony — například bolonske-spagety-01
        dish.setImage(img(dish.getTitle()));
        return dish;
    }
    public static Dish Hranolky() {
        Dish dish = new Dish();
        dish.setTitle("Hranolky");
        dish.setDescription("Hranolky 150 g");
        preparationTimeWrapException(dish,20);
        dish.setPrice(BigDecimal.valueOf(80));
        dish.setImage(img(dish.getTitle()));
        return dish;
    }
    public static Dish Pstruh() {
        Dish dish = new Dish();
        dish.setTitle("Pstruh Na Vine");
        dish.setDescription("Pstruh na vine 200 g");
        preparationTimeWrapException(dish,45);
        dish.setPrice(BigDecimal.valueOf(180));
        dish.setImage(img(dish.getTitle()));
        return dish;
    }
    public static Dish BolonskeSpagetti() {
        Dish dish = new Dish();
        dish.setTitle("Bolonske Spagetti");
        dish.setDescription("Bolonske Spagetti 200 g");
        preparationTimeWrapException(dish,25);
        dish.setPrice(BigDecimal.valueOf(140));
        dish.setImage(img(dish.getTitle()));
        return dish;
    }
    public static Dish Polevka() {
        Dish dish = new Dish();
        dish.setTitle("Polevka");
        dish.setDescription("Polevka zeleninova 0.3l");
        preparationTimeWrapException(dish,25);
        dish.setPrice(BigDecimal.valueOf(40));
        dish.setImage(img(dish.getTitle()));
        return dish;
    }
    public static Dish PizzaGrande() {
        Dish dish = new Dish();
        dish.setTitle("Pizza Grande");
        dish.setDescription("ananas, ham, chees, tomatoes, mozzarela, onion, 30cm'");
        preparationTimeWrapException(dish,15);
        dish.setPrice(BigDecimal.valueOf(150));
        dish.setImage(img(dish.getTitle()));
        return dish;
    }
    public static Dish PizzaPepperoni() {
        Dish dish = new Dish();
        dish.setTitle("Pizza Pepperoni");
        dish.setDescription("chees, tomatoes, pepperoni, mozzarela, onion, 30cm'");
        preparationTimeWrapException(dish,16);
        dish.setPrice(BigDecimal.valueOf(150));
        dish.setImage(img(dish.getTitle()));
        return dish;
    }
    public static Dish PizzaVegan() {
        Dish dish = new Dish();
        dish.setTitle("Pizza Vegan");
        dish.setDescription("ananas, mushrooms, tomatoes, onion, 30cm'");
        preparationTimeWrapException(dish,12);
        dish.setPrice(BigDecimal.valueOf(135));
        dish.setImage(img(dish.getTitle()));
        return dish;
    }
    public static Dish KofolaSmall() {
        Dish dish = new Dish();
        dish.setTitle("Kofola Small");
        dish.setDescription("Kofola 0.25l");
        preparationTimeWrapException(dish,2);
        dish.setPrice(BigDecimal.valueOf(25));
        dish.setImage(img(dish.getTitle()));
        return dish;
    }
    public static Dish KofolaLarge() {
        Dish dish = new Dish();
        dish.setTitle("Kofola Large");
        dish.setDescription("Kofola 0.5l");
        preparationTimeWrapException(dish,2);
        dish.setPrice(BigDecimal.valueOf(35));
        dish.setImage(img(dish.getTitle()));
        return dish;
    }
    public static Dish BeerSmall() {
        Dish dish = new Dish();
        dish.setTitle("Beer Small 10\"");
        dish.setDescription("Beer 10\" 0.5l");
        preparationTimeWrapException(dish,2);
        dish.setPrice(BigDecimal.valueOf(35));
        dish.setImage(img(dish.getTitle()));
        return dish;
    }
    public static Dish BeerLarge() {
        Dish dish = new Dish();
        dish.setTitle("Beer Large 10\"");
        dish.setDescription("Beer 10\" 1.0 l");
        preparationTimeWrapException(dish,2);
        dish.setPrice(BigDecimal.valueOf(50));
        dish.setImage(img(dish.getTitle()));
        return dish;
    }
    private static void preparationTimeWrapException(Dish dish, int time) {
        try{
            dish.setPreparationTime(time);
        }catch(DishPreparationTimeException ex){
            System.err.println("In DishBuilder we have predefined Dish with time "
                + "preparation higher than zero. This exception will never happen");
        }
    }
}