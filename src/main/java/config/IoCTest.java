package config;

//import org.springframework.beans.factory.annotation.Autowired;

// package com.springboot.chapter3.config;

// import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import config.pojo.*;

public class IoCTest {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		User user1 = (User)ctx.getBean(User.class);
        //User u = AppConfig.initUser();
        User user2 = (User)ctx.getBean(User.class);
		System.out.println("if they are the same user " + (user1 == user2));

        CleanUser cn = (CleanUser)ctx.getBean("cu");
        System.out.println(cn.getName());

        // try {
        //     NotScan ns = (NotScan)ctx.getBean(NotScan.class);
        // } catch (Exception e) {
        //     System.out.println("no such bean!");
        //     e.printStackTrace();
        // }

        System.out.println(user1.getAnimal());
        user1.getAnimal().eat();

		ctx.close();
	}
}