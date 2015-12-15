/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.flooringmaster.app;

import com.thesoftwareguild.flooringmaster.aop.TimerAspect;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 1
 *
 * @author calarrick
 */
public class App {

    public static void main(String[] args) {
        
        
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        
        FlooringMasterController controller = ctx.getBean("controller", FlooringMasterController.class);
        
        
        controller.app();
        
    }

}
