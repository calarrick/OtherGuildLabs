/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flooringmaster.app;

import com.thesoftwareguild.flooringmaster.IO.ConsoleIO;
//import com.thesoftwareguild.flooringmaster.dao.Taxes;
//import com.thesoftwareguild.flooringmaster.dao.Products;
import com.thesoftwareguild.flooringmaster.dao.OrdersDAO;
import com.thesoftwareguild.flooringmaster.dao.Products;
import com.thesoftwareguild.flooringmaster.dao.Taxes;
import com.thesoftwareguild.flooringmaster.dto.Order;
import com.thesoftwareguild.flooringmaster.dto.Product;
import com.thesoftwareguild.flooringmaster.dto.Tax;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;


/*
 *
 * @author calarrick and kvwulp
 */
public class FlooringMasterController {

    boolean testMode;
    //int orderNumberTally;
    Scanner sc = new Scanner(System.in);
    ConsoleIO console = new ConsoleIO();
    Taxes taxes = new Taxes();
    Products products = new Products();
    OrdersDAO orders;
    //= new OrdersFileDAO();
    OrderFactory orderFactory = new OrderFactory();
    DateTimeFormatter orderDateFormat = DateTimeFormatter.ofPattern("MMddyyyy");
    DateTimeFormatter orderDateFormatInput = DateTimeFormatter.
            ofPattern("MM/dd/yyyy");
    //TimerAspect timerAspect;

    
    public FlooringMasterController(OrdersDAO dao){//, TimerAspect timerAspect){
        this.orders = dao;
        //this.timerAspect = timerAspect;
        
        
    }
    
    
    public void app() {

        testMode = false;
        testMode = orders.checkConfiguration();
        mainMenu(); //main program flow

        
        cleanConfig();

    }

    private void mainMenu() {

        boolean keepAlive = true;
        while (keepAlive) {

            clearConsole();

            console.
                    displayPrompt("=======================================================================================\n"
                            + "|                                     Flooring Master                                 |");

            int select = console.
                    readInteger(
                            (testMode ? "|                                       Test Mode!                                    |\n"
                                    : "|                                                                                     |\n")
                            + "|                                                                                     |\n"
                            + "|                                                                                     |\n"
                            + "|    1. Display Orders                                                                |\n"
                            + "|    2. Add an Order                                                                  |\n"
                            + "|    3. Edit an Order                                                                 |\n"
                            + "|    4. Remove an Order                                                               |\n"
                            + "|    5. Save Current Work                                                             |\n"
                            + "|    6. Quit.                                                                         |\n"
                            + "|                                                                                     |\n"
                            + "|                                                                                     |\n"
                            + "=======================================================================================\n"
                            + "Enter your choice:", 1, 6);

            switch (select) {

                case 1:
                    displayOrders();
                    break;
                case 2:
                    addOrder();
                    break;
                case 3:
                    editOrder();
                    break;
                case 4:
                    removeOrder();
                    break;
                case 5:
                    saveWork();
                    console.readLine("\nPress enter to continue.");
                    break;
                case 6:
                    keepAlive = exitProgram();
                    break;

            }

        }

    }

