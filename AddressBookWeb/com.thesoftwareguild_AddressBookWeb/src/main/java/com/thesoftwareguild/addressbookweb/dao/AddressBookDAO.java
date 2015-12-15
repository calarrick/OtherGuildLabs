/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.addressbookweb.dao;

import com.thesoftwareguild.addressbookweb.model.Address;
import com.thesoftwareguild.addressbookweb.model.EmailEntry;
import com.thesoftwareguild.addressbookweb.model.FirstNameEntry;
import com.thesoftwareguild.addressbookweb.model.PhoneEntry;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author calarrick
 */
public interface AddressBookDAO {
    
    
    public List<Address> listAddressesSorted();
    
    public List<Address> getByLastName(String lastName);
    
    public List<Address> getByCity(String cityName);
    
    public List<Address> getByState(String stateName);
    
    public List<Address> getByZipCode (String zipCode);
    
    public Address getById (int id);
    
    public List<Address> searchAddresses(Map<SearchTerm, String> criteria);
    
    public void addAddress(Address address);
    
    public void removeAddress(int addressId);
    
    public Address updateAddress(Address address);
 
    public int getAddressCount();
    
    public FirstNameEntry getFirstNames(int addressId);
    
    public PhoneEntry getPhoneNumbs (int addressId);
    
    public EmailEntry getEmails (int addressId);
    
        
    //public void saveData() throws IOException;
        
    //public void loadData() throws FileNotFoundException;
}
