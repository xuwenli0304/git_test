//package com.springboot.chapter1.main;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RestController;

// @SpringBootApplication
// @Controller
// @EnableAutoConfiguration
@RestController
@EnableAutoConfiguration
public class Chapter1Main {
	
	public static void main(String[] args) {
		SpringApplication.run(Chapter1Main.class, args);
	}
	
	@RequestMapping("/test")
	@ResponseBody
	public Map<String, String> test() {
		Map<String, String> map = new HashMap<>();
        // IoCTest i = new IoCTest();
        // i.work();
		map.put("key1", "value1");
		return map;
	}
}
