/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flooringmaster;

import com.thesoftwareguild.flooringmaster.app.FlooringMasterController;
import com.thesoftwareguild.flooringmaster.app.OrderFactory;
import com.thesoftwareguild.flooringmaster.dao.OrdersDAO;
import com.thesoftwareguild.flooringmaster.dao.OrdersFileDAO;
import com.thesoftwareguild.flooringmaster.dto.Order;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class OrdersUnitTest {
    
    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        
        OrdersDAO orders = ctx.getBean("dao",OrdersDAO.class);
        
        
     
    OrderFactory orderFactory = new OrderFactory();
    
    public OrdersUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void OrdersReadWriteTest() {
        String name1 = "Bob Kinney";
        String date1 = "10082015";
        Integer orderNumber1 = 1;
        String state1 = "MI";
        String product1 = "Laminate";
        Double area1 = 100.0;
        
        Order order1 = orderFactory.createOrder(date1, orderNumber1, name1, state1, product1, area1);
        
        String name2 = "Max Headwater";
        String date2 = "10092015";
        Integer orderNumber2 = 2;
        String state2 = "OH";
        String product2 = "Wood";
        Double area2 = 59.4;
        
        Order order2 = orderFactory.createOrder(date2, orderNumber2, name2, state2, product2, area2);
        
        String name3 = "Alex Jones";
        String date3 = "10092015";
        Integer orderNumber3 = 3;
        String state3 = "PA";
        String product3 = "Carpet";
        Double area3 = 53.5;
        
        Order order3 = orderFactory.createOrder(date3, orderNumber3, name3, state3, product3, area3);
        
        String name4 = "Rob Schneider";
        String date4 = "10082015";
        Integer orderNumber4 = 4;
        String state4 = "IN";
        String product4 = "Tile";
        Double area4 = 39.4;
        
        Order order4 = orderFactory.createOrder(date4, orderNumber4, name4, state4, product4, area4);
        
        orders.addOrder(order1);
        orders.addOrder(order2);
        orders.addOrder(order3);
        orders.addOrder(order4);
        
        orders.setTest(true);
        
        OrdersDAO orders2 = ctx.getBean("dao",OrdersDAO.class);
        
        orders2.setTest(true);
        
        try {
            orders.saveSessionOrders();
            orders2.readOrders("10082015");
            orders2.readOrders("10092015");
        } catch (Exception e) {
            
        }
        
        assertEquals(orders, orders2);
    }
    
    @Test
    public void OrdersListAllOrdersTest() {
        String name1 = "Bob Kinney";
        String date1 = "10082015";
        Integer orderNumber1 = 1;
        String state1 = "MI";
        String product1 = "Laminate";
        Double area1 = 100.0;
        
        Order order1 = orderFactory.createOrder(date1, orderNumber1, name1, state1, product1, area1);
        
        String name2 = "Max Headwater";
        String date2 = "10092015";
        Integer orderNumber2 = 2;
        String state2 = "OH";
        String product2 = "Wood";
        Double area2 = 59.4;
        
        Order order2 = orderFactory.createOrder(date2, orderNumber2, name2, state2, product2, area2);
        
        String name3 = "Alex Jones";
        String date3 = "10092015";
        Integer orderNumber3 = 3;
        String state3 = "PA";
        String product3 = "Carpet";
        Double area3 = 53.5;
        
        Order order3 = orderFactory.createOrder(date3, orderNumber3, name3, state3, product3, area3);
        
        String name4 = "Rob Schneider";
        String date4 = "10082015";
        Integer orderNumber4 = 4;
        String state4 = "IN";
        String product4 = "Tile";
        Double area4 = 39.4;
        
        Order order4 = orderFactory.createOrder(date4, orderNumber4, name4, state4, product4, area4);
        
        orders.addOrder(order1);
        orders.addOrder(order2);
        orders.addOrder(order3);
        orders.addOrder(order4);
        
        List<Order> expected = new ArrayList();
        
        String name5 = "Bob Kinney";
        String date5 = "10082015";
        Integer orderNumber5 = 1;
        String state5 = "MI";
        String product5 = "Laminate";
        Double area5 = 100.0;
        
        Order order5 = orderFactory.createOrder(date5, orderNumber5, name5, state5, product5, area5);
        
        String name6 = "Max Headwater";
        String date6 = "10092015";
        Integer orderNumber6 = 2;
        String state6 = "OH";
        String product6 = "Wood";
        Double area6 = 59.4;
        
        Order order6 = orderFactory.createOrder(date6, orderNumber6, name6, state6, product6, area6);
        
        String name7 = "Alex Jones";
        String date7 = "10092015";
        Integer orderNumber7 = 3;
        String state7 = "PA";
        String product7 = "Carpet";
        Double area7 = 53.5;
        
        Order order7 = orderFactory.createOrder(date7, orderNumber7, name7, state7, product7, area7);
        
        String name8 = "Rob Schneider";
        String date8 = "10082015";
        Integer orderNumber8 = 4;
        String state8 = "IN";
        String product8 = "Tile";
        Double area8 = 39.4;
        
        Order order8 = orderFactory.createOrder(date8, orderNumber8, name8, state8, product8, area8);
        
        expected.add(order5);
        expected.add(order6);
        expected.add(order7);
        expected.add(order8);
        
        List<Order> result = orders.listAllOrders();
        
        assertEquals(expected, result);
    }
    
    @Test
    public void OrdersGetOrderTest() {
        String name1 = "Bob Kinney";
        String date1 = "10082015";
        Integer orderNumber1 = 1;
        String state1 = "MI";
        String product1 = "Laminate";
        Double area1 = 100.0;
        
        Order order1 = orderFactory.createOrder(date1, orderNumber1, name1, state1, product1, area1);
        
        String name2 = "Max Headwater";
        String date2 = "10092015";
        Integer orderNumber2 = 2;
        String state2 = "OH";
        String product2 = "Wood";
        Double area2 = 59.4;
        
        Order order2 = orderFactory.createOrder(date2, orderNumber2, name2, state2, product2, area2);
        
        String name3 = "Alex Jones";
        String date3 = "10092015";
        Integer orderNumber3 = 3;
        String state3 = "PA";
        String product3 = "Carpet";
        Double area3 = 53.5;
        
        Order order3 = orderFactory.createOrder(date3, orderNumber3, name3, state3, product3, area3);
        
        String name4 = "Rob Schneider";
        String date4 = "10082015";
        Integer orderNumber4 = 4;
        String state4 = "IN";
        String product4 = "Tile";
        Double area4 = 39.4;
        
        Order order4 = orderFactory.createOrder(date4, orderNumber4, name4, state4, product4, area4);
        
        orders.addOrder(order1);
        orders.addOrder(order2);
        orders.addOrder(order3);
        orders.addOrder(order4);
        
        Order expected = orderFactory.createOrder(date1, orderNumber1, name1, state1, product1, area1);
        Order result = orders.getOrder(1);
        
        assertEquals(expected, result);
        
        expected = orderFactory.createOrder(date2, orderNumber2, name2, state2, product2, area2);
        result = orders.getOrder(2);
        
        assertEquals(expected, result);
        
        expected = orderFactory.createOrder(date3, orderNumber3, name3, state3, product3, area3);
        result = orders.getOrder(3);
        
        assertEquals(expected, result);
        
        expected = orderFactory.createOrder(date4, orderNumber4, name4, state4, product4, area4);
        result = orders.getOrder(4);
        
        assertEquals(expected, result);
    }
    
    @Test
    public void OrdersRemoveOrderTest() {
        String name1 = "Bob Kinney";
        String date1 = "10082015";
        Integer orderNumber1 = 1;
        String state1 = "MI";
        String product1 = "Laminate";
        Double area1 = 100.0;
        
        Order order1 = orderFactory.createOrder(date1, orderNumber1, name1, state1, product1, area1);
        
        String name2 = "Max Headwater";
        String date2 = "10092015";
        Integer orderNumber2 = 2;
        String state2 = "OH";
        String product2 = "Wood";
        Double area2 = 59.4;
        
        Order order2 = orderFactory.createOrder(date2, orderNumber2, name2, state2, product2, area2);
        
        String name3 = "Alex Jones";
        String date3 = "10092015";
        Integer orderNumber3 = 3;
        String state3 = "PA";
        String product3 = "Carpet";
        Double area3 = 53.5;
        
        Order order3 = orderFactory.createOrder(date3, orderNumber3, name3, state3, product3, area3);
        
        String name4 = "Rob Schneider";
        String date4 = "10082015";
        Integer orderNumber4 = 4;
        String state4 = "IN";
        String product4 = "Tile";
        Double area4 = 39.4;
        
        Order order4 = orderFactory.createOrder(date4, orderNumber4, name4, state4, product4, area4);
        
        orders.addOrder(order1);
        orders.addOrder(order2);
        orders.addOrder(order3);
        orders.addOrder(order4);
        
        String name5 = "Bob Kinney";
        String date5 = "10082015";
        Integer orderNumber5 = 1;
        String state5 = "MI";
        String product5 = "Laminate";
        Double area5 = 100.0;
        
        Order order5 = orderFactory.createOrder(date5, orderNumber5, name5, state5, product5, area5);
        
        String name6 = "Max Headwater";
        String date6 = "10092015";
        Integer orderNumber6 = 2;
        String state6 = "OH";
        String product6 = "Wood";
        Double area6 = 59.4;
        
        Order order6 = orderFactory.createOrder(date6, orderNumber6, name6, state6, product6, area6);
        
        String name7 = "Alex Jones";
        String date7 = "10092015";
        Integer orderNumber7 = 3;
        String state7 = "PA";
        String product7 = "Carpet";
        Double area7 = 53.5;
        
        Order order7 = orderFactory.createOrder(date7, orderNumber7, name7, state7, product7, area7);
        
        String name8 = "Rob Schneider";
        String date8 = "10082015";
        Integer orderNumber8 = 4;
        String state8 = "IN";
        String product8 = "Tile";
        Double area8 = 39.4;
        
        Order order8 = orderFactory.createOrder(date8, orderNumber8, name8, state8, product8, area8);
        
        OrdersDAO orders2 = ctx.getBean("dao2",OrdersDAO.class);
        
        orders2.addOrder(order1);
        orders2.addOrder(order2);
        orders2.addOrder(order3);
        orders2.addOrder(order4);
        
        assertEquals(orders, orders2);
        
        orders.removeOrder(3);
        
        assertFalse(orders.equals(orders2));
        
        orders2.removeOrder(3);
        
        assertEquals(orders, orders2);
    }
    
    @Test
    public void OrdersUpdateOrderTest() {
        String name1 = "Bob Kinney";
        String date1 = "10082015";
        Integer orderNumber1 = 1;
        String state1 = "MI";
        String product1 = "Laminate";
        Double area1 = 100.0;
        
        Order order1 = orderFactory.createOrder(date1, orderNumber1, name1, state1, product1, area1);
        
        String name2 = "Max Headwater";
        String date2 = "10092015";
        Integer orderNumber2 = 2;
        String state2 = "OH";
        String product2 = "Wood";
        Double area2 = 59.4;
        
        Order order2 = orderFactory.createOrder(date2, orderNumber2, name2, state2, product2, area2);
        
        String name3 = "Alex Jones";
        String date3 = "10092015";
        Integer orderNumber3 = 3;
        String state3 = "PA";
        String product3 = "Carpet";
        Double area3 = 53.5;
        
        Order order3 = orderFactory.createOrder(date3, orderNumber3, name3, state3, product3, area3);
        
        String name4 = "Rob Schneider";
        String date4 = "10082015";
        Integer orderNumber4 = 4;
        String state4 = "IN";
        String product4 = "Tile";
        Double area4 = 39.4;
        
        Order order4 = orderFactory.createOrder(date4, orderNumber4, name4, state4, product4, area4);
        
        orders.addOrder(order1);
        orders.addOrder(order2);
        orders.addOrder(order3);
        orders.addOrder(order4);
        
        String name5 = "Bob Kinney";
        String date5 = "10082015";
        Integer orderNumber5 = 1;
        String state5 = "MI";
        String product5 = "Laminate";
        Double area5 = 100.0;
        
        Order order5 = orderFactory.createOrder(date5, orderNumber5, name5, state5, product5, area5);
        
        String name6 = "Max Headwater";
        String date6 = "10092015";
        Integer orderNumber6 = 2;
        String state6 = "OH";
        String product6 = "Wood";
        Double area6 = 59.4;
        
        Order order6 = orderFactory.createOrder(date6, orderNumber6, name6, state6, product6, area6);
        
        String name7 = "Alex Jones";
        String date7 = "10092015";
        Integer orderNumber7 = 3;
        String state7 = "PA";
        String product7 = "Carpet";
        Double area7 = 53.5;
        
        Order order7 = orderFactory.createOrder(date7, orderNumber7, name7, state7, product7, area7);
        
        String name8 = "Rob Schneider";
        String date8 = "10082015";
        Integer orderNumber8 = 4;
        String state8 = "IN";
        String product8 = "Tile";
        Double area8 = 39.4;
        
        Order order8 = orderFactory.createOrder(date8, orderNumber8, name8, state8, product8, area8);
        
        OrdersDAO orders2 = ctx.getBean("dao2",OrdersDAO.class);
        
        orders2.addOrder(order1);
        orders2.addOrder(order2);
        orders2.addOrder(order3);
        orders2.addOrder(order4);
        
        String nameUpdate = "Max Headwater";
        String dateUpdate = "10082015";
        Integer orderNumberUpdate = 2;
        String stateUpdate = "OH";
        String productUpdate = "Tile";
        Double areaUpdate = 132.9;
        
        Order newOrder = orderFactory.createOrder(dateUpdate, orderNumberUpdate, nameUpdate, stateUpdate, productUpdate, areaUpdate);
        
        
        assertEquals(orders, orders2);
        
        orders.updateOrder(newOrder);
        
        assertFalse(orders.equals(orders2));
        
        orders2.updateOrder(newOrder);
        
        assertEquals(orders, orders2);
    }
}
