/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.restaurace;

import com.restaurace.DishManager.Dish;
import com.restaurace.DishManager.DishBuilder;
import com.restaurace.DishManager.DishManager;
import com.restaurace.DishManager.DishPreparationTimeException;
import com.restaurace.orderManager.Order;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;

/**
Solution some test's problem :
https://maven.apache.org/surefire/maven-surefire-plugin/examples/pojo-test.html
https://stackoverflow.com/questions/49441049/junit-5-does-not-execute-method-annotated-with-beforeeach
https://www.appsloveworld.com/java/100/17/junit-5-does-not-execute-method-annotated-with-beforeeach
 */
public class RestaurantManagerTest{

//private List<Order> list = new ArrayList<>();
private static List<Order> list = new ArrayList<>();

//    @BeforeEach
    @BeforeAll
//    public void testCreateTestOrders() {
    public static void testCreateTestOrders() {
        LocalDateTime now=LocalDateTime.now();
//hranolky
        Dish hranolky=DishBuilder.Hranolky();
//kureciRizek
        Dish kureciRizek=DishBuilder.KureciRizek();
//kofolaLarge
        Dish kofolaLarge=DishBuilder.KofolaLarge();
//water
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
//bramborovySalat
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
        DishManager.addNewDishTest(water);
        DishManager.addNewDishTest(bramborovySalat);
        
        Order order1 = new Order(1,water);
        order1.setPaid(true);
        order1.setOrderedTime(now.minusMinutes(5));
        order1.setFulfilmentTime(now);
        list.add(order1);
        
        Order order2 = new Order(1,water);
        order2.setPaid(true);
        order2.setOrderedTime(now.minusMinutes(3));
        order2.setFulfilmentTime(now);
        list.add(order2);
        
        Order order3 = new Order(1,kofolaLarge);
        order3.setPaid(true);
        order3.setOrderedTime(now.minusMinutes(4));
        order3.setFulfilmentTime(now);
        list.add(order3);
        
        Order order4 = new Order(1,Menu.BEER_LARGE_10);
        order4.setOrderedTime(now.minusMinutes(7));
        order4.setFulfilmentTime(now);
        list.add(order4);
        
        Order order5 = new Order(1,hranolky);
        order5.setOrderedTime(now.minusMinutes(5));
        list.add(order5);
        
        Order order6 = new Order(1,kureciRizek);
        order6.setOrderedTime(now.minusMinutes(3));
        list.add(order6);
        
        Order order7 = new Order(1,kureciRizek);
        order7.setOrderedTime(now.minusMinutes(3));
        list.add(order7);
        
        Order order8 = new Order(1,bramborovySalat);
        order8.setOrderedTime(now);
        list.add(order8);
        
        Order order9 = new Order(1,Menu.PIZZA_GRANDE);
        order9.setOrderedTime(now.minusMinutes(1));
        list.add(order9);
        
        Order order10 = new Order(1,Menu.POLEVKA);
        order10.setOrderedTime(now.minusMinutes(6));
        order10.setFulfilmentTime(now);
        list.add(order10);   
    }

    @Test
//    public void testNumberOfNotCompleteOrders_withSimpleInitialize(){
    public void numberOfNotCompleteOrders_withSimpleInitialize(){
        RestaurantManager restaurantManager = new RestaurantManager(list);
        // size(10) - water, water, kofolaLarge = 7;
        long expected = 7;
        assertEquals(expected,restaurantManager.numberOfNotCompleteOrders());
    }
    
    @Test
//    public void testNumberOfNotCompleteOrders_withAddedCompleteOrders(){
    public void numberOfNotCompleteOrders_withAddedCompleteOrders(){
        Predicate<Order> polevka = o -> o.getDish().getTitle().equals(DishBuilder.Polevka().getTitle());
        Predicate<Order> pizzaGrande = o -> o.getDish().getTitle().equals(DishBuilder.PizzaGrande().getTitle());
        Predicate<Order> potatoSalad = o -> o.getDish().getTitle().equals("Bramborovy Salat");
        list.stream()
//                .filter(o -> polevka.test(o) || pizzaGrande.test(o))
                .filter(o -> polevka.test(o) || pizzaGrande.test(o) || potatoSalad.test(o))
            .forEach(o -> {   o.setPaid(true);
                    o.setFulfilmentTime(LocalDateTime.now());});
        RestaurantManager restaurantManager = new RestaurantManager(list);
        //size(10) - water, water, kofolaLarge, polevka, pizzaGrande = 5;
        //size(10) - water, water, kofolaLarge, polevka, pizzaGrande, potatoSalad = 4;
//        long expected = 5;
        long expected = 4;
        assertEquals(expected,restaurantManager.numberOfNotCompleteOrders());
    }
    
