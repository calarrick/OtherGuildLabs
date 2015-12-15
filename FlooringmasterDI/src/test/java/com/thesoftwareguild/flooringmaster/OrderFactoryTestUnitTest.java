/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flooringmaster;

import com.thesoftwareguild.flooringmaster.app.OrderFactory;
import com.thesoftwareguild.flooringmaster.dto.Order;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author apprentice
 */
public class OrderFactoryTestUnitTest {
    
    OrderFactory orderFactory;
    
    public OrderFactoryTestUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        orderFactory = new OrderFactory();
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
    public void OrderFactoryOneTest() {
        String name = "Bob Kinney";
        String date = "10082015";
        Integer orderNumber = 1;
        String state = "PA";
        String product = "Wood";
        Double area = 100.0;
        
        Order expected  = new Order();
        expected.setOrderDate(date);
        expected.setOrderNumber(orderNumber);
        expected.setCustomerName(name);
        expected.setCustomerState(state);
        expected.setTaxRate(6.75);
        expected.setArea(area);
        expected.setProductType(product);
        expected.setCostPerSquareFoot(5.15);
        expected.setLaborCostPerSquareFoot(4.75);
        expected.setMaterialCost(515.0);
        expected.setLaborCost(475.0);
        expected.setTax(66.825);
        expected.setTotal(1056.825);
        Order result = orderFactory.createOrder(date, orderNumber, name, state, product, area);
        
        assertEquals(expected.getOrderDate(), result.getOrderDate());
        assertEquals(expected.getOrderNumber(), result.getOrderNumber());
        assertEquals(expected.getCustomerName(), result.getCustomerName());
        assertEquals(expected.getCustomerState(), result.getCustomerState());
        assertEquals(expected.getTaxRate(), result.getTaxRate());
        assertEquals(expected.getArea(), result.getArea());
        assertEquals(expected.getProductType(), result.getProductType());
        assertEquals(expected.getCostPerSquareFoot(), result.getCostPerSquareFoot());
        assertEquals(expected.getLaborCostPerSquareFoot(), result.getLaborCostPerSquareFoot(), 0.001);
        assertEquals(expected.getMaterialCost(), result.getMaterialCost(), 0.001);
        assertEquals(expected.getLaborCost(), result.getLaborCost(), 0.001);
        assertEquals(expected.getTax(), result.getTax(), 0.001);
        assertEquals(expected.getTotal(), result.getTotal(), 0.001);
    }
    
    @Test
    public void OrderFactoryTwoTest() {
        String name = "Bob Kinney";
        String date = "10082015";
        Integer orderNumber = 1;
        String state = "IN";
        String product = "Tile";
        Double area = 100.0;
        
        Order expected  = new Order();
        expected.setOrderDate(date);
        expected.setOrderNumber(orderNumber);
        expected.setCustomerName(name);
        expected.setCustomerState(state);
        expected.setTaxRate(6.0);
        expected.setArea(area);
        expected.setProductType(product);
        expected.setCostPerSquareFoot(3.5);
        expected.setLaborCostPerSquareFoot(4.15);
        expected.setMaterialCost(350.0);
        expected.setLaborCost(415.0);
        expected.setTax(45.9);
        expected.setTotal(810.9);
        Order result = orderFactory.createOrder(date, orderNumber, name, state, product, area);
        
        assertEquals(expected.getOrderDate(), result.getOrderDate());
        assertEquals(expected.getOrderNumber(), result.getOrderNumber());
        assertEquals(expected.getCustomerName(), result.getCustomerName());
        assertEquals(expected.getCustomerState(), result.getCustomerState());
        assertEquals(expected.getTaxRate(), result.getTaxRate());
        assertEquals(expected.getArea(), result.getArea());
        assertEquals(expected.getProductType(), result.getProductType());
        assertEquals(expected.getCostPerSquareFoot(), result.getCostPerSquareFoot());
        assertEquals(expected.getLaborCostPerSquareFoot(), result.getLaborCostPerSquareFoot(), 0.001);
        assertEquals(expected.getMaterialCost(), result.getMaterialCost(), 0.001);
        assertEquals(expected.getLaborCost(), result.getLaborCost(), 0.001);
        assertEquals(expected.getTax(), result.getTax(), 0.001);
        assertEquals(expected.getTotal(), result.getTotal(), 0.001);
    }
    
    @Test
    public void OrderFactoryThreeTest() {
        String name = "Bob Kinney";
        String date = "10082015";
        Integer orderNumber = 1;
        String state = "MI";
        String product = "Laminate";
        Double area = 100.0;
        
        Order expected  = new Order();
        expected.setOrderDate(date);
        expected.setOrderNumber(orderNumber);
        expected.setCustomerName(name);
        expected.setCustomerState(state);
        expected.setTaxRate(5.75);
        expected.setArea(area);
        expected.setProductType(product);
        expected.setCostPerSquareFoot(1.75);
        expected.setLaborCostPerSquareFoot(2.10);
        expected.setMaterialCost(175.0);
        expected.setLaborCost(210.0);
        expected.setTax(22.1375);
        expected.setTotal(407.1375);
        Order result = orderFactory.createOrder(date, orderNumber, name, state, product, area);
        
        assertEquals(expected.getOrderDate(), result.getOrderDate());
        assertEquals(expected.getOrderNumber(), result.getOrderNumber());
        assertEquals(expected.getCustomerName(), result.getCustomerName());
        assertEquals(expected.getCustomerState(), result.getCustomerState());
        assertEquals(expected.getTaxRate(), result.getTaxRate());
        assertEquals(expected.getArea(), result.getArea());
        assertEquals(expected.getProductType(), result.getProductType());
        assertEquals(expected.getCostPerSquareFoot(), result.getCostPerSquareFoot());
        assertEquals(expected.getLaborCostPerSquareFoot(), result.getLaborCostPerSquareFoot(), 0.001);
        assertEquals(expected.getMaterialCost(), result.getMaterialCost(), 0.001);
        assertEquals(expected.getLaborCost(), result.getLaborCost(), 0.001);
        assertEquals(expected.getTax(), result.getTax(), 0.001);
        assertEquals(expected.getTotal(), result.getTotal(), 0.001);
    }
}
