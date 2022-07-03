package config.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

//package com.springboot.chapter3.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import config.pojo.definition.*;

@Component("user")
public class User {
	@Value("1")
	private Long id;
	@Value("user_name_1")
	private String userName;
	@Value("note_1")
	private String note;

	@Autowired(required = false)
	private NotScan ns;

	public void setNotScan(NotScan ns){
		this.ns = ns;
	}

	public NotScan getNotScan(){
		return this.ns;
	}


	@Autowired
	@Qualifier("dog")
    private Animal animal;

	public void setAnimal(Animal a) {
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
        System.out.println(this.id + "  " + this.userName + "  " + this.note);
    }

}