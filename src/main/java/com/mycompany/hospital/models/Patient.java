package com.mycompany.hospital.models;

import java.util.UUID;

public class Patient {
	private String id;
	// private int age;
	private String fullName;
	private String number;
	private String birthDate;

	public Patient() {
		this.id = UUID.randomUUID().toString();
	}

	public Patient(String fullName, String number, String birthDate) {
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

	public String getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(String birthDate){
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "Patient [fullName=" + fullName + ", number=" + number + ", birthDate=" + birthDate + "]";
	}

}