    private void displayOrders() {
        clearConsole();

        LocalDate date = LocalDate.now();

        String dateSelect = console.
                readLine("Please enter a date to get orders for that date or press enter for today (MM/DD/YYYY):");

        if (!dateSelect.isEmpty()) {
            do {
                try {
                    if (date == null) {
                        dateSelect = console.
                                readLine("That date was not valid. Please try again (MM/DD/YYYY):");
                    }
                    date = LocalDate.parse(dateSelect, orderDateFormatInput);
                } catch (Exception e) {
                    date = null;
                }
            } while (date == null);
        } else {
            date = LocalDate.now();
        }

        orders.readOrders(date.format(orderDateFormat));
        List<Order> orderList = orders.listAllOrders();

        if (!orderList.isEmpty()) {
            List<Order> ordersOnDate = new ArrayList();
            for (Order order : orderList) {
                if (order.getOrderDate().equals(date.format(orderDateFormat))) {
                    ordersOnDate.add(order);
                }
            }

            clearConsole();

            int i = 0;
            int j = 0;

            console.
                    displayPrompt("Displaying orders for " + date.getMonth() + " " + date.
                            getDayOfMonth() + ", " + date.getYear() + "\n");

            for (Order order : ordersOnDate) {

                Double total = order.getTotal();
                String formattedTotal = String.format("$%1$.2f", total);

                console.displayPrompt(String.format("Order Number: %1$-25d"
                        + "\tOrder Date: %2$s\n"
                        + "Customer Name: %3$-25s"
                        + "Customer State: %4$s\n"
                        + "Tax Rate: %5$-25s"
                        + "\tProduct Type: %6$s\n"
                        + "Area: %7$-25s\n\n"
                        + "Material Cost Per Square Foot: $%8$.2f"
                        + "\tLabor Cost Per SquareFoot: $%9$.2f\n"
                        + "Total Material Cost: $%10$.2f"
                        + "\t\tTotal Labor Cost: $%11$.2f\n\n"
                        + "Total Tax: $%12$.2f"
                        + "\t\tTotal Cost: $%13$.2f\n"
                        + "==================================================\n",
                        order.getOrderNumber(),
                        LocalDate.parse(order.getOrderDate(), orderDateFormat).
                        format(orderDateFormatInput),
                        order.getCustomerName().replaceAll("\\\\,", ","),
                        order.getCustomerState(),
                        order.getTaxRate() + "%",
                        order.getProductType(),
                        order.getArea(),
                        order.getCostPerSquareFoot(),
                        order.getLaborCostPerSquareFoot(),
                        order.getMaterialCost(),
                        order.getLaborCost(),
                        order.getTax(),
                        order.getTotal()));
                j++;
                i++;
                if (j == 3 && i != ordersOnDate.size()) {
                    console.readLine("Press enter to continue.");
                    clearConsole();
                    console.displayPrompt("Displaying orders for " + date.
                            getMonth()
                            + " " + date.getDayOfMonth() + ", " + date.getYear() + "\n");
                    j = 0;
                }

            }
        } else {
            console.
                    displayPrompt("There have been no orders placed for this day.");

        }

        console.readLine("\nPress enter to continue.");

    }

