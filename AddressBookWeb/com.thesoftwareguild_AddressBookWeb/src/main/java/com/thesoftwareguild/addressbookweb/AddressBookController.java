/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.addressbookweb;

import com.thesoftwareguild.addressbookweb.dao.AddressBookDAO;
import com.thesoftwareguild.addressbookweb.model.Address;
import com.thesoftwareguild.addressbookweb.model.FirstNameEntry;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author calarrick
 */
@Controller
@RequestMapping({"/"})
public class AddressBookController {

    private final AddressBookDAO dao;

    @Inject
    public AddressBookController(AddressBookDAO dao) {
        this.dao = dao;
    }

//initial load of page. 
    @RequestMapping(value = {"/", "home"}, method = RequestMethod.GET)
    public String displayAddresses(Model model) {

        //added this for initial load of any existing addresses w/out added ajax query
        List<Address> aList = dao.listAddressesSorted();
        model.addAttribute("addressList", aList);

        return "home";
    }

//on ajax query for addresses... initially fires right after first page load
    @RequestMapping(value = "/addresses", method = RequestMethod.GET) //the plural-noun endpoint
    @ResponseBody
    public List<Address> getAllAddresses() {

        
        return dao.listAddressesSorted();

    }

    @RequestMapping(value = "/address/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Address getAddress(@PathVariable("id") int id) {
        
        return dao.getById(id);
        
    }

