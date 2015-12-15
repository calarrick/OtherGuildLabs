/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flooringmaster.dao;

import com.thesoftwareguild.flooringmaster.dto.Order;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface OrdersDAO {

    public boolean isTest();
    
    public int getOrderNumberTally();
    
    public void incOrderNumberTally(int inc);

    public void setTest(boolean test);

    public List<Order> listAllOrders();

    public void addOrder(Order order);

    public Order getOrder(Integer orderNumber);

    public Order removeOrder(Integer orderNumber);

    public Order updateOrder(Order order);

    public void readOrders(String date);

    public void saveSessionOrders();

    public boolean checkConfiguration();
    
    public void cleanConfig();

   
}
