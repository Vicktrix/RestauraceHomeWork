package com.restaurace;

import com.restaurace.DishManager.Dish;
import com.restaurace.orderManager.Order;
import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.*;
import java.util.stream.Stream;

public class RestaurantManager{

    private List<Order> allOrder;

    public RestaurantManager(List<Order> allOrder){
        this.allOrder=allOrder;
    }
    
    // dokončena objednavka -> 
    //každa objednavka ktera je zaplacena a zaroven ma fulfilmentTime
    private List<Order> listOfCompleteOrders() {
        Predicate<Order> isPaid = o -> o.isPaid();
        Predicate<Order> isFulFillment = o -> o.getFulfilmentTime() != null;
        return allOrder.stream().filter(isPaid).filter(isFulFillment).toList();
    }
    
    // 1. Kolik objednávek je aktuálně rozpracovaných a nedokončených.
    // počet rozpracovaných objednávek je rozdil mezy celkovým počtem a počtem dokončenych objednávek 
    public long numberOfNotCompleteOrders() {
        return allOrder.size() - listOfCompleteOrders().size();
    }
    // 2. Možnost seřadit objednávky podle času zadání.
    public List<Order> sortListOfOrderByOrderedTime(List<Order> myOrders) {
        Collections.sort(myOrders,(o1, o2) -> o1.getOrderedTime().compareTo(o2.getOrderedTime()));
        return myOrders;
    }
    // 3. Průměrnou dobu zpracování objednávek.
    // je to sčitani časovych period (fulfilmentTime - orderedTime) děleno na počet už zpracovaných objednavek
//    public int averageCookingTime() {
    public double averageWorkingTimeToOrders() {
        List<Order> listOfCompleteOrders=listOfCompleteOrders();
        int numOfCompleteOrders=listOfCompleteOrders.size();
        long allWorkingTimeToOrders=listOfCompleteOrders.stream()
            .mapToLong(o -> o.getOrderedTime().until(o.getFulfilmentTime(),ChronoUnit.MINUTES)).sum();
        return allWorkingTimeToOrders/(0.0+numOfCompleteOrders);
    }
    // 5. Seznam jídel, která byla dnes objednána. Bez ohledu na to, kolikrát bylo dané jídlo objednáno.
    public List<Dish> listOfOrderedDishes() {
        return allOrder.stream().map(o -> o.getDish()).distinct().toList();
    }
    // 6. Export seznamu objednávek pro jeden stůl ve formátu (například pro výpis na obrazovku):
    public String listOfOrdersforTableInRequiredFormat(int tableNum) {
        List<Order> listOfOrdersForTable=allOrder.stream().filter(o -> o.getTable()==tableNum).toList();
        Map<Dish,Long> collect=listOfOrdersForTable.stream().collect(groupingBy(Order::getDish, counting()));
        StringBuilder sb = new StringBuilder("\n\n** Objednávky pro stůl č.  "+tableNum+" **\n****");
        int numOfItem =1;
        for(Map.Entry<Dish,Long> entry : collect.entrySet()) {
            Dish dish = entry.getKey();
            long count = entry.getValue();
            sb.append("\n"+(numOfItem++)+". ").append(dish.getTitle()+" "+count+"x (")
            .append(dish.getPrice().multiply(BigDecimal.valueOf(count),MathContext.DECIMAL32)+"Kč):\t");
            Supplier<Stream<Order>> sup = () -> listOfOrdersForTable.stream().filter(o -> o.getDish().equals(dish));
            String paid = sup.get().allMatch(o -> o.isPaid())? " zaplaceno" : "";
//            sb.append(sup.get().map(timeToString).collect(Collectors.joining(", "))+paid);
            sb.append(sup.get().map(o -> {
                Function<LocalDateTime, String> fun = l -> l==null? "null ":""+l.getHour()+":"+l.getMinute();
                return fun.apply(o.getOrderedTime())+" - "+fun.apply(o.getFulfilmentTime());
            }).collect(Collectors.joining(", "))+paid);
        }
        return sb.toString();
    }
}
