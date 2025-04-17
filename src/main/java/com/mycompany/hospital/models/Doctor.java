package com.mycompany.hospital.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.xml.crypto.Data;

public class Doctor {
	private String id; 
	private String specialization;
	private String schedule;
	private String fullName;
	private Date birthDate;
	private String phoneNumber;
	private Long createdAt;

	public Doctor() {
		this.createdAt = new Date().getTime();
		this.id = UUID.randomUUID().toString();
	}

	public Doctor(String fullName, String specialization, 
				  String schedule, Date birthDate, String phoneNumber) {
		this.id = UUID.randomUUID().toString();
		this.specialization = specialization;
		this.schedule = schedule;
		this.fullName = fullName;
		this.createdAt = new Date().getTime();
		this.birthDate = birthDate;
		this.phoneNumber = phoneNumber;
	}

	public String getId() {
		return id;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	// Сделать возможность добавления времени работы
	public void addSchedule(String timeSlot) {
		this.schedule = timeSlot;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Long getCreatedAt() {
		return createdAt;
	}

	public void resetSchedule() {
		this.schedule = "График работы не указанн!";
	}

	@Override
	public String toString() {
		return fullName + " : " + specialization + " : " + schedule + " : " + birthDate + " : " + phoneNumber;
	}
}
