/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flooringmaster.dao;

import com.thesoftwareguild.flooringmaster.dto.Order;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.Scanner;

/**
 *
 * @author calarrick & kvwulp
 */
public class OrdersFileDAO implements OrdersDAO {

    private final String CONFIG_FILE = "config.properties";
    private final String DELIMITER = ",";
    private Map<Integer, Order> orderList = new HashMap();
    private boolean test = false;
    private int orderNumberTally = 0;
    private boolean testMode = false;

    private final DateTimeFormatter orderDateFormat = DateTimeFormatter.
            ofPattern("MMddyyyy");
    private final DateTimeFormatter orderDateFormatInput = DateTimeFormatter.
            ofPattern("MM/dd/yyyy");

    private ArrayList<String> datesRead = new ArrayList<>();//tracks other dates that have been loaded, 
    //these become the non-current dates from which orders are likely to be in the system
    //and the past dates for which files will be saved when a session is saved

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.orderList);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OrdersFileDAO other = (OrdersFileDAO) obj;
        if (!Objects.equals(this.orderList, other.orderList)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isTest() {
        return test;
    }

    @Override
    public void setTest(boolean test) {
        this.test = test;
    }

    @Override
    public List<Order> listAllOrders() {
        List<Order> orders = new ArrayList();
        for (Order order : orderList.values()) {
            orders.add(order);
        }

        return orders;
    }

    @Override
    public void addOrder(Order order) {
        orderList.put(order.getOrderNumber(), order);
    }

    @Override
    public Order getOrder(Integer orderNumber) {
        return orderList.get(orderNumber);
    }

    @Override
    public Order removeOrder(Integer orderNumber) {
        return orderList.remove(orderNumber);
    }

    @Override
    public Order updateOrder(Order order) {
        return orderList.replace(order.getOrderNumber(), order);
    }

    @Override
    public void readOrders(String date){
        
        try {
            loadOrders(date);
        } catch (FileNotFoundException ex) {//deal with this situation later in flow
System.out.println("No existing orders file for date.");
        }
    }
    
    
    
    private void loadOrders(String date) throws FileNotFoundException {

        datesRead.add(date);
        Scanner sc;
        if (test) {
            //junit test read
            sc = new Scanner(new BufferedReader(new FileReader("./Orders_" + date + ".txt")));
        } else {
            sc = new Scanner(new BufferedReader(new FileReader("./Orders_" + date + ".txt")));
        }

        while (sc.hasNextLine()) {
            String currentLine = sc.nextLine();
            String[] currentTokens = currentLine.split("(?<!\\\\)" + DELIMITER);

            Order order = new Order();
            order.setOrderDate(date);
            order.setOrderNumber(Integer.parseInt(currentTokens[0]));
            order.setCustomerName(currentTokens[1]);
            order.setCustomerState(currentTokens[2]);
            order.setTaxRate(Double.parseDouble(currentTokens[3]));
            order.setProductType(currentTokens[4]);
            order.setArea(Double.parseDouble(currentTokens[5]));
            order.setCostPerSquareFoot(Double.parseDouble(currentTokens[6]));
            order.
                    setLaborCostPerSquareFoot(Double.
                            parseDouble(currentTokens[7]));
            order.setMaterialCost(Double.parseDouble(currentTokens[8]));
            order.setLaborCost(Double.parseDouble(currentTokens[9]));
            order.setTax(Double.parseDouble(currentTokens[10]));
            order.setTotal(Double.parseDouble(currentTokens[11]));

            boolean check = true;

            for (Integer orderNumber : orderList.keySet()) {
                if (Objects.equals(orderNumber, order.getOrderNumber())) {
                    check = false;
                }

            }
            if (check) {
                orderList.put(order.getOrderNumber(), order);
            }

        }

        sc.close();
    }

    @Override
    public void saveSessionOrders() {
        try {
                    writeOrders();
                    cleanConfig();
                } catch (IOException e) {
                    System.out.println("There was a problem writing to a save file. (actionable step to be added later)");
                }

                

            }
        
    
    
    private void writeOrders() throws IOException {

        Map<String, List<Order>> ordersToBeSerialized = new HashMap();

        //get dates of files that have been opened and put them into the map
        for (String date : datesRead) {
            List<Order> orders = new ArrayList();
            ordersToBeSerialized.put(date, orders);
        }

        //start sorting orders in memory
        for (Order order : orderList.values()) {

            if (ordersToBeSerialized.containsKey(order.getOrderDate())) {
                //if order date exists in map, put order into that list
                ordersToBeSerialized.get(order.getOrderDate()).add(order);
            } else {
                //else if order date not in map, create new map key and value and add the order
                List<Order> orders = new ArrayList();
                orders.add(order);
                ordersToBeSerialized.put(order.getOrderDate(), orders);
            }
        }

        for (String date : ordersToBeSerialized.keySet()) {
            PrintWriter writer;
            if (test) {
                //junit test write
                writer = new PrintWriter(new FileWriter("./Test/Orders_" + date + ".txt"));
            } else {
                writer = new PrintWriter(new FileWriter("Orders_" + date + ".txt"));
            }

//        for (String date : ordersToBeSerialized.keySet()) {
//            PrintWriter writer = new PrintWriter(new FileWriter("Orders_" + date + ".txt"));
            for (Order order : ordersToBeSerialized.get(date)) {

                writer.println(order.getOrderNumber() + DELIMITER
                        + order.getCustomerName() + DELIMITER
                        + order.getCustomerState() + DELIMITER
                        + order.getTaxRate() + DELIMITER
                        + order.getProductType() + DELIMITER
                        + order.getArea() + DELIMITER
                        + order.getCostPerSquareFoot() + DELIMITER
                        + order.getLaborCostPerSquareFoot() + DELIMITER
                        + order.getMaterialCost() + DELIMITER
                        + order.getLaborCost() + DELIMITER
                        + order.getTax() + DELIMITER
                        + order.getTotal());

                writer.flush();

            }
            writer.close();

        }

        try {
            removeEmptyFiles();
        } catch (IOException ex) {
            System.out.println("Something went wrong.");
        }

    }

    private void removeEmptyFiles() throws IOException {
        File file = new File(".");

        for (File fileEntry : file.listFiles()) {
            if (!fileEntry.isDirectory() && fileEntry.getName().
                    matches("^Orders_[0-9]*\\.txt")) {
                BufferedReader br = new BufferedReader(new FileReader(fileEntry.
                        getName()));

                if (br.readLine() == null) {
                    fileEntry.delete();
                }

                br.close();
            }
        }
    }

    @Override
    public boolean checkConfiguration() {
        //check for config file
        boolean testMode = false;

        String[] configSettings = new String[2];

        try {
            configSettings = readConfig();

        } catch (FileNotFoundException ex) {
            System.out.
                    println("Error loading the configuration file. Please ensure that config.properties is in the working directory.");
        } catch (IOException ex) {
            System.out.
                    println("Error reading configuration file. Please contact help desk to reformat config file.");
        }

        if (configSettings[0].equals("true")) {
            testMode = true;
        }
        orderNumberTally = Integer.parseInt(configSettings[1]);

        this.testMode = testMode;
        return testMode;
    }

    
    private String[] readConfig() throws FileNotFoundException, IOException {

        String testMode = "";
        String orderNumberTally = "";

        Reader propReader = new BufferedReader(new FileReader(CONFIG_FILE));
        Properties props = new Properties();
        props.load(propReader);

        testMode = props.getProperty("test");
        orderNumberTally = props.getProperty("lastOrder");

        propReader.close();

        String[] configParams = new String[2];
        configParams[0] = testMode;
        configParams[1] = orderNumberTally;

        return configParams;

    }

    @Override
    public void cleanConfig() {

        if (!testMode) {
            String testModeString = String.valueOf(testMode);
            String orderNumberCount = String.valueOf(orderNumberTally);
            try {
                writeConfig(testModeString, orderNumberCount);
            } catch (IOException e) {
                System.out.
                        println("Unable to write to config.properties. Please contact help desk to make sure your file is updated to track current orders.");

            }

        }
    }

    private void writeConfig(String testMode, String orderNumberTally) throws IOException {

        PrintWriter writer = new PrintWriter(new FileWriter(CONFIG_FILE));

        Properties props = new Properties();
        props.setProperty("test", testMode);
        props.setProperty("lastOrder", orderNumberTally);
        props.store(writer, "lastOrder");

        writer.flush();
        writer.close();

    }

    @Override
    public int getOrderNumberTally() {
        return orderNumberTally;
    }
    
    @Override
    public void incOrderNumberTally(int inc) {
        orderNumberTally += inc;
    }

}
