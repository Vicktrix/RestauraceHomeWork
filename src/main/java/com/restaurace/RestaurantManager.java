package com.restaurace;

import com.restaurace.DishManager.Dish;
import com.restaurace.DishManager.DishManager;
import com.restaurace.DishManager.DishPreparationTimeException;
import com.restaurace.orderManager.Order;
import com.restaurace.orderManager.OrderManager;
import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
//Export seznamu objednávek pro jeden stůl ve formátu (například pro výpis na obrazovku):
    public static void showOrdersForTable(int table) {
        List<Order> list=managedOrders.stream().filter(o -> o.getTable()==table).toList();
        StringBuilder sb = new StringBuilder("\n\n** Objednávky pro stůl č.  "+table+" **\n" +
            "****");
        int numOfItem =1;
        Map<Integer,Integer> collect=list.stream().collect(
            Collectors.toMap(o -> o.getDish().getId(),o -> 1,(i1,i2) -> i1+i2));
        for(Map.Entry<Integer,Integer> entry : collect.entrySet()) {
            Dish dish = DishManager.getDishById(entry.getKey());
            sb.append("\n").append(numOfItem++).append(". ")
            .append(dish.getTitle())
            .append(" ").append(entry.getValue()).append("x ("
            +(dish.getPrice().multiply(BigDecimal.valueOf(entry.getValue()),MathContext.DECIMAL32))
            +"Kč):\t");
            Function<Order, String> mapper = o -> {
                StringBuilder str = new StringBuilder("");
                LocalDateTime orderedTime=o.getOrderedTime();
                LocalDateTime fulfilmentTime=o.getFulfilmentTime();
                Function<LocalDateTime, String> fun = l -> l==null? "null ":""+l.getHour()+":"+l.getMinute();
            return str.append(fun.apply(orderedTime)).append(" - ").append(fun.apply(fulfilmentTime)).toString();
            };
            Supplier<Stream<Order>> sup = () -> list.stream().filter(o -> o.getDish().getId()==entry.getKey());
            String paid = sup.get().allMatch(o -> o.isPaid())? " zaplaceno" : "";
            String line=sup.get()
                    .map(mapper).collect(Collectors.joining(", "))+paid;
            System.out.println(sb.append(line));
        }
    }
}
