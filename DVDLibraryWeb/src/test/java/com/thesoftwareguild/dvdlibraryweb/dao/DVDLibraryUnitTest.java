package com.thesoftwareguild.dvdlibraryweb.dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.swcguild.dvdlibrary.dao.DvdLibraryDao;
import com.swcguild.dvdlibrary.dto.Dvd;




import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author apprentice
 */
public class DVDLibraryUnitTest {

    DvdLibraryDao dao;
        

    public DVDLibraryUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
       ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("dao", DvdLibraryDao.class);
        DvdLibraryDao dvdLibrary2 = dao = ctx.getBean("dao", DvdLibraryDao.class);
        
        JdbcTemplate cleanup = ctx.getBean("jdbcTemplate", JdbcTemplate.class);
        
        cleanup.update("DELETE FROM Dvds");
        
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
    public void DVDLibraryReadWriteTest() {
        

        Dvd dvd1 = new Dvd();
        //dvd1.setId(1);
        dvd1.setTitle("Star Wars");
        dvd1.setReleaseDate(LocalDate.of(1977, Month.MAY, 25));
        dvd1.setDirector("George Lucas");
        dvd1.setMpaaRating("PG-13");
        String note = "It was okay";
        List<String> notes1 = new ArrayList();
        notes1.add(note);
        dvd1.setNote(note);

        Dvd dvd2 = new Dvd();
        //dvd2.setId(2);
        dvd2.setTitle("Jurassic Park");
        dvd2.setReleaseDate(LocalDate.of(1994, Month.JUNE, 11));
        dvd2.setDirector("Some Dude");
        dvd2.setMpaaRating("PG-13");
        note = "It was okay";
        List<String> notes2 = new ArrayList();
        notes2.add(note);
        dvd2.setNote(note);

        Dvd dvd3 = new Dvd();
        //dvd3.setId(3);
        dvd3.setTitle("Some Movie");
        dvd3.setReleaseDate(LocalDate.of(1987, Month.MARCH, 15));
        dvd3.setDirector("Some Other Guy");
        dvd3.setStudio("Backlot Tedium");
        dvd3.setMpaaRating("R");
        note = "Good";
         
        List<String> notes3 = new ArrayList();
        String note3 = "";
        dvd3.setNote(note);

        List<String> attListMem = new ArrayList<>();
        
        attListMem.add(dvd2.getTitle());
        attListMem.add(dvd2.getDirector());
        attListMem.add(dvd3.getTitle());
        attListMem.add(dvd3.getDirector());
        attListMem.add(dvd1.getTitle());
        attListMem.add(dvd1.getDirector());
        
        
       
        
        
        
        dao.add(dvd1);
        dao.add(dvd2);
        dao.add(dvd3);

        List<String> attListDb = new ArrayList<>();
        List<Dvd> dvds = new ArrayList<>();
        dvds = dao.listAll();
        for (Dvd dvd : dvds){
            attListDb.add(dvd.getTitle());
            attListDb.add(dvd.getDirector());
        }
        

        assertEquals(attListMem, attListDb);
    }

    @Test
    public void DVDLibraryGetAllDVDTest() {
        Dvd dvd1 = new Dvd();
        //dvd1.setId(1);
        dvd1.setTitle("Star Wars");
        dvd1.setReleaseDate(LocalDate.of(1977, Month.MAY, 25));
        dvd1.setDirector("George Lucas");
        dvd1.setMpaaRating("PG-13");
        String note = "It was okay";
        List<String> notes1 = new ArrayList();
        notes1.add(note);
        dvd1.setNote(note);

        Dvd dvd2 = new Dvd();
        //dvd2.setId(2);
        dvd2.setTitle("Jurassic Park");
        dvd2.setReleaseDate(LocalDate.of(1994, Month.JUNE, 11));
        dvd2.setDirector("Some Dude");
        dvd2.setMpaaRating("PG-13");
        note = "It was okay";
        List<String> notes2 = new ArrayList();
        notes2.add(note);
        dvd2.setNote(note);

        Dvd dvd3 = new Dvd();
        //dvd3.setId(3);
        dvd3.setTitle("Some Movie");
        dvd3.setReleaseDate(LocalDate.of(1987, Month.MARCH, 15));
        dvd3.setDirector("Some Other Guy");
        dvd3.setStudio("Backlot Tedium");
        dvd3.setMpaaRating("R");
        note = "Good";
         
        List<String> notes3 = new ArrayList();
        notes3.add(note);
        dvd3.setNote(note);

        dao.add(dvd1);
        dao.add(dvd2);
        dao.add(dvd3);

        List<Dvd> expectedList = new ArrayList();
        
        expectedList.add(dvd2);
        expectedList.add(dvd3);
        expectedList.add(dvd1);
        
        List<Dvd> resultList = dao.listAll();
        
        List<String> result = new ArrayList<>();
        List<String> expected = new ArrayList<>();
       
        for(Dvd dvd: expectedList){
            expected.add(dvd.getStudio());
            expected.add(dvd.getDirector());
            expected.add(dvd.getNote());
            
        }
        for(Dvd dvd: resultList){
            
            result.add(dvd.getStudio());
            result.add(dvd.getDirector());
            result.add(dvd.getNote());
            
        }
        
        assertEquals(expected, result);
    }

