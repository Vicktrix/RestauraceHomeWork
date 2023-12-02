package com.restaurace;

import com.restaurace.DishManager.Dish;
import com.restaurace.DishManager.DishManager;
import com.restaurace.DishManager.DishPreparationTimeException;
import com.restaurace.orderManager.Order;
import com.restaurace.orderManager.OrderManager;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class RestaurantManager{

    private static List<Order> managedOrders;
//Zadani : Kolik objednávek je aktuálně rozpracovaných a nedokončených.
    // dokončena objednavka -> každa objednavka ktera ma fulfilmentTime a zaroven je zaplacena
    public static List<Order> getDoneOrders() {
        return managedOrders.stream().filter(o -> o.isPaid()).filter(o -> o.getFulfilmentTime()!=null).toList();
    }
    public static List<Order> getNotDoneOrders() {
        return managedOrders.stream().filter(o -> !o.isPaid() || o.getFulfilmentTime()==null).toList();
    }
    
    public static List<Order> getAllManagedOrders() {
        if(managedOrders != null)
            return managedOrders;
// initializing orders for tests (only one and only at starts)
        return testFulfilmentTimeAndPaidOrders();
    }
    private static List<Order> testFulfilmentTimeAndPaidOrders() {
        Order order1 = new Order(1,Menu.POLEVKA);
        Order order2 = new Order(1,Menu.HRANOLKY);
        Order order3 = new Order(1,Menu.KURECI_RIZEK);
        Order order4 = new Order(1,Menu.KOFOLA_LARGE);
        Order order5 = new Order(1,Menu.BEER_LARGE_10);
        order4.setFulfilmentTime(LocalDateTime.now());
        order5.setFulfilmentTime(LocalDateTime.now());
        order1.setFulfilmentTime(LocalDateTime.now());
        order3.setPaid(true);
        order4.setPaid(true);
        order5.setPaid(true);
        Dish water = new Dish();
        water.setTitle("Mineral Water");
        water.setDescription("Mineral Water 0.5");
        try{
            water.setPreparationTime(2);
        }catch(DishPreparationTimeException ex){
            System.err.println(ex);
        }
        water.setImage("blank");
        water.setPrice(BigDecimal.valueOf(25));
        Dish bramborovySalat = new Dish();
        bramborovySalat.setTitle("Bramborovy Salat");
        bramborovySalat.setDescription("Bramborovy Salat velka porce");
        try{
            bramborovySalat.setPreparationTime(10);
        }catch(DishPreparationTimeException ex){
            System.err.println(ex);
        }
        bramborovySalat.setImage("blank");
        bramborovySalat.setPrice(BigDecimal.valueOf(55));
        DishManager.addNewDish(water);
        DishManager.addNewDish(bramborovySalat);
        Order order6 = new Order(3,water);
        Order order7 = new Order(3,water);
        Order order8 = new Order(3,bramborovySalat);
        Order order9 = new Order(3,bramborovySalat);
        Order order10 = new Order(4,Menu.KURECI_RIZEK);
        Order order11 = new Order(4,Menu.PIZZA_PEPPERONI);
        order6.setFulfilmentTime(LocalDateTime.now());
        order7.setFulfilmentTime(LocalDateTime.now());
        order7.setPaid(true);
        order10.setFulfilmentTime(LocalDateTime.now());
        order10.setPaid(true);
        order11.setPaid(true);
        OrderManager.addOrder(order1);
        OrderManager.addOrder(order2);
        OrderManager.addOrder(order3);
        OrderManager.addOrder(order4);
        OrderManager.addOrder(order5);
        OrderManager.addOrder(order6);
        OrderManager.addOrder(order7);
        OrderManager.addOrder(order8);
        OrderManager.addOrder(order9);
        OrderManager.addOrder(order10);
        OrderManager.addOrder(order11);
//Možnost seřadit objednávky podle času zadání.
        order1.setOrderedTime(LocalDateTime.now().minusMinutes(20));
        order2.setOrderedTime(LocalDateTime.now().minusMinutes(23));
        order3.setOrderedTime(LocalDateTime.now().minusMinutes(24));
        order4.setOrderedTime(LocalDateTime.now().minusMinutes(30));
        order5.setOrderedTime(LocalDateTime.now().minusMinutes(23));
        order6.setOrderedTime(LocalDateTime.now().minusMinutes(17));
        order7.setOrderedTime(LocalDateTime.now().minusMinutes(10));
        order8.setOrderedTime(LocalDateTime.now().minusMinutes(70));
        order9.setOrderedTime(LocalDateTime.now().minusMinutes(75));
        order10.setOrderedTime(LocalDateTime.now().minusMinutes(10));
        order11.setOrderedTime(LocalDateTime.now().minusMinutes(15));
        managedOrders = OrderManager.getAllOrder();
        return managedOrders;
    }
}
