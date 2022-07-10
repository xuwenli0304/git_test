//package com.springboot.chapter1.main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RestController;

import config.pojo.*;
import config.pojo.definition.Animal;

// @SpringBootApplication
// @Controller
// @EnableAutoConfiguration
@RestController
@EnableAutoConfiguration
@ComponentScan(basePackages = {"config.*"})
public class Chapter1Main {

    @Value("${a.b}")
    private String ab;
	
    @Autowired
    Environment environment;

    @Autowired
    private PrefixLoad prefixLoad;

    @Autowired
    private ConfigFileInjection configFileInjection;

    @Autowired
    private Animal dog;

    @Autowired
    private ApplicationContext applicationContext;

	public static void main(String[] args) {
		SpringApplication.run(Chapter1Main.class, args);
	}
	
	@RequestMapping("/test")
	@ResponseBody
	public Map<String, String> test() {
		Map<String, String> map = new HashMap<>();
        // IoCTest i = new IoCTest();
        // i.work();
        // System.out.println(environment.getProperty("server.port"));
        // System.out.println(environment.getProperty("a.b"));
        // System.out.println(Arrays.toString(applicationContext.getBeanDefinitionNames()));
        // System.out.println("xxxxxxxxxxxxxxxxxxx");
        // System.out.println(environment);
        System.out.println(configFileInjection.getC());
        // System.out.println("..............." + this.ab);
        boolean met = false;
        String[] names = applicationContext.getBeanDefinitionNames();
        for(String name: names){
            if(name.equals("cat")){
                met = true;
            }
        }
        if(met){
            System.out.println("-----------------met!");
        }else{
            System.out.println("-----------------not met!");
        }
        //System.out.println(prefixLoad.getA());
        dog.eat();
		map.put("key1", "value1");
		return map;
	}
}
