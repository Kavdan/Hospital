package com.mycompany.hospital.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Patient {
	private String id;
	// private int age;
	private String fullName;
	private String number;
	private Date birthDate;

	public Patient() {
		this.id = UUID.randomUUID().toString();
	}

	public Patient(String fullName, String number, Date birthDate) {
		this.id = UUID.randomUUID().toString();
		// this.age = age;
		this.fullName = fullName;
		this.number = number;
		this.birthDate = birthDate;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	// public void setAge(int age) {
	// 	this.age = age;
	// }

	// public int getAge() {
	// 	return this.age;
	// }

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getNumber() {
		return this.number;
	}

	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate){
		this.birthDate = birthDate;
	}

	public int getAge() {
		long yearInMs = 1000L * 60 * 60 * 24 * 365;
    	return (int) ((new Date().getTime() - birthDate.getTime()) / yearInMs);
	}

	public String formatBirthDate(String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(birthDate);
	}

	@Override
	public String toString() {
		return fullName + ":" + number + ":" + formatBirthDate("yyyy-MM-dd");
	}

}
