/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.thesoftwareguild.flooringmaster.dao;

import com.thesoftwareguild.flooringmaster.dto.Tax;

import java.io.File;
import java.io.IOException;
//import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


/**
 *
 * @author calarrick
 */
public class Taxes {
    
    private Map<String, Tax> taxList = new HashMap();
    private final File FILE_NAME = new File("./taxes.xml");
    
    public Taxes() {
        try {
            readTaxFile();
        } catch (IOException ex) {
            System.out.println("Could not load the tax file.");
        }
    }
    
    public ArrayList<Tax> listAllStates() {
    
        ArrayList<Tax> taxingStates = new ArrayList<>();
    for (Map.Entry<String,Tax> state : taxList.entrySet()){
        taxingStates.add(state.getValue());
        }
    return taxingStates;
    
    
}
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.taxList);
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
        final Taxes other = (Taxes) obj;
        if (!Objects.equals(this.taxList, other.taxList)) {
            return false;
        }
        return true;
    }
    
    public Tax getTaxRate(String state) {
        return taxList.get(state);
    }
    
    private void readTaxFile() throws IOException {
        Document document = Jsoup.parse(FILE_NAME, "UTF-8");
        Elements states = document.select("state");
        
        for (int i = 0; i < states.size(); i++) {
            Tax tax = new Tax();
            tax.setState(states.get(i).child(0).text());
            tax.setTaxRate(Double.parseDouble(states.get(i).child(1).text()));
            
            taxList.put(tax.getState(), tax);
        }
    }
}
