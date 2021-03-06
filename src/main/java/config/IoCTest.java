package config;

//import org.springframework.beans.factory.annotation.Autowired;

// package com.springboot.chapter3.config;

// import org.springframework.context.ApplicationContext;

import java.util.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import config.pojo.*;
import config.pojo.definition.Animal;
//import config.*;
import config.pojo.definition.HelloService;
import config.pojo.interceptor.MyInterceptor;

public class IoCTest {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		User user1 = (User)ctx.getBean(User.class);
        //User u = AppConfig.initUser();
        User user2 = (User)ctx.getBean(User.class);
		System.out.println("if they are the same user " + (user1 == user2));

        NotScan ns = (NotScan)ctx.getBean(NotScan.class);
        System.out.println("ns is....." + ns);
        
        System.out.println("--------------------------------------");

        Animal dog = (Dog)ctx.getBean(Dog.class);
        dog.eat();

        System.out.println("--------------------------------------");
        
        System.out.println(Arrays.toString(ctx.getBeanDefinitionNames()));


        // CleanUser cn = (CleanUser)ctx.getBean("cu");
        // System.out.println(cn.getName());

        // // try {
        // //     NotScan ns = (NotScan)ctx.getBean(NotScan.class);
        // // } catch (Exception e) {
        // //     System.out.println("no such bean!");
        // //     e.printStackTrace();
        // // }
        // System.out.println("testing begins...........");
        // user1.printMyself();
        // // System.out.println(user1.getAnimal());
        // // user1.getAnimal().eat();

        // // System.out.println(user1.getNotScan() == null);
        // ConfigFileInjection cfi = (ConfigFileInjection)ctx.getBean(ConfigFileInjection.class);
        // System.out.println("----------------------" + cfi.getC());
        // System.out.println(ctx.getEnvironment().getProperty("server.port"));
		
        // HelloService hs = new HelloServiceImpl();
        // HelloService hsProxied = (HelloService) ProxyBean.getProxyBean(hs, new MyInterceptor());
        // hsProxied.sayHello("will");
        ctx.close();
	}
}