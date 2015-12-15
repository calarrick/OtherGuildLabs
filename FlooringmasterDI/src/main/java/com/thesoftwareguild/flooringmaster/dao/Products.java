/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.thesoftwareguild.flooringmaster.dao;

import com.thesoftwareguild.flooringmaster.dto.Product;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
//import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author calarrick
 */
public class Products {

    private Map<String, Product> productList = new HashMap();
    private final File FILE_NAME = new File("./products.xml");
    
    public Products() {
        try {
            readProductFile();
        } catch (IOException ex) {
            System.out.println("Could not load the products file.");
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.productList);
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
        final Products other = (Products) obj;
        if (!Objects.equals(this.productList, other.productList)) {
            return false;
        }
        return true;
    }
    
    public Product getProduct(String productType) {
        return productList.get(productType);
    }
    
    public ArrayList<Product> listAllProducts() {
        ArrayList<Product> products = new ArrayList();
        for (Product product: productList.values()) {
            products.add(product);
        }
        return products;
    }
    

    private void readProductFile() throws IOException {
        Document document = Jsoup.parse(FILE_NAME, "UTF-8");
        Elements products = document.select("product");

    
    
    
    
    //private void readProductFile() throws FileNotFoundException {
        //Scanner sc = new Scanner(new BufferedReader(new FileReader(FILE_NAME)));

        
        for (int i = 0; i < products.size(); i++) {
            Product product = new Product();
            product.setProductName(products.get(i).child(0).text());
            product.setCostPerSquareFoot(Double.parseDouble(products.get(i).child(1).text()));
            product.setLaborCostPerSquareFoot(Double.parseDouble(products.get(i).child(2).text()));
            
            productList.put(product.getProductName(), product);
        }
    }
}
