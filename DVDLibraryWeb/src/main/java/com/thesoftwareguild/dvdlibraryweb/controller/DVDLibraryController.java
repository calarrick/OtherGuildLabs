 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.dvdlibraryweb.controller;


import com.swcguild.dvdlibrary.dto.Dvd;
import com.thesoftwareguild.dvdlibraryweb.dao.DvdLibraryDAO;
import com.thesoftwareguild.dvdlibraryweb.dao.SearchTerm;
import com.thesoftwareguild.dvdlibraryweb.dto.DvdTranslator;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author apprentice
 */
@Controller
@RequestMapping({"/"})
public class DVDLibraryController {

    private final DvdLibraryDAO dao;
    Comparator<Dvd> dateSort = (dvd1, dvd2) -> dvd1.getReleaseDate().
            compareTo(dvd2.
                    getReleaseDate());
    //public static DateTimeFormatter ISO_LOCAL_DATE;
    
     

    @Inject
    public DVDLibraryController(DvdLibraryDAO dao) {
        this.dao = dao;
    }

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String displayHomePage() {

        return "dvdlibrary";

    }

    @RequestMapping(value = "/dvds", method = RequestMethod.GET) //the plural-noun endpoint
    @ResponseBody
    public List<Dvd> getAllDvds() {

        return dao.listAll();

    }

    @RequestMapping(value = "/dvds_date", method = RequestMethod.GET)
    @ResponseBody
    public List<Dvd> getSortDate() {

        return dao.listAll().stream().sorted(dateSort).collect(Collectors.
                toList());

    }

    @RequestMapping(value = "search/dvds", method = RequestMethod.POST)
    @ResponseBody
    public List<Dvd> searchAddresses(@RequestBody Map<String, String> searchMap) {
        
        Map<SearchTerm, String> criteriaMap = new HashMap<>();

        String currentTerm = searchMap.get("title");
        if (!currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerm.TITLE, currentTerm);
        }

        currentTerm = searchMap.get("director");
        if (!currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerm.DIRECTOR, currentTerm);
        }

        currentTerm = searchMap.get("studio");
        if (!currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerm.STUDIO, currentTerm);
        }

        return dao.searchDvd(criteriaMap);
    }
    
    
    //pulling all these statistical queries from the server even though
    //currently could in client in order to get accurate information
    //once collection is large and db client delivery is being paged
    
    //paged queries might also be usable to make this more efficient too
    //if eliminate need to load full list to mem to get newest/oldest
    //and possibly track average without pulling all to calculate it each time
    //requested
    
    @RequestMapping(value = "/oldest", method = RequestMethod.GET)
    @ResponseBody
    public List<Dvd> getOldest() {
        
        List<Dvd> oldest = listOldestMovies(findOldestAge());
        
       return oldest;
    }
    
    @RequestMapping(value = "/newest", method = RequestMethod.GET)
    @ResponseBody
    public List<Dvd> getNewest() {
        
        List<Dvd> newest = listNewestMovies(findNewestAge());
        return newest;
    }
    
    @RequestMapping(value = "/average_age", method = RequestMethod.GET)
    @ResponseBody
    public double getAverage() {
        
        return findAverage();
        
        
    }
    
    
    @RequestMapping(value = "/dvd", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Dvd addDvd(@RequestBody DvdTranslator dvd) {
        
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ISO_LOCAL_DATE);
        dvd.setReleaseDate(LocalDate.parse(dvd.getReleaseDateString(), ISO_LOCAL_DATE));
        Dvd newDvd = (Dvd) dvd;
        
        dao.add(newDvd);
        
        return dvd;
        
    }
    
    @RequestMapping(value="/dvd/{id}", method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDvd(@PathVariable("id") int id){
        
        
        dao.remove(id);
            }
    
    
    
    
    
    

    //non-endpoint methods
    private int findOldestAge() {

        List<Dvd> allMovies = dao.listAll();
        //Period longestP = null;

        List<Integer> years = new ArrayList<>();
        allMovies.stream().forEach(dvd -> {
            int yearsOld = dvd.getReleaseDate().until(LocalDate.now()).
                    getYears();
            years.add(yearsOld);
        });

        int mostOld = Collections.max(years);
        return mostOld;
    }

    private List<Dvd> listOldestMovies(int mostOld) {
        List<Dvd> oldestMovies = new ArrayList<>();
        List<Dvd> allMovies = dao.listAll();

        for (Dvd movie : allMovies) {

            int yearsOld = movie.getReleaseDate().until(LocalDate.now()).
                    getYears();
            if (yearsOld == mostOld) {

                oldestMovies.add(movie);
            }

        }

        return oldestMovies;
    }
private int findNewestAge() {

        List<Dvd> allMovies = dao.listAll();
        

        List<Integer> years = new ArrayList<>();
        allMovies.stream().forEach(dvd -> {
            int yearsOld = dvd.getReleaseDate().until(LocalDate.now()).
                    getYears();
            years.add(yearsOld);
        });

        int mostNew = Collections.min(years);
        return mostNew;
    }

    private List<Dvd> listNewestMovies(int mostNew) {
        List<Dvd> newestMovies = new ArrayList<>();
        List<Dvd> allMovies = dao.listAll();

        allMovies.stream().
                forEach((movie) -> {
                    int yearsOld = movie.getReleaseDate().until(LocalDate.now()).
                            getYears();
            if (yearsOld == mostNew) {
                newestMovies.add(movie);
            }
        });

        return newestMovies;
    }
    
    
    private double findAverage() {

        List<Dvd> allMovies = dao.listAll();
        List<Integer> years = new ArrayList<>();
        allMovies.stream().forEach(dvd -> {
            int yearsOld = dvd.getReleaseDate().until(LocalDate.now()).
                    getYears();
            years.add(yearsOld);
        });

        
        OptionalDouble average = years
                .stream()
                .mapToDouble(a -> a)
                .average();
        
        return average.getAsDouble();

    }
    
}


