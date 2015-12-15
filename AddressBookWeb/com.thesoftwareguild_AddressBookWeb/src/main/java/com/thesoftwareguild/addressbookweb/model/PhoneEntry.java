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
public class PhoneEntry {
    
    private int phoneId;
    private String phoneNum;
    private int addressId;

    public int getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(int phoneId) {
        this.phoneId = phoneId;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.phoneId;
        hash = 17 * hash + Objects.hashCode(this.phoneNum);
        hash = 17 * hash + this.addressId;
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
        final PhoneEntry other = (PhoneEntry) obj;
        if (this.phoneId != other.phoneId) {
            return false;
        }
        if (!Objects.equals(this.phoneNum, other.phoneNum)) {
            return false;
        }
        if (this.addressId != other.addressId) {
            return false;
        }
        return true;
    }
    
    

}
