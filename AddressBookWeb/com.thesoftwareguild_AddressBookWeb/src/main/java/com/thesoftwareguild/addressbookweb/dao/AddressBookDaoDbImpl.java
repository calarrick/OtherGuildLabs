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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author calarrick
 */
public class AddressBookDaoDbImpl implements AddressBookDAO {

    private JdbcTemplate jdbcTemplate;

    private List<Address> addressList = new ArrayList<>();

    private static final String SQL_INSERT_ADDRESS_MAIN
            = "INSERT INTO Addresses (last_name, street_num, street_name, apt_num, city_name, state_name, zip_code)"
            + "values(?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_INSERT_FIRST_NAMES
            = "INSERT INTO First_names (address_id, first_name) values (?, ?)";

    private static final String SQL_UPDATE_FIRST_NAMES
            = "UPDATE First_names SET first_name = ? WHERE address_id = ?";

    private static final String SQL_INSERT_PHONE
            = "INSERT INTO Phone_numbers(address_id, phone_num) values(?, ?)";

    private static final String SQL_UPDATE_PHONE
            = "UPDATE Phone_numbers SET phone_num = ? WHERE address_id = ?";

    private static final String SQL_INSERT_EMAIL_ADDRESSES
            = "INSERT INTO Email_addresses (address_id, email_address) values(?, ?)";

    private static final String SQL_UPDATE_EMAIL_ADDRESSES
            = "UPDATE Email_addresses SET email_address = ? WHERE address_id = ?";

    private static final String SQL_DELETE_ADDRESS_MAIN
            = "DELETE FROM Addresses WHERE address_id=?";

    private static final String SQL_SELECT_ADDRESS
            = "SELECT * FROM Addresses "
            + "WHERE address_id=? ";

    private static final String SQL_DELETE_FIRST_BY_ADDRESS_ID
            = "DELETE FROM First_names     WHERE address_id = ?";

    private static final String SQL_DELETE_EMAILS_BY_ADDRESS_ID
            = "DELETE FROM Email_addresses WHERE address_id = ?";

    private static final String SQL_DELETE_PHONE_BY_ADDRESS_ID
            = "DELETE FROM Phone_numbers WHERE address_id = ?";

    private static final String SQL_SELECT_FIRST_BY_ADDRESS_ID
            = "SELECT * FROM First_names WHERE address_id = ?";

    private static final String SQL_SELECT_EMAILS_BY_ADDRESS_ID
            = "SELECT * FROM Email_addresses WHERE address_id = ?";

    private static final String SQL_SELECT_PHONE_BY_ADDRESS_ID
            = "SELECT * FROM Phone_numbers WHERE address_id = ?";

    private static final String SQL_UPDATE_ADDRESS
            = "UPDATE Addresses SET last_name=?, street_num=?, street_name=?, apt_num=?,"
            + "city_name=?, state_name=?, zip_code=? "
            + "WHERE address_id = ?";

    private static final String SQL_SELECT_ALL_ADDRESSES
            = "SELECT * FROM Addresses "
            + "ORDER BY last_name ASC";

    private static final String SQL_SELECT_ADDRESSES_BY_LAST_NAME
            = "SELECT * FROM Addresses "
            + "WHERE last_name LIKE ?";

    private static final String SQL_SELECT_ADDRESSES_BY_CITY
            = "SELECT * FROM Addresses "
            + "WHERE city_name=?";

    private static final String SQL_SELECT_ADDRESSES_BY_STATE
            = "SELECT * "
            + "FROM Addresses "
            + "WHERE state_name=?";

    private static final String SQL_SELECT_ADDRESSES_BY_ZIP
            = "SELECT * FROM Addresses "
            + "WHERE zip_code=?";

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Address> listAddressesSorted() {

        return jdbcTemplate.query(SQL_SELECT_ALL_ADDRESSES, new AddressMapper());

    }

    @Override
    public List<Address> getByLastName(String lastName) {

        return jdbcTemplate.
                query(SQL_SELECT_ADDRESSES_BY_LAST_NAME, new AddressMapper());

    }

    @Override
    public List<Address> getByCity(String cityName) {

        return jdbcTemplate.
                query(SQL_SELECT_ADDRESSES_BY_CITY, new AddressMapper());
    }

    @Override
    public List<Address> getByState(String stateName) {

        return jdbcTemplate.
                query(SQL_SELECT_ADDRESSES_BY_STATE, new AddressMapper());
    }

