/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.thesoftwareguild.dvdlibraryweb.dto;

import com.swcguild.dvdlibrary.dto.Dvd;

/**
 *
 * @author calarrick
 */
public class DvdTranslator extends Dvd {
    
    
    //is still sticking to using Dvd dao interface and Dvd DTO specced in earlier assignment
    //(the dependency-injection refactoring exercise) so have worked around
    //those constraints. 
    
    //still working on complications for this of doing server-side validation
    
private String releaseDateString;

    public String getReleaseDateString() {
        return releaseDateString;
    }

    public void setReleaseDateString(String releaseDate) {
        this.releaseDateString = releaseDate;
    }


    

}
