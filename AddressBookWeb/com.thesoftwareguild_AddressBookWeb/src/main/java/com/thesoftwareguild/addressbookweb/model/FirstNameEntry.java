/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.thesoftwareguild.addressbookweb.model;

import java.util.Objects;

/**
 *
 * @author calarrick
 */
public class FirstNameEntry {
    
    private int fNameId;
    private String firstName;
    private int addressId;

    public int getfNameId() {
        return fNameId;
    }

    public void setfNameId(int fNameId) {
        this.fNameId = fNameId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.fNameId;
        hash = 37 * hash + Objects.hashCode(this.firstName);
        hash = 37 * hash + this.addressId;
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
        final FirstNameEntry other = (FirstNameEntry) obj;
        if (this.fNameId != other.fNameId) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (this.addressId != other.addressId) {
            return false;
        }
        return true;
    }
    
    

}
