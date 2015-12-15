/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.thesoftwareguild.addressbookweb.model;

import java.util.Objects;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author calarrick
 */
public class EmailEntry {
    
    private int emailId;
    
    @NotEmpty(message="You must supply a valid email address")
    @Email(message="You must supply a valid email address")
    @Length(max=50, message="Email address must be no more than 50 characters in length.")
    private String emailAddress;
    
    
    private int addressId;

    public int getEmailId() {
        return emailId;
    }

    public void setEmailId(int emailId) {
        this.emailId = emailId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
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
        hash = 73 * hash + this.emailId;
        hash = 73 * hash + Objects.hashCode(this.emailAddress);
        hash = 73 * hash + this.addressId;
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
        final EmailEntry other = (EmailEntry) obj;
        if (this.emailId != other.emailId) {
            return false;
        }
        if (!Objects.equals(this.emailAddress, other.emailAddress)) {
            return false;
        }
        if (this.addressId != other.addressId) {
            return false;
        }
        return true;
    }
    

}
