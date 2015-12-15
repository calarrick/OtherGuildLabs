/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flooringmaster;

import com.thesoftwareguild.flooringmaster.dao.Products;
import com.thesoftwareguild.flooringmaster.dto.Product;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apprentice
 */
public class ProductsUnitTest {
    
    Products products;
    
    public ProductsUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        products = new Products();
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
    public void ProductsGetLaminateTest() {
        Product expected = new Product();
        expected.setProductName("Laminate");
        expected.setCostPerSquareFoot(1.75);
        expected.setLaborCostPerSquareFoot(2.10);
        
        Product result = products.getProduct("Laminate");
        
        assertEquals(expected, result);
    }
    @Test
    public void ProductsGetWoodTest() {
        Product expected = new Product();
        expected.setProductName("Wood");
        expected.setCostPerSquareFoot(5.15);
        expected.setLaborCostPerSquareFoot(4.75);
        
        Product result = products.getProduct("Wood");
        
        assertEquals(expected, result);
    }
    @Test
    public void ProductsGetCarpetTest() {
        Product expected = new Product();
        expected.setProductName("Carpet");
        expected.setCostPerSquareFoot(2.25);
        expected.setLaborCostPerSquareFoot(2.10);
        Product result = products.getProduct("Carpet");
        
        assertEquals(expected, result);
    }
    @Test
    public void ProductsGetTileTest() {
        Product expected = new Product();
        expected.setProductName("Tile");
        expected.setCostPerSquareFoot(3.50);
        expected.setLaborCostPerSquareFoot(4.15);
        
        Product result = products.getProduct("Tile");
        
        assertEquals(expected, result);
    }
}