    private void addOrder() {

        clearConsole();
        List<Product> productList = products.listAllProducts();

        //retrieves products from the Products DAO and reads their names into menu list
        String customerName = console.
                readLine("Please enter the customer's name:");

        //while (customerName.contains(",")) {
        //customerName = console.
        //       readLine("The name must be entered without a comma.\nPlease re-enter the customer's name: ");
        //}
        //String[] splitName;
        customerName = customerName.replaceAll(",", "\\\\,");
//splitName = customerName.split(",",2);

        //customerName = splitName[0] + "\\," + splitName[1];
        //}
        
        ArrayList<Tax> taxingStates = new ArrayList<>();
        taxingStates = taxes.listAllStates();
        console.displayPrompt("State choices are:");
        
        String[] stateNames = new String[taxingStates.size()];
        for (Tax state: taxingStates) {
            int stateNum = taxingStates.indexOf(state);
            String stateAbr = taxingStates.get(stateNum).getState();
            console.displayPrompt((stateNum+1) + ": " + stateAbr);
            
        }
        
//        console.displayPrompt("\nState choices are:\n"
//               + "# OH\n# PA\n# MI\n# IN");

        String stateName = console.
                readLine("\nPlease enter the two letter postal abbreviaton for the customer's state: ").
                toUpperCase();

        boolean stateMatch = false;
        for (Tax state: taxingStates) {
            int stateNum = taxingStates.indexOf(state);
            String stateAbr = taxingStates.get(stateNum).getState();
            if(stateName.equals(stateAbr)) {
            {
                stateMatch = true;
            }
            }
            }
        
//        while (!stateName.equals("OH") && !stateName.equals("PA") && !stateName.
//                equals("MI") && !stateName.equals("IN")) {
        while (!stateMatch) {
            
            
            console.
                    displayPrompt("\nAt this time we are unable to business in " + stateName + ". Please enter an appropriate state abbreviation.");
            
            stateName = console.
                    readLine("\nPlease enter the name of the customer's state: ").
                    toUpperCase();
            
            for (Tax state: taxingStates) {
            int stateNum = taxingStates.indexOf(state);
            String stateAbr = taxingStates.get(stateNum).getState();
            if(stateName.equals(stateAbr)) {
            {
                stateMatch = true;
            }
            }
            }
            
            
        }
        
        /* console.displayPrompt("\nMaterials choices are:\n"
         + "1. Carpet\n"
         + "2. Laminate\n"
         + "3. Tile\n"
         + "4. Wood");
         Integer productChoice = console.
         readInteger("\nPlease enter the choice of product: ", 1, 4);

         String productName = "";

         switch (productChoice) {
         case 1:
         productName = "Carpet";
         break;
         case 2:
         productName = "Laminate";
         break;
         case 3:
         productName = "Tile";
         break;
         case 4:
         productName = "Wood";
         break;
         }
        
         */
        
        
        Integer productChoice = 0;
        Boolean badNum = false;

        ArrayList<Product> productsList = products.listAllProducts();
        int productCount = productsList.size();

        console.displayPrompt("\nMaterials choices are: \n");
        for (int menuNum = 0; menuNum < productsList.size(); menuNum++) {
            console.
                    displayPrompt(menuNum + 1 + ". " + productsList.get(menuNum).
                            getProductName() + " ");

        }

        do {
            String productSelect = console.
                    readLine("\nPlease enter "
                            + "\"1\""
                            + " through \""
                            + productsList.size() + "\" to select a flooring material."
                    );

            if (!productSelect.isEmpty()) {
                try {
                    productChoice = Integer.parseInt(productSelect);
                    badNum = false;
                } catch (NumberFormatException e) {
                    badNum = true;
                }

            }
        } while (productChoice > productsList.size() || productChoice < 1 || badNum);

        String productName = "";

        productName = productsList.get(productChoice - 1).getProductName();

        Double area = 0.0;
        do {
            area = console.
                    readDouble("\nWhat is the area (in square feet) of flooring?");
        } while (area <= 0);

        LocalDate date;

        String forToday = console.
                readLine("\nIs this order for today? Enter \"y\" or \"n\")");
        if (forToday.toLowerCase().equals("y")) {
            date = LocalDate.now();
        } else {

            String dateSelect = console.
                    readLine("\nPlease enter the date for this order (MM/DD/YYYY format, please):");

            try {
                date = LocalDate.parse(dateSelect, orderDateFormatInput);
            } catch (Exception e) {
                date = null;
            }

            dateSelect = dateSelect.replaceAll("-", "/");
            try {
                date = LocalDate.parse(dateSelect, orderDateFormatInput);
            } catch (Exception e) {
                date = null;
            }

            if (dateSelect.matches("[0-9]{8}")) {
                try {

                    char[] dateSelectChars = new char[8];

                    dateSelectChars = dateSelect.toCharArray();
                    StringBuilder sb = new StringBuilder();
                    sb.append(dateSelectChars[0]).append(dateSelectChars[1]).
                            append("/").
                            append(dateSelectChars[2]).
                            append(dateSelectChars[3]).append("/").
                            append(dateSelectChars[4]).
                            append(dateSelectChars[5]).
                            append(dateSelectChars[6]).
                            append(dateSelectChars[7]);

                    dateSelect = sb.toString();

                    date = LocalDate.parse(dateSelect, orderDateFormatInput);
                } catch (Exception e) {
                    date = null;
                }

            }

            do {
                try {
                    if (date == null) {

                        dateSelect = console.
                                readLine("That date was not valid. Please try again (MM/DD/YYYY):");
                    }
                    date = LocalDate.parse(dateSelect, orderDateFormatInput);
                } catch (Exception e) {
                    date = null;
                }
            } while (date == null);

        }

        String orderDate = date.format(orderDateFormat);

        clearConsole();
        orders.incOrderNumberTally(1);

        Order order = orderFactory.
                createOrder(orderDate, orders.getOrderNumberTally(), customerName, stateName, productName, area);

        console.displayPrompt(String.
                format("This is the current order to be added based on the information you entered:\n"
                        + "Order Date: " + date.format(orderDateFormatInput) + "\n"
                        + "Customer Name: " + customerName.
                        replaceAll("\\\\,", ",") + "\n"
                        + "Customer State: " + stateName + "\n"
                        + "Tax Rate: " + order.getTaxRate() + "\n"
                        + "Product Type: " + order.getProductType() + "\n"
                        + "Area: " + area + "\n"
                        + "Material Cost Per Square Foot: $%1$.2f\n"
                        + "Labor Cost Per SquareFoot: $%2$.2f\n"
                        + "Total Material Cost: $%3$.2f\n"
                        + "Total Labor Cost: $%4$.2f\n"
                        + "Total Tax: $%5$.2f\n"
                        + "Total Cost: $%6$.2f",
                        order.getCostPerSquareFoot(),
                        order.getLaborCostPerSquareFoot(),
                        order.getMaterialCost(),
                        order.getLaborCost(),
                        order.getTax(),
                        order.getTotal()));

        String confirm = console.
                readLine("\nIs this information correct? (Enter \"y\" or \"n\")");
        while (!confirm.toLowerCase().equals("y") && !confirm.toLowerCase().
                equals("n")) {
            console.displayPrompt("Sorry, that input was not correct.");
            confirm = console.
                    readLine("\nIs this information correct? (Enter \"y\" or \"n\")");
        }

        
        if (confirm.toLowerCase().equals("y")) {

            
            orders.readOrders(order.getOrderDate());
            orders.addOrder(order);

            
            console.readLine("\nOrder updated successfully.\n"
                    + "Press enter to continue.");
        }

    }
    
