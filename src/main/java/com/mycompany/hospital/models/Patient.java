package com.mycompany.hospital.models;

import java.util.Date;
import java.util.UUID;

class Patient {
	private String id;
	private int age;
	private String name;
	private String number;
	private Long date;

	public Patient() {
		this.id = UUID.randomUUID().toString();
		this.date = new Date().getTime();
	}

	public Patient(int age, String name, String number) {
		this.id = UUID.randomUUID().toString();
		this.age = age;
		this.name = name;
		this.number = number;
		this.date = new Date().getTime();
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAge() {
		return this.age;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getNumber() {
		return this.number;
	}

	public Long getDate() {
		return this.date;
	}

}