    @Test
    public void sortListOfOrderByOrderedTime(){
        RestaurantManager restaurantManager=new RestaurantManager(list);
        StringBuilder before = new StringBuilder();
        StringBuilder after = new StringBuilder();
        String name = null;
        System.out.println("Order list before sorting");
        Function<Order, String> time = o -> ""+o.getOrderedTime().getHour()+":"
                +o.getOrderedTime().getMinute();
        for( Order order : list) {
            name = order.getDish().getTitle();
            before.append(name);
            System.out.print(name+", ordered by : "+time.apply(order)+";\t");
        }
        System.out.println();
        restaurantManager.sortListOfOrderByOrderedTime(list);
        System.out.println("Order list after sorting");
        for( Order order : list) {
            name = order.getDish().getTitle();
            after.append(name);
            System.out.print(name+", ordered by : "+time.apply(order)+";\t");
        }
        System.out.println();
        assertNotEquals(before.toString(),after.toString());
    }

    @Test
    public void averageWorkingTimeToOrders_useDeltaForDouble(){
        RestaurantManager restaurantManager=new RestaurantManager(list);
        Function<Order, LocalDateTime> orderedTime = o -> o.getOrderedTime();
        Function<Order, LocalDateTime> fulfilmentTime = o -> o.getFulfilmentTime();
        // saved old orderedTimes, fulfilmentTime and replace them after ends test 
        // because our tests starts independent
        LocalDateTime[] oldOrdered = list.stream().map(orderedTime).toArray(LocalDateTime[]::new);
        LocalDateTime[] oldFulfilment = list.stream().map(fulfilmentTime).toArray(LocalDateTime[]::new);
        Boolean[] isPaid=list.stream().map(o -> o.isPaid()).toArray(Boolean[]::new);
        LocalDateTime now = LocalDateTime.now();
        // emulating different time for different orders;
        // shift emulating does shift average
        int shift = 5;
        int[] emulating = {1 +shift};
        // setOrderedTime : 1, 2, 3... numberOfCompletedOrders(size)
        // sum = numberOfCompletedOrders*(numberOfCompletedOrders+1) / 2
        // average = sum / numberOfCompletedOrders = (numberOfCompletedOrders+1) / 2.0
        list.stream().forEach(o -> {    o.setFulfilmentTime(now); o.setPaid(true);
            o.setOrderedTime(now.minusMinutes(emulating[0]++));});
        double expected = (list.size()+1)/2.0 + shift;
        assertEquals(expected,restaurantManager.averageWorkingTimeToOrders(), 0.1);
        int[] iterator = {0};
        // roll back data after test
        list.stream().forEach(o -> {    o.setFulfilmentTime(oldFulfilment[iterator[0]]);
            o.setOrderedTime(oldOrdered[iterator[0]]);
            o.setPaid(isPaid[iterator[0]++]);});
    }

    @Test
    public void setOfOrderedDishes(){
        RestaurantManager restaurantManager=new RestaurantManager(list);
        String expected = "< Beer Large 10\", Bramborovy Salat, Hranolky, "
                + "Kofola Large, Kureci Rizek, Mineral Water, Pizza Grande, Polevka >";
        // we sort list by Dishes name
        // because our tests starts and sorts independent
        String result=restaurantManager.listOfOrderedDishes().stream().map(d -> d.getTitle())
                .sorted().collect(Collectors.joining(", ","< "," >"));
        assertEquals(expected,result);
    }