    @RequestMapping(value = "/address", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Address addAddress(@Valid @RequestBody Address address) {

        dao.addAddress(address);

        return address;

    }
    
//    @RequestMapping(value = "/address/firstnames", method = RequestMethod.GET)
//    @ResponseBody
//    public FirstNameEntry getFirstNames(@PathVariable("id") int id) {
//        //
//    }

//    @RequestMapping(value = "/displayEditAddressForm", method = RequestMethod.GET)
//    public String displayEditAddressForm(HttpServletRequest req, Model model) {
//
//        List<Address> matches = new ArrayList<>();
//        List<Address> fullMatches = new ArrayList<>();
//        
//        Address address;
//        //List<Address> unmatched = new ArrayList<>();
//        String[] lNames = req.getParameterValues("ln");
//        String[] srNames = req.getParameterValues("srnm");
//        String[] stNames = req.getParameterValues("stnm");
//
//        //testing that presence and format for params matches expectations
//        Enumeration<String> params = req.getParameterNames();
//        if (!(params.nextElement().equals("ln"))) {
//            return "displayEditAddressForm";
//        } else if (!(params.hasMoreElements())) {
//            return "displayEditAddressForm";
//        } else if (!(params.nextElement().equals("srnm"))) {
//            return "displayEditAddressForm";
//        } else if (!(params.hasMoreElements())) {
//            return "displayEditAddressForm";
//        } else if (!(params.nextElement().equals("stnm"))) {
//            return "displayEditAddressForm";
//        } else if (params.hasMoreElements()) {
//            return "displayEditAddressForm";
//        } else {
//
////dumping back if non-match on any param
//            if ((!(lNames.length == 1 && srNames.length == 1) && stNames.length == 1)) {
//                return "displayEditAddressForm";
//
//            } else {// loops to check that our params 'line up' correctly if
//                //we have dupe names
//
//                matches = dao.getByLastName(lNames[0]);
//                fullMatches = dao.getByLastName(lNames[0]);
//                //these should match for now
//
//                if (matches.size() == 1) {
//                    address = matches.get(0);
//                    model.addAttribute("address", address);
//                    return "editAddressForm";
//                    //exit here for unique last name
//
//                } else {
//                    //distinguishing addresses with same last name
//                    
//                    for (Address a : matches) {
//                        String streetName = a.getStreetName();
//                        String stateName = a.getStateName();
//
//                        if (!streetName.equals(srNames[0])) {
//                            fullMatches.remove(a);
//
//                        }
//                        if (!stateName.equals(stNames[0])) {
//                            fullMatches.remove(a);
//                        }
//                    }
//                    
//                    
//
//                if (fullMatches.size() == 1) {
//                    
//                    address = fullMatches.get(0);
//                    model.addAttribute("address", address);
//                    return "editAddressForm";
//                    //returns if we've succesfully zeroed in on an address
//
//                } else if (fullMatches.size() < 1) {//this would require weirdness
//                    //but not trusting the client
//                    
//                    
//                    return "displayEditAddressForm";
//
//                } else {
//
//                        //get here if have more than one matching result
//                    //should be relatively rare but not a total edge case (e.g.
//                    //family members living next door to each other)
//                    
//                    for (Address a: fullMatches){
//                    model.addAttribute("address", a);}
//                    return "displayMultiEditAddressForm";
//                }
//            }
//            }
//        }
//    }
    @RequestMapping(value = "/address/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContact(@PathVariable("id") int id) {

        dao.removeAddress(id);
    }

    
    @RequestMapping(value="/address/{id}", method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putContact(@PathVariable("id") int id, @RequestBody Address address){
        
        address.setAddressId(id);
        dao.updateAddress(address);
                
    }
    
    @RequestMapping(value = "/editAddress", method = RequestMethod.POST)
    public String editAddress(@Valid @ModelAttribute("address") Address address,
            BindingResult result) {

        if (result.hasErrors()) {
            return "editAddressForm";
        }

//        String firstNames = address.getFirstNameBag();
//        String phoneNums = address.getPhoneNumBag();
//        String[] firstNameArray = firstNames.split(",");
//        String[] phoneNumArray = phoneNums.split(",");
//        address.setFirstNames(firstNameArray);
//        address.setPhoneNumbers(phoneNumArray);
        dao.updateAddress(address);

        return "redirect:displayAddressBook";

    }

    /*
     int select = console.
     readInteger("Please enter the number next to your selection: \n"
     + "1) List all address book entries.\n"
     + "2) View full entries, by last name\n"
     + "3) Retrieve entries by town or city\n"
     + "4) Retrieve entries by state\n"
     + "5) Retrieve entries by zip code\n"
     + "6) Create a new address book entry\n"
     + "7) Update an address book entry\n"
     + "8) Remove an address book entry\n"
     + "9) Exit program and save.\n\n"
     + "There are now " + addressBook.getAddressCount() + " entries in the address book.\n\n");

     switch (select) {
     case 1:
     listAddresses();
     break;
     case 2:
     byLastName();
     break;

     case 3:
     byCity();
     break;
     case 4:
     byState();
     break;
     case 5:
     byZipCode();
     break;

     case 6:
     createAddress();
     break;
     case 7:
     updateAddress();
     break;
     case 8:
     removeAddress();
     break;
     case 9:
     try {
     addressBook.saveData();
     } catch (IOException e) {
     System.out.
     println("Unable to write to file at at this point.");
     stayAlive = true;
     }
     stayAlive = false;
     break;

     }

     }
     }
     */
    //private void listAddresses() {
    //  dao.listAddressesSorted();
    //.
    //        stream().
    //       forEach(address -> console.displayPrompt("##" + address.
    //                       getLastName() + "\n" + address.getStreetName() + "\t" + address.
    //                       getCityName() + "\n\n"));
    //}
/*

     private void createAddress() {
        
     boolean done = false;
     Address newAddress = new Address();

     String lastName = console.
     readString("Please enter the last name for your address book entry:");

     List<String> firstNames = new ArrayList();
     while (!done) {
     String firstName = console.
     readString("Please enter a first name for this entry:");
     firstNames.add(firstName);
     String check = console.
     readString("Do you want to enter another first name? (y/n)");
     done = check.equals("n");
     }
     done = false;
     List<String> phoneNumbers = new ArrayList();
     while (!done) {
     String phoneNumber = console.
     readString("Please enter a phone number for this entry:");
     phoneNumbers.add(phoneNumber);
     String check = console.
     readString("Do you want to enter another phone number? (y/n)");
     done = check.equals("n");
     }
     String streetNumber = console.
     readLine("Please enter the street number for this entry:");
     String streetName = console.
     readLine("Please enter the street name for this entry:");
     String cityName = console.
     readLine("Please enter the city name for this entry");
     String stateName = console.
     readLine("Please enter the state name for this entry:");
     String zipCode = console.
     readLine("Please enter the zip code for this entry:");

     newAddress = new Address();
     newAddress.setLastName(lastName);
     String[] firstNamesArray = new String[firstNames.size()];
     newAddress.setFirstNames(firstNames.toArray(firstNamesArray));
     String[] phoneNumbersArray = new String[phoneNumbers.size()];
     newAddress.setPhoneNumbers(phoneNumbers.toArray(phoneNumbersArray));
     newAddress.setStreetNumber(streetNumber);
     newAddress.setStreetName(streetName);
     newAddress.setCityName(cityName);
     newAddress.setStateName(stateName);
     newAddress.setZipCode(zipCode);
        
        
     addressBook.addAddress(newAddress);

     }

     private void removeAddress() {

     Address match;
     List<Address> matches;

     String getName = console.
     readString("Please enter the last name of the person you are looking for:");

     matches = addressBook.getByLastName(getName);

     if (matches.size() <= 0) {
     console.displayPrompt("There are no matches for that name.");
     return;
     }

     if (matches.size() > 1) {
     console.displayPrompt("Which entry do you want to remove?");

     matches.stream()
     .forEach(address -> console.inLine((matches.
     indexOf(address) + 1) + ": " + address.
     getLastName() + Arrays.toString(address.
     getFirstNames()) + ", " + address.
     getCityName() + "\n") );

     int select = console.
     readInteger("\nEnter the number next to the record you want to remove: ", 1, matches.
     size());

     match = matches.get(select - 1);

     } else {
     match = matches.get(0);
     }

     String choice = console.
     readString("Do you really want to remove " + match.
     getLastName() + " from " + match.getCityName()
     + "(Answer with \"y\" or \"n\")");

     if (choice.toLowerCase().equals("y")) {
     addressBook.removeAddress(match);
     }
        
        
        
        

     }

     private void updateAddress() {
        
     List<Address> matches;
     Address match;

     String getName = console.
     readString("Please enter the last name of the person you are looking for:");

     matches = addressBook.getByLastName(getName);

        
     if (matches.size() <= 0) {
     console.displayPrompt("There are no matches for that name.");
     return;
     }

     if (matches.size() > 1) {
            
            
     console.displayPrompt("Which entry do you want to edit?");

     matches.stream()
     .forEach(address -> console.inLine((matches.
     indexOf(address) + 1) + ": " + address.
     getLastName() + Arrays.toString(address.
     getFirstNames()) + ", " + address.
     getCityName() + "\n"));

     int select = console.
     readInteger("\nEnter the number next to the record you want to edit: ", 1, matches.
     size());

     match = matches.get(select - 1);

     } else {
     match = matches.get(0);
     }

     String choice = console.readString("Editing entry for " + match.
     getLastName() + " from " + match.getCityName()
     + "(Answer with \"y\" or \"n\")");

     if (!(choice.toLowerCase().equals("y"))) {

     console.displayPrompt("Cancelling edit, returning to menu...");
     return;
     }

     String lastName = match.getLastName();
     String[] firstNamesArray = match.getFirstNames();
     String[] phoneNumbersArray = match.getPhoneNumbers();
     String streetNumber = match.getStreetNumber();
     String streetName = match.getStreetName();
     String cityName = match.getCityName();
     String stateName = match.getStateName();
     String zipCode = match.getZipCode();

     if (match != null) {
     boolean edit = true;
     boolean done = false;

     printAddress(match);
            
            
     choice = console.
     readString("Current last name for the entry is " + lastName + "\nDo you want to edit the last name? (y/n)");
     if (choice.toLowerCase().equals("y")) {
     lastName = console.
     readString("Please enter the new last name for your address book entry:");
     match.setLastName(lastName);
     }
            
            
            

     List<String> firstNames = new ArrayList();
     console.inLine("Current first names for the entry are: ");
     for (String firstNamesArray1 : firstNamesArray) {
     console.inLine(firstNamesArray1 + " ");
     }
     choice = console.readString("\nDo you want to replace these first names? (y/n)"
     );
     if (choice.equals("y")) {
     while (!done) {
     String firstName = console.
     readLine("Please enter a first name for this entry: ");
     firstNames.add(firstName);
     String check = console.
     readLine("Do you want to enter another first name? (y/n)");
     done = check.equals("n");
     }
     done = false;
     match.setFirstNames(firstNames.toArray(firstNamesArray));
     }

     List<String> phoneNumbers = new ArrayList();
     console.inLine("Current phone numbers for the entry are: ");
     for (String phoneNumbers1 : phoneNumbersArray) {
     console.inLine(phoneNumbers1 + " ");
     }
     choice = console.readString("Do you want to replace these phone numbers? (y/n)");
     if (choice.equals("y")) {
     while (!done) {
     String phoneNumber = console.readLine("Please enter a phone number for this entry:");
     phoneNumbers.add(phoneNumber);
     String check = console.readLine("Do you want to enter another phone number? (y/n)");
     done = check.equals("n");
     }
     match.setPhoneNumbers(phoneNumbers.toArray(phoneNumbersArray));
     }

     choice = console.readString("Do you want to edit the street number? (y/n)");
     if (choice.equals("y")) {
     streetNumber = console.readString("Please enter the street number for this entry:");
     match.setStreetNumber(streetNumber);
     }

     choice = console.readString("Do you want to edit the street name? (y/n)");
     if (choice.equals("y")) {
     streetName = console.readString("Please enter the street name for this entry:");
     match.setStreetName(streetName);
     }

     choice = console.readString("Do you want to edit the city name? (y/n)");
     if (choice.equals("y")) {
     cityName = console.readString("Please enter the city name for this entry");
     match.setCityName(cityName);
     }

     choice = console.readString("Do you want to edit the state name? (y/n)");
     if (choice.equals("y")) {
     stateName = console.readString("Please enter the state name for this entry:");
     match.setStateName(stateName);
     }

     choice = console.readString("Do you want to edit the zip code? (y/n)");
     if (choice.equals("y")) {
     zipCode = console.readString("Please enter the zip code for this entry:");
     match.setZipCode(zipCode);
     }

     }

     }

     //these methods all iterate/stream through the sub-list returned by the DAO
     //arguably makes for an "extra" pass through, though should be a small
     //(usually VERY small in computing terms) list that is returned. 
     //But I see no other way to keep appropriate
     //distinct DAO/controller roles and allow for continued enhancement
     //of control-layer features and UI without altering the 
     //DAO-layer and interface (though realize could 'extend' it w/out changing)
    
    
    
     private void byLastName() {
     String getName = console.readString("Please enter the last name of the person you are looking for:");

     List<Address> lastNamesList = addressBook.getByLastName(getName);

     lastNamesList.stream().forEach(address -> printAddress(address));

     }

     private void byCity() {
     String getName = console.readString("Please enter the city:");

     List<Address> cityNamesList = addressBook.getByCity(getName);

     cityNamesList.stream().forEach(address -> printAddress(address));

     }
    
     private void byState() {
     String getName = console.readString("Please enter the state: ");
     List<Address> stateNamesList = addressBook.getByState(getName);
        
     Map<String, List<Address>> byCity
     = stateNamesList.stream()
     .collect(Collectors.groupingBy(Address::getCityName));
     //got this working, but still don't quite understand the :: grouping 
     byCity.keySet().stream().forEach(city -> {
     System.out.print("\n" + city + ": \n");      
     byCity.get(city).stream().forEach(address -> 
     printAddress(address));});
                
     }
    
     private void byZipCode() {
        
     String getZip = console.readString("Please enter the zip code: ");
     List<Address> zipList = addressBook.getByZipCode(getZip);
        
     console.displayPrompt("\n");
     for (Address address: zipList) {
            
     printAddress(address);
            
     }//with enhanced for here as opposed to stream and lambda used in the city one. about equal
     //readability & simplicity between the two in this case, I think
        
     console.displayPrompt("\n");
        
     }
    
    
                
        

     private void printAddress(Address address) {

     String[] firstNames = address.getFirstNames();
     System.out.print("\n" + address.getLastName() + "; ");
     for (int i = 0; i < firstNames.length; i++) {
     if (i != firstNames.length - 1) {
     System.out.print(firstNames[i] + ", ");
     } else {
     System.out.print(firstNames[i]);
     }
     }
     System.out.print("\n");
     String[] phoneNumbers = address.getPhoneNumbers();
     for (int i = 0; i < phoneNumbers.length; i++) {
     if (i != phoneNumbers.length - 1) {
     System.out.print(phoneNumbers[i] + ", ");
     } else {
     System.out.print(phoneNumbers[i]);
     }
     }
     System.out.print("\n");
     System.out.print(address.getStreetNumber());
     System.out.print(" " + address.getStreetName());
     System.out.print(", " + address.getCityName());
     System.out.print(", " + address.getStateName());
     System.out.println(" " + address.getZipCode());
     System.out.println("\n\n");

     }

     }
     */
}
