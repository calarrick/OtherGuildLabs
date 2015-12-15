/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.dvdlibraryweb.dao;

import com.swcguild.dvdlibrary.dto.Dvd;
import com.swcguild.dvdlibrary.dao.DvdLibraryDao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author calarrick
 */
public class DVDLibraryDAOImpl implements DvdLibraryDAO {

    private JdbcTemplate jdbcTemplate;
    //private Map<Integer, Dvd> dvdList = new HashMap();

    private final String SQL_INSERT_DVD
            = "INSERT INTO Dvds (title, release_date, mpaa_rating, director, studio, note) "
            + "values(?, ?, ?, ?, ?, ?) ";

//   private final String SQL_INSERT_NOTES = 
//           "INSERT INTO dvd_notes (dvd_id, added_date, note) "
//           + "values(?, ?, ?)";
    private final String SQL_SELECT_DVD = "SELECT * FROM Dvds WHERE dvd_id = ? ";

//   private final String SQL_NOTES = "SELECT * FROM dvd_notes WHERE dvd_id = ? ";
    private final String SQL_SELECT_ALL_DVD
            = "SELECT * FROM Dvds ORDER BY title ASC ";

    private final String SQL_SELECT_ALL_DVD_DATE
            = "SELECT * FROM Dvds ORDER BY release_date ASC ";

    private final String SQL_SELECT_BY_TITLE = "SELECT * FROM Dvds WHERE title = ?";
    private final String SQL_SELECT_BY_DIRECTOR = "SELECT * FROM Dvds WHERE director = ?";
    private final String SQL_SELECT_BY_STUDIO = "SELECT * FROM Dvds WHERE studio = ?";
    private final String SQL_SELECT_BY_RATING = "SELECT * FROM Dvds WHERE mpaa_rating = ?";

    private final String SQL_UPDATE_DVD
            = "UPDATE Dvds SET title = ?, release_date = ?, mpaa_rating = ?, director = ?, "
            + "studio = ?, note = ? WHERE dvd_id = ?";

//   private final String SQL_DELETE_NOTE = "DELETE FROM dvd_notes WHERE note_id = ?";
    private final String SQL_DELETE_DVD = "DELETE FROM Dvds WHERE dvd_id = ?";

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void add(Dvd dvd) {

        jdbcTemplate.update(SQL_INSERT_DVD,
                dvd.getTitle(),
                dvd.getReleaseDate().toString(),
                dvd.getMpaaRating(),
                dvd.getDirector(),
                dvd.getStudio(),
                dvd.getNote());

        dvd.setId(jdbcTemplate.
                queryForObject("SELECT LAST_INSERT_ID()", Integer.class
                ));

        //dvdList.put(dvd.getId(), dvd);
    }

    @Override
    public void remove(int id) {

        jdbcTemplate.update(SQL_DELETE_DVD, id);

    }

    @Override
    public List<Dvd> listAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL_DVD, new DvdMapper());
    }

//    public List<Dvd> listAllDate() {
//        
//        return jdbcTemplate.query(SQL_SELECT_ALL_DVD_DATE, new DvdMapper());
//    }
    @Override
    public Dvd getById(int id) {

        Dvd dvd = new Dvd();

        try {
            return (Dvd) jdbcTemplate.
                    queryForObject(SQL_SELECT_DVD, new DvdMapper(), id);

        } catch (EmptyResultDataAccessException ex) {

            return null;
        }
    }

    @Override
    public List<Dvd> getByTitle(String title) {
        return jdbcTemplate.query(SQL_SELECT_BY_TITLE, new DvdMapper(), title);
    }

    @Override
    public List<Dvd> getByRating(String rating) {
        return jdbcTemplate.query(SQL_SELECT_BY_RATING, new DvdMapper(), rating);
    }

    @Override
    public List<Dvd> getByStudio(String studio) {
        return jdbcTemplate.query(SQL_SELECT_BY_STUDIO, new DvdMapper(), studio);
    }

    public List<Dvd> getByDirector(String director) {

        return jdbcTemplate.
                query(SQL_SELECT_BY_DIRECTOR, new DvdMapper(), director);
    }

    public List<Dvd> searchDvd(Map<SearchTerm, String> criteria) {

        if (criteria == null || criteria.size() == 0) {
            return listAll();
        }

        StringBuilder query = new StringBuilder("SELECT * FROM Dvds WHERE ");

        int numParams = criteria.size();
        int paramPosition = 0;

        String[] paramVals = new String[numParams];

        Set<SearchTerm> keyset = criteria.keySet();
        Iterator<SearchTerm> iter = keyset.iterator();

        while (iter.hasNext()) {

            SearchTerm currentKey = iter.next();
            String currentValue = criteria.get(currentKey);

            if (paramPosition > 0) {
                query.append(" and ");
            }

            query.append(currentKey);

            query.append(" =? ");
            paramVals[paramPosition] = currentValue;

            paramPosition++;
        }

        return jdbcTemplate.
              query(query.toString(), new DvdMapper(), paramVals);

    }

    private static final class DvdMapper implements RowMapper {

        @Override
        public Dvd mapRow(ResultSet rs, int i) throws SQLException {

            Dvd dvd = new Dvd();

            dvd.setId(rs.getInt("dvd_id"));
            dvd.setTitle(rs.getString("title"));
            dvd.setDirector(rs.getString("director"));
            dvd.setMpaaRating(rs.getString("mpaa_rating"));
            dvd.setReleaseDate(rs.getDate("release_date").toLocalDate());
            dvd.setStudio(rs.getString("studio"));
            dvd.setNote(rs.getString("note"));

            return dvd;
        }
    }
}
