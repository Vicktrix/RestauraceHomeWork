package com.restaurace.DishManager;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;

public class DishBuilder{
    private static URL img(String name) {
    /*Fotografie samotná je na serveru, její ukládání tedy neřešíš. Ulož pouze název souboru bez přípony*/
    name = name.replace(" ","");
        URL image = null;
        try{
    /* V systému kvůli budoucímu zobrazování nesmí být jídlo/recept bez fotografie, 
    ale na serveru je speciální fotografie s názvem blank, 
    kterou použij jako výchozí pro recepty, které zatím fotografii nemají.*/
            image = new URL("blank");  // There are url-address of Image from Server
        }catch(MalformedURLException ex){
//            temporary comented for clean terminal
//            System.out.println("Can't read image \""+name+"\" from server "+ex);
        }
        return image;
    };
    public static Dish KureciRizek() {
        Dish dish = new Dish();
        dish.setTitle("Kureci Rizek");
        dish.setDescription("Kureci rizek obalovany 150 g");
        dish.setPreparationTime(35);
        dish.setPrice(BigDecimal.valueOf(150));
        dish.setImage(img(dish.getTitle()));
        return dish;
    }
    public static Dish Hranolky() {
        Dish dish = new Dish();
        dish.setTitle("Hranolky");
        dish.setDescription("Hranolky 150 g");
        dish.setPreparationTime(20);
        dish.setPrice(BigDecimal.valueOf(80));
        dish.setImage(img(dish.getTitle()));
        return dish;
    }
    public static Dish Pstruh() {
        Dish dish = new Dish();
        dish.setTitle("Pstruh Na Vine");
        dish.setDescription("Pstruh na vine 200 g");
        dish.setPreparationTime(45);
        dish.setPrice(BigDecimal.valueOf(180));
        dish.setImage(img(dish.getTitle()));
        return dish;
    }
    public static Dish BolonskeSpagetti() {
        Dish dish = new Dish();
        dish.setTitle("Bolonske Spagetti");
        dish.setDescription("Bolonske Spagetti 200 g");
        dish.setPreparationTime(25);
        dish.setPrice(BigDecimal.valueOf(140));
        dish.setImage(img(dish.getTitle()));
        return dish;
    }
    public static Dish Polevka() {
        Dish dish = new Dish();
        dish.setTitle("Polevka ");
        dish.setDescription("Polevka zeleninova 0.3l");
        dish.setPreparationTime(25);
        dish.setPrice(BigDecimal.valueOf(140));
        dish.setImage(img(dish.getTitle()));
        return dish;
    }
    public static Dish PizzaGrande() {
        Dish dish = new Dish();
        dish.setTitle("Pizza Grande");
        dish.setDescription("ananas, ham, chees, tomatoes, mozzarela, onion, 30cm'");
        dish.setPreparationTime(15);
        dish.setPrice(BigDecimal.valueOf(150));
        dish.setImage(img(dish.getTitle()));
        return dish;
    }
    public static Dish PizzaPepperoni() {
        Dish dish = new Dish();
        dish.setTitle("Pizza Pepperoni");
        dish.setDescription("chees, tomatoes, pepperoni, mozzarela, onion, 30cm'");
        dish.setPreparationTime(16);
        dish.setPrice(BigDecimal.valueOf(150));
        dish.setImage(img(dish.getTitle()));
        return dish;
    }
    public static Dish PizzaVegan() {
        Dish dish = new Dish();
        dish.setTitle("Pizza Vegan");
        dish.setDescription("ananas, mushrooms, tomatoes, onion, 30cm'");
        dish.setPreparationTime(12);
        dish.setPrice(BigDecimal.valueOf(135));
        dish.setImage(img(dish.getTitle()));
        return dish;
    }
    public static Dish KofolaSmall() {
        Dish dish = new Dish();
        dish.setTitle("Kofola Small");
        dish.setDescription("Kofola 0.25l");
        dish.setPreparationTime(2);
        dish.setPrice(BigDecimal.valueOf(25));
        dish.setImage(img(dish.getTitle()));
        return dish;
    }
    public static Dish KofolaLarge() {
        Dish dish = new Dish();
        dish.setTitle("Kofola Large");
        dish.setDescription("Kofola 0.5l");
        dish.setPreparationTime(2);
        dish.setPrice(BigDecimal.valueOf(35));
        dish.setImage(img(dish.getTitle()));
        return dish;
    }
    public static Dish BeerSmall() {
        Dish dish = new Dish();
        dish.setTitle("Beer Small 10\"");
        dish.setDescription("Beer 10\" 0.5l");
        dish.setPreparationTime(2);
        dish.setPrice(BigDecimal.valueOf(35));
        dish.setImage(img(dish.getTitle()));
        return dish;
    }
    public static Dish BeerLarge() {
        Dish dish = new Dish();
        dish.setTitle("Beer Large 10\"");
        dish.setDescription("Beer 10\" 1.0 l");
        dish.setPreparationTime(2);
        dish.setPrice(BigDecimal.valueOf(50));
        dish.setImage(img(dish.getTitle()));
        return dish;
    }
    public static Dish WrongTimePreparationDish() {
        Dish dish = new Dish();
        dish.setTitle("Fast Dish");
        dish.setDescription("Some Fast preparations Dishes");
        dish.setPrice(BigDecimal.valueOf(50));
        dish.setImage(img(dish.getTitle()));
        dish.setPreparationTime(-2);
        return dish;
    }
}