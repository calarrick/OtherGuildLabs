/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.thesoftwareguild.addressbook.validation;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.ResponseStatus;

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
    
    //is a base implentation of methodargumentnotvalid exception that just returns 400
    //that's not adequate in ajax context, so 
    //we are going to grab it and do something more meaningful with it 
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorContainer processValidationErrors(MethodArgumentNotValidException e){
        
        //want to pull in binding-response from our endpoint 'out of' the exception
        //this is fairly platform specific stuff, and a way to intervene/override
        //following the platform's way to do it
        
        BindingResult result = e.getBindingResult();
        
        //field error is yet another dto that spring framework provides
        List<FieldError> fieldErrors = result.getFieldErrors();
        ValidationErrorContainer errors = new ValidationErrorContainer();
        
        //so taking rather complex dtos out of bindingresult and simplifying
        
        for(FieldError currentError: fieldErrors){
            
            errors.addValidationError(currentError.getField(), currentError.getDefaultMessage());
                        
        }
        return errors;
        
    }

    //so @Valid asked it to check against what we'd specified in annotations on some of our params
    //passes certain kind of exceptions
    //we then intercept w/ this advice
    //so any endpoint w/ @Valid will get 'entangled' in validation stuff, 
    //once that happens and we have a discrepency, exception thrown
    //this AOP ties on to any method in controller endpoint that throws exception 
    
    
    
    
}
