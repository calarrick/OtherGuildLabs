/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flooringmaster;

import com.thesoftwareguild.flooringmaster.dao.Taxes;
import com.thesoftwareguild.flooringmaster.dto.Tax;
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
public class TaxesUnitTest {
    
    Taxes taxes;
    
    public TaxesUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        taxes = new Taxes();
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
    public void TaxesGetOhioTest() {
        Tax expected = new Tax();
        expected.setState("OH");
        expected.setTaxRate(6.25);
        
        Tax result = taxes.getTaxRate("OH");
        
        assertEquals(expected, result);
    }
    
    @Test
    public void TaxesGetPennsylvaniaTest() {
        Tax expected = new Tax();
        expected.setState("PA");
        expected.setTaxRate(6.75);
        
        Tax result = taxes.getTaxRate("PA");
        
        assertEquals(expected, result);
    }
    
    @Test
    public void TaxesGetMichiganTest() {
        Tax expected = new Tax();
        expected.setState("MI");
        expected.setTaxRate(5.75);
        
        Tax result = taxes.getTaxRate("MI");
        
        assertEquals(expected, result);
    }
    
    @Test
    public void TaxesGetIndianaTest() {
        Tax expected = new Tax();
        expected.setState("IN");
        expected.setTaxRate(6.00);
        
        Tax result = taxes.getTaxRate("IN");
        
        assertEquals(expected, result);
    }
}