    @Override
    public List<Address> getByZipCode(String zipCode) {

        return jdbcTemplate.
                query(SQL_SELECT_ADDRESSES_BY_ZIP, new AddressMapper());
    }

    @Override
    public Address getById(int id) {

        Address address;
        List<FirstNameEntry> firstNames;
        List<EmailEntry> emails;
        List<PhoneEntry> phones;

        try {
            address = jdbcTemplate.
                    queryForObject(SQL_SELECT_ADDRESS, new AddressMapper(), id);
            firstNames = jdbcTemplate.
                    query(SQL_SELECT_FIRST_BY_ADDRESS_ID, new FirstNameMapper(), id);
            emails = jdbcTemplate.
                    query(SQL_SELECT_EMAILS_BY_ADDRESS_ID, new EmailMapper(), id);
            phones = jdbcTemplate.
                    query(SQL_SELECT_PHONE_BY_ADDRESS_ID, new PhoneNumMapper(), id);

        } catch (EmptyResultDataAccessException ex) {

            return null;
        }

        List<String> names = firstNames.stream().map(fn -> fn.getFirstName())
                .collect(Collectors.toList());
        String[] namesArray = new String[names.size()];
        for (int i = 0; i < namesArray.length; i++) {
            namesArray[i] = names.get(i);
        }
        address.setFirstNames(namesArray);

        List<String> emailAddresses = emails.stream().map(eA -> eA.
                getEmailAddress())
                .collect(Collectors.toList());

//       address.setEmailAddresses(email.toArray());
        String[] emailArray = new String[emailAddresses.size()];
        for (int i = 0; i < emailArray.length; i++) {
            emailArray[i] = emailAddresses.get(i);
        }
        address.setEmailAddresses(emailArray);
//        

        List<String> phoneNums = phones.stream().map(p -> p.getPhoneNum())
                .collect(Collectors.toList());
        String[] phoneArray = new String[phoneNums.size()];
        for (int i = 0; i < phoneArray.length; i++) {
            phoneArray[i] = phoneNums.get(i);
        }
        address.setPhoneNumbers(phoneArray);

        return address;
    }

    @Override
    public List<Address> searchAddresses(Map<SearchTerm, String> criteria) {

        if (criteria == null || criteria.size() == 0) {
            return listAddressesSorted();
        }

        StringBuilder query = new StringBuilder("SELECT * FROM Addresses WHERE ");

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

        return jdbcTemplate.query(query.toString(), new AddressMapper(), paramVals);

    
    }