    private void editOrder() {

        clearConsole();

        LocalDate date = LocalDate.now();

        String dateSelect = console.
                readLine("Please enter a date of the order you wish to edit, or press enter for today (MM/DD/YYYY):");

        if (!dateSelect.isEmpty()) {

            dateSelect = dateSelect.replaceAll("-", "/");
            try {
                date = LocalDate.parse(dateSelect, orderDateFormatInput);
            } catch (Exception e) {
                date = null;
            }

            if (dateSelect.matches("[0-9]{8}")) {
                try {

                    char[] dateSelectChars = new char[8];

                    dateSelectChars = dateSelect.toCharArray();
                    StringBuilder sb = new StringBuilder();
                    sb.append(dateSelectChars[0]).append(dateSelectChars[1]).
                            append("/").
                            append(dateSelectChars[2]).
                            append(dateSelectChars[3]).append("/").
                            append(dateSelectChars[4]).
                            append(dateSelectChars[5]).
                            append(dateSelectChars[6]).
                            append(dateSelectChars[7]);

                    dateSelect = sb.toString();
                    try {
                        date = LocalDate.parse(dateSelect, orderDateFormatInput);
                    } catch (Exception e) {
                        date = null;
                    }

                } catch (IndexOutOfBoundsException e) {

                    date = null;
                }
            }

            do {
                try {
                    if (date == null) {

                        dateSelect = console.
                                readLine("That date was not valid. Please try again (MM/DD/YYYY):");
                    }
                    date = LocalDate.parse(dateSelect, orderDateFormatInput);
                } catch (Exception e) {
                    date = null;
                }
            } while (date == null);

        } else {
            date = LocalDate.now();
        }

        
            orders.readOrders(date.format(orderDateFormat));
        

        boolean check = false;
        for (Order order : orders.listAllOrders()) {
            if (order.getOrderDate().equals(date.format(orderDateFormat))) {
                check = true;
            }
        }
        if (!check) {
            console.readLine("\nThere were no orders on that day.\n"
                    + "Press enter to continue.");
            return;

        }

        Integer orderNumber = console.
                readInteger("\nPlease enter the order number that you want to edit:");

        while (orders.getOrder(orderNumber) == null) {

            String response = console.
                    readLine("\nThere was no order with that number on that day.\nTry again (\"y\" or \"n\")");
            if (response.equals("n")) {
                return;
            }

            orderNumber = console.
                    readInteger("\nPlease enter a new order number: ");

        }

        Order order = orders.getOrder(orderNumber);

        if (!order.getOrderDate().equals(date.format(orderDateFormat))) {
            String response = console.
                    readLine("\nThere was no order with that number on that day. \n"
                            + "But in this session you have already loaded an order with that number from another date. \n "
                            + "Enter \"y\" if you would like to see that order.");
            if (!response.toLowerCase().equals("y")) {

                return;
            }
        }

        String displayName = order.getCustomerName().replaceAll("\\\\,", ",");
        String newName = console.
                readLine("\nPlease enter the customer name (or press enter to leave as "
                        + displayName + "): ");

        newName = newName.replaceAll(",", "\\\\,");
        String newState = "";
        newState = console.
                readLine("\nPlease enter the customer's state (or press enter to leave as "
                        + order.getCustomerState() + "):").toUpperCase();
        
        if (!newState.isEmpty()){
            
            
        ArrayList<Tax> taxingStates = new ArrayList<>();
        taxingStates = taxes.listAllStates();
        console.displayPrompt("State choices are:");
        
        String[] stateNames = new String[taxingStates.size()];
        for (Tax state: taxingStates) {
            int stateNum = taxingStates.indexOf(state);
            String stateAbr = taxingStates.get(stateNum).getState();
            console.displayPrompt((stateNum+1) + ": " + stateAbr);
            
        }
        
//        console.displayPrompt("\nState choices are:\n"
//               + "# OH\n# PA\n# MI\n# IN");

        newState = console.
                readLine("\nPlease enter the two letter postal abbreviaton for the customer's state: ").
                toUpperCase();

        boolean stateMatch = false;
        
        for (Tax state: taxingStates) {
            int stateNum = taxingStates.indexOf(state);
            String stateAbr = taxingStates.get(stateNum).getState();
            if(newState.equals(stateAbr)) {
            {
                stateMatch = true;
            }
            }
            }
        
//        while (!stateName.equals("OH") && !stateName.equals("PA") && !stateName.
//                equals("MI") && !stateName.equals("IN")) {
        while (!stateMatch) {
            
            
            
            console.
                    displayPrompt("\nAt this time we are unable to business in " + newState + ". Please enter an appropriate state abbreviation.");
            
            newState = console.
                    readLine("\nPlease enter the name of the customer's state: ").
                    toUpperCase();
            
            for (Tax state: taxingStates) {
            int stateNum = taxingStates.indexOf(state);
            String stateAbr = taxingStates.get(stateNum).getState();
            if(newState.equals(stateAbr)) {
            {
                stateMatch = true;
            }
            }
            }
            
            
        }
        }
        
        
        

       /* while (!newState.equals("OH") && !newState.equals("PA")
                && !newState.equals("MI") && !newState.equals("IN") && !newState.
                isEmpty()) {
            console.
                    displayPrompt("\nThat was not a valid choice for state.");
            newState = console.
                    readLine("\nPlease enter the customer's state (or press enter to leave as "
                            + order.getCustomerState() + "):").toUpperCase();
        }*/
        
        
        
        
        
        Integer productChoice = 0;
        Boolean badNum = false;
        /*String productName = order.getProductType();
         if (productName == "Carpet") {
         productChoice = 1;
         } else if (productName == "Laminate") {
         productChoice = 2;
         } else if (productName == "Tile") {
         productChoice = 3;
         } else {
         productChoice = 4;
         }*/

        ArrayList<Product> productsList = products.listAllProducts();
        int productCount = productsList.size();

        console.displayPrompt("\nMaterials choices are: \n");
        for (int menuNum = 0; menuNum < productsList.size(); menuNum++) {
            console.
                    displayPrompt(menuNum + 1 + ". " + productsList.get(menuNum).
                            getProductName() + " ");

        }

        /*console.displayPrompt("\nMaterials choices are:\n"
         + "1. Carpet\n"
         + "2. Laminate\n"
         + "3. Tile\n"
         + "4. Wood");*/
        String newMaterial = "";
            String productSelect = console.
                    readLine("\nPlease enter "
                            + "1\""
                            + " through \""
                            + productsList.size() + "\""
                            + " to select product for the order (or press enter to leave as "
                            + order.getProductType() + "). ");

            if (!productSelect.isEmpty()) {
                
                do {
                
                try {
                    productChoice = Integer.parseInt(productSelect);
                    badNum = false;
                } catch (NumberFormatException e) {
                    badNum = true;
                }

            }
         while (productChoice > productsList.size() || productChoice < 1 || badNum);

        

        newMaterial = productsList.get(productChoice - 1).getProductName();
}
        /*switch (productChoice) {
         case 1:
         newMaterial = "Carpet";
         break;
         case 2:
         newMaterial = "Laminate";
         break;
         case 3:
         newMaterial = "Tile";
         break;
         case 4:
         newMaterial = "Wood";
         break;
         }*/
        String newArea = console.
                readLine("\nPlease enter the new area to be floored (or press enter to leave as "
                        + order.getArea() + "):");

        String newDate = console.
                readLine("\nEnter a date (MM/DD/YYYY) if you wish to alter the date on the order (press enter to leave as "
                        + LocalDate.parse(order.getOrderDate(), orderDateFormat).
                        format(orderDateFormatInput) + ")");

        Order newOrder = orderFactory.createOrder(
                (newDate.isEmpty() ? order.getOrderDate() : LocalDate.
                        parse(newDate, orderDateFormatInput).
                        format(orderDateFormat)),
                order.getOrderNumber(),
                (newName.isEmpty() ? order.getCustomerName() : newName),
                (newState.isEmpty() ? order.getCustomerState() : newState),
                (newMaterial.isEmpty() ? order.getProductType() : newMaterial),
                (newArea.isEmpty() ? order.getArea() : Double.
                        parseDouble(newArea)));

        clearConsole();

        String confirm = console.readLine(String.
                format("You are currently editing an order. You have entered: \n"
                        + "Order Date: " + LocalDate.parse(newOrder.
                                getOrderDate(), orderDateFormat).
                        format(orderDateFormatInput) + "\n"
                        + "Customer Name: " + newOrder.getCustomerName().
                        replaceAll("\\\\,", ",") + "\n"
                        + "Customer State: " + newOrder.getCustomerState() + "\n"
                        + "Tax Rate: " + newOrder.getTaxRate() + "\n"
                        + "Product Type: " + newOrder.getProductType() + "\n"
                        + "Area: " + newOrder.getArea() + "\n"
                        + "Material Cost Per Square Foot: $%1$.2f\n"
                        + "Labor Cost Per SquareFoot: $%2$.2f\n"
                        + "Total Material Cost: $%3$.2f\n"
                        + "Total Labor Cost: $%4$.2f\n"
                        + "Total Tax: $%5$.2f\n"
                        + "Total Cost: $%6$.2f\n\n"
                        + "Please confirm that this information is correct (\"y\" to confirm changes, \"n\" to abandon them)\n",
                        newOrder.getCostPerSquareFoot(),
                        newOrder.getLaborCostPerSquareFoot(),
                        newOrder.getMaterialCost(),
                        newOrder.getLaborCost(),
                        newOrder.getTax(),
                        newOrder.getTotal()));

        if (confirm.toLowerCase().equals("y")) {

            orders.updateOrder(newOrder);

            
                orders.readOrders(newOrder.getOrderDate());
            

            console.readLine("\nOrder updated successfully.\n"
                    + "Press enter to continue.");
        }
    }

