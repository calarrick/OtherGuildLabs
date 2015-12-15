/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.thesoftwareguild.dvdlibraryweb.validation;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author calarrick
 */
//bc spring has very specific context, built in notion of controller
//can specifiy this as a controller advice and framework understands that there 
//are controller beans, and can apply to them
//lots in background w/out our specifying in detail

@ControllerAdvice
public class RestValidationHandler {
    
    
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorContainer processValidationErrors(MethodArgumentNotValidException e){
        
        
        BindingResult result = e.getBindingResult();
        
        //field error is yet another dto that spring framework provides
        List<FieldError> fieldErrors = result.getFieldErrors();
        ValidationErrorContainer errors = new ValidationErrorContainer();
        
        
        
        for(FieldError currentError: fieldErrors){
            
            errors.addValidationError(currentError.getField(), currentError.getDefaultMessage());
                        
        }
        return errors;
        
    }

    
    
    
    
}
