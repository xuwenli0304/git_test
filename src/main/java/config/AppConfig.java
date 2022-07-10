package config;

import org.springframework.context.annotation.Bean;

//package com.springboot.chapter3.config;

// import java.util.Properties;

// import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSourceFactory;
// import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
// import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.FlashMapManager;

import config.pojo.*;


@Configuration
@ComponentScan(basePackages = {"config.*", "config.pojo.interceptor"}, 
lazyInit = false,
excludeFilters = {@Filter(classes = {Service.class})})
@ImportResource(value = {"classpath:spring-other.xml"})
public class AppConfig {

    @Bean(name = "cu")
    public CleanUser getCleanUser(){
        return new CleanUser("clean");
    }
	
	// @Bean(name = "dataSource")
	// public DataSource getDataSource() {
	//     Properties props = new Properties();
	//     props.setProperty("driver", "com.mysql.jdbc.Driver");
	//     props.setProperty("url", "jdbc:mysql://localhost:3306/chapter3");
	//     props.setProperty("username", "root");
	//     props.setProperty("password", "123456");
	//     DataSource dataSource = null;
	//     try {
	//         dataSource = BasicDataSourceFactory.createDataSource(props);
	//     } catch (Exception e) {
	//         e.printStackTrace();
	//     }
	//     return dataSource;
	// }
}