    @Test
//    @Disabled
    public void listOfOrdersforTableInRequiredFormat_defaultScenario(){
        // separateOrdersForThisTestScenario named shorter as 'newList'
        ArrayList<Order> newList=new ArrayList<>();
//        RestaurantManager restaurantManager=new RestaurantManager(newList);
        Dish kofolaLarge=DishBuilder.KofolaLarge();
        Dish pizzaGrande=DishBuilder.PizzaGrande();
        kofolaLarge.setPrice(BigDecimal.valueOf(32.5));
        pizzaGrande.setPrice(BigDecimal.valueOf(130));
        LocalDateTime now=LocalDateTime.now();
        LocalTime kofolaFrom=LocalTime.of(15,25);
        LocalTime kofolaTo=LocalTime.of(15,29);
        LocalTime pizzaFrom=LocalTime.of(15,29);
        LocalTime pizzaTo=LocalTime.of(16,10);
        LocalTime nanukFrom=LocalTime.of(17,12);
        LocalTime nanukTo=LocalTime.of(17,18);
        LocalDateTime orderedKofola=LocalDateTime.of(now.toLocalDate(),kofolaFrom);
        LocalDateTime fulfilmentKofola=LocalDateTime.of(now.toLocalDate(),kofolaTo);
        LocalDateTime orderedPizza=LocalDateTime.of(now.toLocalDate(),pizzaFrom);
        LocalDateTime fulfilmentPizza=LocalDateTime.of(now.toLocalDate(),pizzaTo);
        LocalDateTime orderedNanuk=LocalDateTime.of(now.toLocalDate(),nanukFrom);
        LocalDateTime fulfilmentNanuk=LocalDateTime.of(now.toLocalDate(),nanukTo);
//Nanuk Míša
        Dish nanukMisha = new Dish();
        nanukMisha.setTitle("Nanuk Misha");
        nanukMisha.setDescription("Nanuk Míša description");
        try{
            nanukMisha.setPreparationTime(5);
        }catch(DishPreparationTimeException ex){
            System.err.println(ex);
        }
        nanukMisha.setImage("blank");
        nanukMisha.setPrice(BigDecimal.valueOf(30));
        DishManager.addNewDishTest(nanukMisha);
// create orders
        Order order1=new Order(4,kofolaLarge);
        order1.setOrderedTime(orderedKofola);
        order1.setFulfilmentTime(fulfilmentKofola);
        newList.add(order1);
        Order order2=new Order(4,kofolaLarge);
        order2.setOrderedTime(orderedKofola);
        order2.setFulfilmentTime(fulfilmentKofola);
        newList.add(order2);
        Order order3=new Order(4,kofolaLarge);
        order3.setOrderedTime(orderedKofola);
        order3.setFulfilmentTime(fulfilmentKofola);
        newList.add(order3);
        Order order4=new Order(4,kofolaLarge);
        order4.setOrderedTime(orderedKofola);
        order4.setFulfilmentTime(fulfilmentKofola);
        newList.add(order4);
        Order order5=new Order(4,pizzaGrande);
        order5.setOrderedTime(orderedPizza);
        order5.setFulfilmentTime(fulfilmentPizza);
        newList.add(order5);
        Order order6=new Order(4,nanukMisha);
        order6.setOrderedTime(orderedNanuk);
        order6.setFulfilmentTime(fulfilmentNanuk);
        newList.add(order6);
        List<Order> newListSorted = newList.stream()
                .sorted((o1,o2) -> o1.getOrderedTime().toLocalTime().compareTo(o2.getOrderedTime().toLocalTime())).toList();
        newListSorted.stream().filter(o -> !o.getDish().getTitle().equalsIgnoreCase("Nanuk Misha"))
                .forEach(o -> o.setPaid(true));
        String expected = """
                          
                          
                          ** Objednávky pro stůl č.  4 **
                          ****
                          1. Kofola Large 4x (130.0Kč):	15:25 - 15:29, 15:25 - 15:29, 15:25 - 15:29, 15:25 - 15:29 zaplaceno
                          2. Nanuk Misha 1x (30Kč):	17:12 - 17:18
                          3. Pizza Grande 1x (130Kč):	15:29 - 16:10 zaplaceno""";
        RestaurantManager restaurantManager=new RestaurantManager(newListSorted);
        String result = 
                restaurantManager.listOfOrdersforTableInRequiredFormat(4);
        System.out.println(result);
        assertEquals(expected,result);
    }
    
    @Test
    public void listOfOrdersforTableInRequiredFormat_defaultTableOne(){
        RestaurantManager restaurantManager=new RestaurantManager(list);
        System.out.println(restaurantManager.listOfOrdersforTableInRequiredFormat(1));
    }

}
