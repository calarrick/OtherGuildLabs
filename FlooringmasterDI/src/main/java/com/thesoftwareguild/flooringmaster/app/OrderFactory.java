/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flooringmaster.app;

import com.thesoftwareguild.flooringmaster.dao.Products;
import com.thesoftwareguild.flooringmaster.dao.Taxes;
import com.thesoftwareguild.flooringmaster.dto.Order;

/**
 *
 * @author apprentice
 */
public class OrderFactory {
    
    public Order createOrder(String date, Integer orderNumber, String name, String state, String product, Double area) {
        Taxes taxes = new Taxes();
        Products products = new Products();
        Order order = new Order();
        
        order.setOrderDate(date);
        order.setOrderNumber(orderNumber);
        order.setCustomerName(name);
        order.setTaxRate(taxes.getTaxRate(state).getTaxRate());
        order.setCustomerState(state);
        order.setProductType(product);
        order.setArea(area);
        order.setCostPerSquareFoot(products.getProduct(product).getCostPerSquareFoot());
        order.setLaborCostPerSquareFoot(products.getProduct(product).getLaborCostPerSquareFoot());
        order.setMaterialCost(order.getCostPerSquareFoot() * order.getArea());
        order.setLaborCost(order.getLaborCostPerSquareFoot() * order.getArea());
        order.setTax((order.getMaterialCost() + order.getLaborCost()) * (order.getTaxRate()/100));
        order.setTotal(order.getMaterialCost() + order.getLaborCost() + order.getTax());
        
        return order;
    }
    
}
