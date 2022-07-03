package config.pojo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;

//package com.springboot.chapter3.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeansException;
import config.pojo.definition.*;

@Component("user")
public class User implements BeanNameAware, BeanFactoryAware, ApplicationContextAware{

	private Long id;

	private String userName;

	private String note;

	private NotScan ns;

    private Animal animal;

	public User(@Value("1") Long id, @Value("user_name_1") String userName, @Value("note_1") String note,
	@Autowired(required = false) NotScan ns){
		System.out.println("in constructor");
		this.id = id;
		this.userName = userName;
		this.note = note;
		this.ns = ns;
		// this.animal = animal;
	}

	private String beanName;
	@Override
    public void setBeanName(String beanName) {
		this.beanName = beanName;
        System.out.println("【" + this.getClass().getSimpleName()
                + "】调用BeanNameAware的setBeanName  " + "beanName is " + beanName);
    }

	@Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("..........................【" + this.getClass().getSimpleName()
                + "】调用BeanFactoryAware的setBeanFactory " + beanFactory.getBean(this.beanName));
    }

	@Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("【" + this.getClass().getSimpleName()
                + "】调用ApplicationContextAware的setApplicationContext");

    }

	@PostConstruct
	public void pC(){
		System.out.println("i am calling postConstruct method!");
	}

	@PreDestroy
	public void pD(){
		System.out.println("i am calling preDestroy method!");
	}

	public void setNotScan(NotScan ns){
		this.ns = ns;
	}

	public NotScan getNotScan(){
		return this.ns;
	}

	@Autowired 
	@Qualifier("dog")
	public void setAnimal(Animal a) {
		System.out.println("in setAnimal");
		this.animal = a;
	}

	public Animal getAnimal(){
		return this.animal;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

    public void printMyself(){
		this.animal.eat();
        System.out.println(this.id + "  " + this.userName + "  " + this.note + "  " + (this.ns == null));
    }

}