    @Test
    public void DVDLibraryGetDVDsByTitleTest() {
        Dvd dvd1 = new Dvd();
        //dvd1.setId(1);
        dvd1.setTitle("Star Wars");
        dvd1.setReleaseDate(LocalDate.of(1977, Month.MAY, 25));
        dvd1.setDirector("George Lucas");
        dvd1.setMpaaRating("PG-13");
        String note = "It was okay";
        List<String> notes1 = new ArrayList();
        notes1.add(note);
        dvd1.setNote(note);

        Dvd dvd2 = new Dvd();
        //dvd2.setId(2);
        dvd2.setTitle("Jurassic Park");
        dvd2.setReleaseDate(LocalDate.of(1994, Month.JUNE, 11));
        dvd2.setDirector("Some Dude");
        dvd2.setMpaaRating("PG-13");
        note = "It was okay";
        List<String> notes2 = new ArrayList();
        notes2.add(note);
        dvd2.setNote(note);

        Dvd dvd3 = new Dvd();
        //dvd3.setId(3);
        dvd3.setTitle("Some Movie");
        dvd3.setReleaseDate(LocalDate.of(1987, Month.MARCH, 15));
        dvd3.setDirector("Some Other Guy");
        dvd3.setStudio("Backlot Tedium");
        dvd3.setMpaaRating("R");
        note = "Good";
         
        List<String> notes3 = new ArrayList();
        String note3 = "";
        dvd3.setNote(note);
        
        Dvd dvd4 = new Dvd();
        
        //dvd4.setId(4);
        dvd4.setTitle("Some Movie");
        dvd4.setReleaseDate(LocalDate.of(1994, Month.JUNE, 11));
        dvd4.setDirector("Some Other Guy");
        dvd4.setStudio("Buy and Large Studios");
        dvd4.setMpaaRating("R");
        note = "It was okay";
        List<String> notes4 = new ArrayList();
        notes4.add(note);
        dvd4.setNote(note);

        dao.add(dvd1);
        dao.add(dvd2);
        dao.add(dvd3);
        dao.add(dvd4);

        List<Dvd> expected = new ArrayList();
        expected.add(dvd3);
        expected.add(dvd4);
        List<Dvd> result = dao.getByTitle("Some Movie");
        assertEquals(expected.size(), result.size());
    }

    @Test
    public void DVDLibraryUpdateDVDTest() {
        Dvd dvd1 = new Dvd();
        //dvd1.setId(1);
        dvd1.setTitle("Star Wars");
        dvd1.setReleaseDate(LocalDate.of(1977, Month.MAY, 25));
        dvd1.setDirector("George Lucas");
        dvd1.setMpaaRating("PG-13");
        dvd1.setStudio("Twentieth Century Fox");
        String note = "It was okay";
        List<String> notes1 = new ArrayList();
        notes1.add(note);
        dvd1.setNote(note);

        Dvd dvd2 = new Dvd();
        //dvd2.setId(1);
        dvd2.setTitle("Chuck Wars");
        dvd2.setReleaseDate(LocalDate.of(1977, Month.MARCH, 15));
        dvd2.setDirector("Scum Lucas");
        dvd2.setMpaaRating("R");
        dvd2.setStudio("Knockoff Club");
        String note2 = "it sucked";
        List<String> notes2 = new ArrayList();
        notes2.add(note2);
        dvd2.setNote(note);

        dao.add(dvd1);
        dao.add(dvd2);
        
        LocalDate newDate = LocalDate.of(1977, Month.MAY, 25);
        List<Dvd> retrieval = dao.getByTitle("Chuck Wars");
        Dvd dvd3 = retrieval.get(0);
        
        assertEquals("Chuck Wars", dvd3.getTitle());
            
            dvd3.setTitle("Star Wars");
            
        
        
        

        assertEquals("Star Wars", dvd3.getTitle());
    }

}
