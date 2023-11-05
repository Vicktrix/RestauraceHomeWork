package com.restaurace;

import com.restaurace.DishManager.Dish;
import com.restaurace.DishManager.DishManager;
import com.restaurace.orderManager.Order;
import com.restaurace.orderManager.OrderManager;
import java.time.LocalDateTime;
import java.util.Map;

public class Main{

    public static void main(String[] args){
        Map<Integer,Dish> myDishes=DishManager.getMyDishes();
        myDishes.forEach((k,v) -> System.out.println("key - "+k+"; val - "+v));
        
        Order order1 = new Order(12,myDishes.get(7));
        System.out.println("order = "+order1);
        System.out.println("\n\t My CookBook = \n");
        DishManager.getMyCookBook().forEach(System.out::println);
        System.out.println("\t My CookBook end \n\n");
        Order order2=new Order(3,myDishes.get(3));
        OrderManager.addOrder(order2);
        Order order3=new Order(3,myDishes.get(4));
        OrderManager.addOrder(order3);
        Order order4=new Order(5,myDishes.get(7));
        OrderManager.addOrder(order4);
        Order order5=new Order(5,myDishes.get(8));
        OrderManager.addOrder(order5);
        OrderManager.getAllOrder().forEach(System.out::println);
        System.out.println("\t\tREMOVE : ");
        //      remove orders
        OrderManager.removeOrder(order1);
        OrderManager.removeOrder(order2);
        OrderManager.removeOrder(order3);
        OrderManager.removeOrder(order4);
        OrderManager.removeOrder(order5);
        OrderManager.getAllOrder().forEach(System.out::println);
        System.out.println("\n---------TABLE 4-------------\n");
        OrderManager.addOrder(new Order(4,Menu.POLEVKA));
        OrderManager.addOrder(new Order(4,Menu.POLEVKA));
        OrderManager.addOrder(new Order(4,Menu.POLEVKA));
        OrderManager.addOrder(new Order(4,Menu.PIZZA_PEPPERONI));
        OrderManager.addOrder(new Order(4,Menu.HRANOLKY));
        OrderManager.addOrder(new Order(4,Menu.KURECI_RIZEK));
        OrderManager.addOrder(new Order(4,Menu.BEER_LARGE_10));
        OrderManager.addOrder(new Order(4,Menu.BEER_LARGE_10));
        OrderManager.getAllOrder().forEach(System.out::println);
        System.out.println("\n----------FROM FILE------------\n");
        OrderManager.getOrdersFromFile().forEach(System.out::println);
        
        //      PROJECT SCENARIO
/*        Zákazníci u stolu 15 si objednali dvakrát kuřecí řízek, dvakrát hranolky a dvakrát Kofolu. Kofolu už dostali, na řízek ještě čekají.*/
        Order rizek1 = new Order(15,Menu.KURECI_RIZEK);
        Order rizek2 = new Order(15,Menu.KURECI_RIZEK);
        Order hranolky1 = new Order(15,Menu.HRANOLKY);
        Order hranolky2 = new Order(15,Menu.HRANOLKY);
        Order kofola1 = new Order(15,Menu.KOFOLA_LARGE);
        Order kofola2 = new Order(15,Menu.KOFOLA_LARGE);
        OrderManager.addOrder(rizek1);
        OrderManager.addOrder(rizek2);
        OrderManager.addOrder(hranolky1);
        OrderManager.addOrder(hranolky2);
        OrderManager.addOrder(kofola1);
        OrderManager.addOrder(kofola2);
        //Kofolu už dostali
        kofola1.setFulfilmentTime(LocalDateTime.now());
        kofola2.setFulfilmentTime(LocalDateTime.now());
        //Vytvoř také objednávku pro stůj číslo 2.
        Order objednavka2 = new Order(2,Menu.PSTRUH_NA_VINE);
        OrderManager.addOrder(objednavka2);
        //Vypiš celkovou cenu konzumace pro stůl číslo 15.
        System.out.println("\n\n\t\tscenario table 15\n\n");
        int sum=OrderManager.getAllOrder().stream().filter(o -> o.getTable()==15)
                .mapToInt(o -> o.getDish().getPrice().intValue()).sum();
        //Použij všechny připravené metody pro získání informací pro management — údaje vypisuj na obrazovku.
        OrderManager.getAllOrder().stream().filter(o -> o.getTable()==15).forEach(System.out::println);
        System.out.println("Total cost for table 15 = "+sum);
        
    }

}
