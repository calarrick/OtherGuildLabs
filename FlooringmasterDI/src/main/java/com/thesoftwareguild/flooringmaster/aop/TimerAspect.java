/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flooringmaster.aop;

import org.aspectj.lang.ProceedingJoinPoint;


/**
 *
 * @author calarrick
 */

public class TimerAspect {

    public Object timeMethod(ProceedingJoinPoint jp)
    {
        
        Object ret = null;
        try{
            
            long start = System.currentTimeMillis();
            ret = jp.proceed();
            long end = System.currentTimeMillis();
            System.out.println("+++++++++++++++++++++++++++++++++");
            System.out.println(jp.getSignature().getName() + " took " + (end - start) + "ms.");
            System.out.println("+++++++++++++++++++++++++++++++++");
            
        }
        catch(Throwable ex)
        {
            System.out.println("Exception in simpleTimerAspect.timeMethod()");
            
        }
        return ret;
        
    
    }
}
    
    // Note 1 - ProceedingJoinPoint parameter
    
    /*public Object timeMethod(ProceedingJoinPoint jp) {
        Object ret = null;

        try {

            long start = System.currentTimeMillis();
            ret = jp.proceed();
            long end = System.currentTimeMillis();
            System.out.println(jp.getSignature().getName() + " took "
                    + (end - start) + " ms");
        } catch (Throwable ex) {
            System.out.println(
                    "Exception in timing method");

        }

        return ret;
    }*/