    private void removeOrder() {
        clearConsole();

        LocalDate date = LocalDate.now();

        String dateSelect = console.
                readLine("Please enter a date of the order you wish to remove, or press enter for today (MM/DD/YYYY):");

        if (!dateSelect.isEmpty()) {

            dateSelect = dateSelect.replaceAll("-", "/");
            try {
                date = LocalDate.parse(dateSelect, orderDateFormatInput);
            } catch (Exception e) {
                date = null;
            }

            if (dateSelect.matches("[0-9]{8}")) {
                try {

                    char[] dateSelectChars = new char[8];

                    dateSelectChars = dateSelect.toCharArray();
                    StringBuilder sb = new StringBuilder();
                    sb.append(dateSelectChars[0]).append(dateSelectChars[1]).
                            append("/").
                            append(dateSelectChars[2]).
                            append(dateSelectChars[3]).append("/").
                            append(dateSelectChars[4]).
                            append(dateSelectChars[5]).
                            append(dateSelectChars[6]).
                            append(dateSelectChars[7]);

                    dateSelect = sb.toString();
                    try {
                        date = LocalDate.parse(dateSelect, orderDateFormatInput);
                    } catch (Exception e) {
                        date = null;
                    }

                } catch (IndexOutOfBoundsException e) {

                    date = null;
                }
            }

            do {
                try {
                    if (date == null) {

                        dateSelect = console.
                                readLine("That date was not valid. Please try again (MM/DD/YYYY):");
                    }
                    date = LocalDate.parse(dateSelect, orderDateFormatInput);
                } catch (Exception e) {
                    date = null;
                }
            } while (date == null);

        } else {
            date = LocalDate.now();
        }

        //String fileName = "Order_" + date + ".txt";
        
            orders.readOrders(date.format(orderDateFormat));//needs to check any orders in storage

            boolean check = false;
            for (Order order : orders.listAllOrders()) {//and also check any current session orders from date
                if (order.getOrderDate().equals(date.format(orderDateFormat))) {
                    check = true;
                }
            }
            if (!check) {
                console.displayPrompt("There were no orders on that day.");
                return;
            }
        

        Integer orderNumber = console.
                readInteger("\nPlease enter the order number that you want to edit:\n");

        if (orders.getOrder(orderNumber) != null) {

            Order order = orders.getOrder(orderNumber);

            String confirm = console.readLine(String.
                    format("This is the current order based on the information you entered: \n"
                            + "Order Date: " + LocalDate.parse(order.
                                    getOrderDate(), orderDateFormat).
                            format(orderDateFormatInput) + "\n"
                            + "Customer Name: " + order.getCustomerName().
                            replaceAll("\\\\,", ",") + "\n"
                            + "Customer State: " + order.getCustomerState() + "\n"
                            + "Tax Rate: " + order.getTaxRate() + "\n"
                            + "Product Type: " + order.getProductType() + "\n"
                            + "Area: " + order.getArea() + "\n"
                            + "Material Cost Per Square Foot: $%1$.2f\n"
                            + "Labor Cost Per SquareFoot: $%2$.2f\n"
                            + "Total Material Cost: $%3$.2f\n"
                            + "Total Labor Cost: $%4$.2f\n"
                            + "Total Tax: $%5$.2f\n"
                            + "Total Cost: $%6$.2f\n\n"
                            + "Do you want to remove this order?\n",
                            order.getCostPerSquareFoot(),
                            order.getLaborCostPerSquareFoot(),
                            order.getMaterialCost(),
                            order.getLaborCost(),
                            order.getTax(),
                            order.getTotal()));

            if (confirm.toLowerCase().equals("y")) {
                orders.removeOrder(orderNumber);
            }
        } else {
            console.readLine("Order not found with that number\n"
                    + "Press enter to continue.");
        }
    }

    private void saveWork() {

        if (!testMode) {

            String choice = console.
                    readLine("\nDo you want to save your work? (y/n)").
                    toLowerCase();

            while (!choice.equals("y") && !choice.equals("n")) {
                choice = console.readLine("\nThat was not a valid choice.\n"
                        + "Do you want to save your work? (y/n)");
            }

            if (choice.equals("y")) {

                orders.saveSessionOrders();
                
                
        } else {
            console.displayPrompt("There's no saving in test mode!");
        }
    }
    }

    private void cleanConfig() {

        orders.cleanConfig();
        
        
        
    }

    public final void clearConsole() {
        for (int i = 0; i < 20; i++) {
            console.displayPrompt("");
        }
    }

    private boolean exitProgram() {

        String exitSave = console.
                readLine("Preparing to exit. Are you sure you want to exit? (\"y\" or \"n\")").
                toLowerCase();
        if (exitSave.equals("y")) {

            saveWork();
            cleanConfig();
            console.displayPrompt("Thank you for using Flooring Master!");
        }
        return exitSave.equals("n");
    }

}
