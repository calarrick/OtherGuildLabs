///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.thesoftwareguild.addressbookweb.dao;
//
//
//import com.thesoftwareguild.addressbookweb.model.Address;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.Collections;
//import java.util.List;
//
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
///**
// *
// * @author apprentice
// */
//public class AddressBookUnitTest {
//
//    AddressBookDAO addressBook;
//
//    public AddressBookUnitTest() {
//    }
//
//    @BeforeClass
//    public static void setUpClass() {
//    }
//
//    @AfterClass
//    public static void tearDownClass() {
//    }
//
//    @Before
//    public void setUp() {
//        
//        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
//        addressBook = ctx.getBean("dao",AddressBookDAO.class);
//        
//        
//        Address address1 = new Address();
//        address1.setAddressId(1);
//        address1.setLastName("Smith");
//        address1.setFirstNames(new String[]{"Bob", "Jane"});
//        address1.setPhoneNumbers(new String[]{"999-999-9999"});
//        address1.setStreetNumber("622");
//        address1.setStreetName("N Monroe St");
//        address1.setCityName("Akron");
//        address1.setStateName("Ohio");
//        address1.setZipCode("44311");
//        Address address2 = new Address();
//        address2.setAddressId(2);
//        address2.setLastName("Jones");
//        address2.setFirstNames(new String[]{"Alex", "Mary"});
//        address2.setPhoneNumbers(new String[]{"999-999-9999"});
//        address2.setStreetNumber("434");
//        address2.setStreetName("S Main St");
//        address2.setCityName("Akron");
//        address2.setStateName("Ohio");
//        address2.setZipCode("44311");
//        Address address3 = new Address();
//        address3.setAddressId(3);
//        address3.setLastName("Duggar");
//        address3.setFirstNames(new String[]{"JimBob", "Jane"});
//        address3.setPhoneNumbers(new String[]{"999-999-9999"});
//        address3.setStreetNumber("995");
//        address3.setStreetName("E Exchange St");
//        address3.setCityName("Akron");
//        address3.setStateName("Ohio");
//        address3.setZipCode("44311");
//        addressBook.addAddress(address1);
//        addressBook.addAddress(address2);
//        addressBook.addAddress(address3);
//    }
//
//    @After
//    public void tearDown() {
//    }
//
//    // TODO add test methods here.
//    // The methods must be annotated with annotation @Test. For example:
//    //
//    // @Test
//    // public void hello() {}
////    @Test
////    public void AddressBookReadWriteTest() {
////        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
////        AddressBookDAO addressBook2 = ctx.getBean("dao",AddressBookDAO.class);
////
////        try {
////            addressBook.saveData();
////            addressBook2.loadData();
////        } catch (Exception e) {
////        }
////
////        Assert.assertEquals(addressBook.listAddressesSorted().size(), addressBook2.listAddressesSorted().size());
////        
////
////    }
//    
//    @Test
//    public void AddressBookRemoveTest() {
//        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
//        AddressBookDAO addressBook2 = ctx.getBean("dao",AddressBookDAO.class);
//        
//        Address address2 = new Address();
//        address2.setAddressId(2);
//        address2.setLastName("Jones");
//        address2.setFirstNames(new String[]{"Alex", "Mary"});
//        address2.setPhoneNumbers(new String[]{"999-999-9999"});
//        address2.setStreetNumber("434");
//        address2.setStreetName("S Main St");
//        address2.setCityName("Akron");
//        address2.setStateName("Ohio");
//        address2.setZipCode("44311");
//        Address address3 = new Address();
//        address3.setAddressId(3);
//        address3.setLastName("Duggar");
//        address3.setFirstNames(new String[]{"JimBob", "Jane"});
//        address3.setPhoneNumbers(new String[]{"999-999-9999"});
//        address3.setStreetNumber("995");
//        address3.setStreetName("E Exchange St");
//        address3.setCityName("Akron");
//        address3.setStateName("Ohio");
//        address3.setZipCode("44311");
//        addressBook2.addAddress(address2);
//        addressBook2.addAddress(address3);
//        
//        List<Address> addressList = addressBook.getByLastName("Smith");
//        addressBook.removeAddress(addressList.get(0).getAddressId());
//        
//       addressList = addressBook.listAddressesSorted();
//        List<Address> addressList2 = addressBook2.listAddressesSorted();
//        
//        //Assert.assertEquals(addressBook.getByLastName("Smith"), addressBook2.getByLastName("Smith"));
//        Assert.assertEquals(addressList.size(), addressList2.size());
//    }
//    
//    @Test
//    public void AddressBookUpdateTest() {
//        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
//        AddressBookDAO addressBook2 = ctx.getBean("dao",AddressBookDAO.class);
//        
//        Address address1 = new Address();
//        address1.setAddressId(1);
//        address1.setLastName("Meijer");
//        address1.setFirstNames(new String[]{"Fred", "Lena"});
//        address1.setPhoneNumbers(new String[]{"999-999-9999"});
//        address1.setStreetNumber("622");
//        address1.setStreetName("N Monroe St");
//        address1.setCityName("Grand Rapids");
//        address1.setStateName("Michigan");
//        address1.setZipCode("44311");
//        Address address2 = new Address();
//        address2.setAddressId(2);
//        address2.setLastName("Jones");
//        address2.setFirstNames(new String[]{"Alex", "Mary"});
//        address2.setPhoneNumbers(new String[]{"999-999-9999"});
//        address2.setStreetNumber("434");
//        address2.setStreetName("S Main St");
//        address2.setCityName("Akron");
//        address2.setStateName("Ohio");
//        address2.setZipCode("44311");
//        Address address3 = new Address();
//        address3.setAddressId(3);
//        address3.setLastName("Duggar");
//        address3.setFirstNames(new String[]{"JimBob", "Jane"});
//        address3.setPhoneNumbers(new String[]{"999-999-9999"});
//        address3.setStreetNumber("995");
//        address3.setStreetName("E Exchange St");
//        address3.setCityName("Akron");
//        address3.setStateName("Ohio");
//        address3.setZipCode("44311");
//        addressBook2.addAddress(address1);
//        addressBook2.addAddress(address2);
//        addressBook2.addAddress(address3);
//        
//        //addressBook.updateAddress(address1);
//        addressBook.addAddress(address1);
//        
//        Assert.assertEquals(addressBook.getByLastName("Meijer"), addressBook2.getByLastName("Meijer"));
//    }
//}
