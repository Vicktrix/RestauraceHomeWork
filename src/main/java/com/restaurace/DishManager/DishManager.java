package com.restaurace.DishManager;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class DishManager{
//Mnohem lepší nápad je přiřadit jednotlivým jídlům čísla a do objednávek ukládat vždy jen číslo jídla.
// map Dish.ID -> Dish
    private static final Map<Integer,Dish> myDishes = new HashMap<>();
    private static int id=0;
//Kuchaři mají připraven zásobník jídel
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
        myDishes.forEach((k,v) -> v.setId(k));
        myDishes.values().forEach(d -> writeToCookBook(d.getTitle(),"\nThere are Recept : \n\t"+d,false));
        id = myDishes.size();
    }
    public static Map<Integer,Dish> getMyDishes(){
        return myDishes;
    }
    public static Dish getDishById(int id){
        return myDishes.get(id);
    }
    public static void addNewDish(Dish dish){
        dish.setId(++id);
        writeToCookBook(dish.getTitle(),"\nThere are Recept : \n\t"+dish,false);
        myDishes.put(id,dish);
    }
    public static void removeDish(Dish dish){
        writeToCookBook(dish.getTitle(),"DELETE",false);
        myDishes.remove(dish.getId());
    }
    private static void writeToCookBook(String src, String cook, boolean appends) {
        src = "cookBook/"+src.replace(" ","").replace("\"","").trim();
        try(PrintWriter toFile = new PrintWriter(new FileWriter(src, appends))) {
            toFile.println(cook);
        } catch(IOException ex) {
          System.out.println("Error in write File "+src+"\n "+ex);
        }
    }
//Kuchaři mají připraven zásobník jídel (dish + cook book).
    public static List<String> getMyCookBook(){
        List<String> list = new ArrayList<>();
        Consumer<Path> wrap = p -> {
            try{ Files.lines(p).forEach(l -> list.add(l));
            }catch(IOException ex) { System.out.println("Wrong in read Files");};                
        };  
        try (Stream<Path> find=Files.find(Path.of("cookBook"),Integer.MAX_VALUE,(p, a) -> !a.isDirectory())) {
            find.sequential().forEach(wrap);
        } catch(IOException e) {
            System.out.println("Wrong with findind all Files");
        }
        return list;
    }
}