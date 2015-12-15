/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.thesoftwareguild.addressbookweb;

import com.thesoftwareguild.addressbookweb.dao.AddressBookDAO;
import com.thesoftwareguild.addressbookweb.dao.SearchTerm;
import com.thesoftwareguild.addressbookweb.model.Address;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author calarrick
 */

@Controller
public class SearchController {
    
    private final AddressBookDAO dao;
    
    @Inject
    public SearchController(AddressBookDAO dao) {
        this.dao = dao;
    }
    
     @RequestMapping(value = {"/search"}, method = RequestMethod.GET)
    public String displaySearch() {
    return "search";   
    
    }
    
    @RequestMapping(value="search/addresses", method=RequestMethod.POST)
    @ResponseBody
    public List<Address> searchAddresses(@RequestBody Map<String,String> searchMap)
    {
        Map<SearchTerm, String> criteriaMap = new HashMap<>();
        
        String currentTerm = searchMap.get("lastName");
        if(!currentTerm.isEmpty())
        {
            criteriaMap.put(SearchTerm.LAST_NAME, currentTerm);
        }
        
        
        
        currentTerm = searchMap.get("cityName");
        if(!currentTerm.isEmpty())
        {
            criteriaMap.put(SearchTerm.CITY_NAME, currentTerm);
        }
        
        currentTerm = searchMap.get("stateName");
        if(!currentTerm.isEmpty())
        {
            criteriaMap.put(SearchTerm.STATE_NAME, currentTerm);
        }
        
        currentTerm = searchMap.get("zipCode");
        if(!currentTerm.isEmpty())
        {
            criteriaMap.put(SearchTerm.ZIP_CODE, currentTerm);
        }
        
        
        
        
        return dao.searchAddresses(criteriaMap);
    }
}
    
    
    
    
    
    
    
    
    
