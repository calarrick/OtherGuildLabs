/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flooringmaster.aop;

import com.thesoftwareguild.flooringmaster.dto.Order;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 *
 * @author calarrick
 */
public class Audit {

    private Object logOrder(ProceedingJoinPoint jp) throws IOException {
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("AuditLog.txt", true)));
        Object ret = null;
        
        try{
            LocalDateTime logTime = LocalDateTime.now();
            String logTimeString = logTime.toString();
            writer.println(jp.getSignature().getName() + "::" + logTimeString);
            writer.flush();
            writer.close();
            ret = jp.proceed();
        }
        catch(Throwable ex)
        {
            System.out.println("Exception in Audit.logOrder()");
            
        }
        return ret;
        
        
        
       

    
        
}

}
