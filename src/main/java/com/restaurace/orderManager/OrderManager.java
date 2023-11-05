package com.restaurace.orderManager;

import com.restaurace.DishManager.DishManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class OrderManager{  
    private static String delimiter = "; ";
    private static String ordersFile = "orders";
    //Restaurace má očíslované stoly.
    public static List<Integer> availableTables;
    static {
        availableTables = IntStream.range(1,51).boxed().toList();
    }
    private static List<Order> orders = new ArrayList<>();
    // copy of uor arders
    public static List<Order> getAllOrder() {
        return orders.stream().toList();
    }
    public static void addOrder(Order order) {
        saveOdredToFile(order);
        orders.add(order);
    }
    public static void removeOrder(Order order) {
        orders.remove(order);
    }
    private static void saveOdredToFile(Order order) {
        try(PrintWriter toFile = new PrintWriter(new FileWriter(ordersFile, true))) {
            toFile.println(parseOrderToStrInFile(order));
        } catch(IOException ex) {
          System.out.println("Error in write File orders\n "+ex);
        }
    }
    private static List<Order> readOrderFroFile(){
        List<Order> list = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(ordersFile))) {
            int numOfLine = 1;
            Order order;
            while(sc.hasNextLine()) {
                order = parseStrToOrderFromFile(sc.nextLine(),numOfLine++);
                list.add(order);
            }
        } catch(FileNotFoundException ex) {
            System.out.println("Error in read File "+ordersFile+"\n "+ex);
        }
        return list;
    }
    private static String parseOrderToStrInFile(Order order) {
        StringBuilder sb = new StringBuilder("");
        sb.append(order.getDish().getId()).append(delimiter);
        sb.append(order.getTable()).append(delimiter);
        sb.append(order.isPaid()).append(delimiter);
        sb.append(order.getFulfilmentTime()).append(delimiter);
        sb.append(order.getOrderedTime());
        return sb.toString();
    }
    private static Order parseStrToOrderFromFile(String str, int line) {
        String[] sb=str.split(delimiter);
        Order order = new Order();
        try {
        order.setDish(DishManager.getDishById(Integer.valueOf(sb[0])));
        order.setTable(Integer.valueOf(sb[1]));
        order.setPaid(Boolean.valueOf(sb[2]));
        order.setFulfilmentTime(LocalDateTime.parse(sb[3]));
        order.setOrderedTime(LocalDateTime.parse(sb[4]));
        } catch(Exception ex) {
            System.out.println("Wrong in parsing from file "+ordersFile+" to Order");
            System.out.println("In line "+line);
        }
        return order;
    }
    
}
