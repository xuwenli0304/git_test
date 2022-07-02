package config;

// package com.springboot.chapter3.config;

// import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import config.pojo.*;

public class IoCTest {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		User person = ctx.getBean(User.class);
		person.printMyself();
		ctx.close();
	}
}