    @Override
    public void addAddress(Address address) {
        address = addMainAddress(address);
        addSuppAddress(address);

    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    private Address addMainAddress(Address address) {

        jdbcTemplate.update(SQL_INSERT_ADDRESS_MAIN,
                address.getLastName(),
                address.getStreetNumber(),
                address.getStreetName(),
                address.getAptNum(),
                address.getCityName(),
                address.getStateName(),
                address.getZipCode());

        address
                .setAddressId(jdbcTemplate.
                        queryForObject("SELECT LAST_INSERT_ID()", Integer.class
                        ));

        return address;
    }

    private Address addSuppAddress(Address address) {

        for (String firstName : address.getFirstNames()) {

            jdbcTemplate.update(SQL_INSERT_FIRST_NAMES,
                    address.getAddressId(),
                    firstName);
        }
        for (String phoneNum : address.getPhoneNumbers()) {

            jdbcTemplate.update(SQL_INSERT_PHONE,
                    address.getAddressId(),
                    phoneNum);
        }
        for (String email : address.getEmailAddresses()) {

            jdbcTemplate.update(SQL_INSERT_EMAIL_ADDRESSES,
                    address.getAddressId(),
                    email);
        }

        return address;
    }

    @Override
    public void removeAddress(int addressId) {

        jdbcTemplate.update(SQL_DELETE_FIRST_BY_ADDRESS_ID, addressId);

        jdbcTemplate.update(SQL_DELETE_EMAILS_BY_ADDRESS_ID, addressId);

        jdbcTemplate.update(SQL_DELETE_PHONE_BY_ADDRESS_ID, addressId);

        jdbcTemplate.update(SQL_DELETE_ADDRESS_MAIN, addressId);

    }

    @Override
    public Address updateAddress(Address address) {

        int addressId = address.getAddressId();

        jdbcTemplate.update(SQL_UPDATE_ADDRESS,
                address.getLastName(),
                address.getStreetNumber(),
                address.getStreetName(),
                address.getAptNum(),
                address.getCityName(),
                address.getStateName(),
                address.getZipCode(),
                addressId);

        for (String firstName : address.getFirstNames()) {

            jdbcTemplate.update(SQL_INSERT_FIRST_NAMES,
                    addressId,
                    firstName);
        }
        for (String phoneNum : address.getPhoneNumbers()) {

            jdbcTemplate.update(SQL_INSERT_PHONE,
                    addressId,
                    phoneNum
            );
        }
        for (String email : address.getEmailAddresses()) {

            jdbcTemplate.update(SQL_INSERT_EMAIL_ADDRESSES,
                    addressId,
                    email);
        }

        return address;

    }

    @Override
    public int getAddressCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public FirstNameEntry getFirstNames(int addressId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PhoneEntry getPhoneNumbs(int addressId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EmailEntry getEmails(int addressId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static final class AddressMapper implements RowMapper<Address> {

        @Override
        public Address mapRow(ResultSet rs, int i) throws SQLException {

            Address address = new Address();

            address.setAddressId(rs.getInt("address_id"));
            address.setLastName(rs.getString("last_name"));
            address.setStreetNumber(rs.getString("street_num"));
            address.setStreetName(rs.getString("street_name"));
            address.setAptNum(rs.getString("apt_num"));
            address.setCityName(rs.getString("city_name"));
            address.setStateName(rs.getString("state_name"));
            address.setZipCode(rs.getString("zip_code"));

            return address;

        }

    }

    private static final class FirstNameMapper implements RowMapper<FirstNameEntry> {

        @Override
        public FirstNameEntry mapRow(ResultSet rs, int i) throws SQLException {

            FirstNameEntry firstName = new FirstNameEntry();

            firstName.setfNameId(rs.getInt("f_name_id"));
            firstName.setFirstName(rs.getString("first_name"));
            firstName.setAddressId(rs.getInt("address_id"));

            return firstName;

        }
    }

    private static final class PhoneNumMapper implements RowMapper<PhoneEntry> {

        @Override
        public PhoneEntry mapRow(ResultSet rs, int i) throws SQLException {

            PhoneEntry phoneNums = new PhoneEntry();

            phoneNums.setAddressId(rs.getInt("address_id"));
            phoneNums.setPhoneNum(rs.getString("phone_num"));

            return phoneNums;

        }

    }

    private static final class EmailMapper implements RowMapper<EmailEntry> {

        @Override
        public EmailEntry mapRow(ResultSet rs, int i) throws SQLException {

            EmailEntry emails = new EmailEntry();
            emails.setEmailId(rs.getInt("email_id"));
            emails.setEmailAddress(rs.getString("email_address"));
            emails.setAddressId(rs.getInt("address_id"));

            return emails;

        }

    }

}

//    private static final class AddressExtractor implements ResultSetExtractor {
//
//        @Override
//        public List<Address> extractData(ResultSet rs) throws SQLException, DataAccessException {
//
//            List<Address> addresses = new ArrayList<>();
//            Address address = null;
//            List<String> firstNameList = null;
//            List<String> phoneList = null;
//            List<String> emailList = null;
//            int firstNameCount = 0;
//            int phoneNumCount = 0;
//            int emailCount = 0;
//            while (rs.next()) {
//                if (address == null) {
//                    address = new Address();
//                    address.setAddressId(rs.getInt("address_id"));
//                    address.setLastName(rs.getString("last_name"));
//                    address.setStreetNumber(rs.getString("street_num"));
//                    address.setStreetName(rs.getString("street_name"));
//                    address.setAptNum(rs.getString("apt_num"));
//                    address.setCityName(rs.getString("city_name"));
//                    address.setStateName(rs.getString("state_name"));
//                    address.setZipCode(rs.getString("zip_code"));
//                    firstNameList = new ArrayList<>();
//                    phoneList = new ArrayList<>();
//                    emailList = new ArrayList<>();
//                    addresses.add(address);
//                }
//                String fName = rs.getString("first_name");
//                if (fName != null) {
//                    firstNameList.add(fName);
//                    firstNameCount++;
//                }
//
//                String pNum = rs.getString("phone_num");
//                if (pNum != null) {
//                    phoneList.add(pNum);
//                }
//
//                String email = rs.getString("email_address");
//                if (email != null) {
//                    emailList.add(email);
//                }
//
//            }
//            for (Address a : addresses) {
//
//                String[] firstNames = firstNameList.toArray(new String[0]);
//                String[] phoneNumbers = phoneList.toArray(new String[0]);
//                String[] emailAddresses = emailList.toArray(new String[0]);
//
//            }
//
//            return addresses;
//        }

