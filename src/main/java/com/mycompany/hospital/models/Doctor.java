package com.mycompany.hospital.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.xml.crypto.Data;

class Doctor {
	private String id;
	private String specialization;
	private List<String> schedule;
	private String name;
	private Long createdAt;

	public Doctor() {
		this.createdAt = new Date().getTime();
		this.id = UUID.randomUUID().toString();
	}

	public Doctor(String id, String specialization, List<String> schedule) {
		this.id = UUID.randomUUID().toString();
		this.specialization = specialization;
		this.schedule = schedule;
		this.schedule = new ArrayList<String>();
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public List<String> getSchedule() {
		return schedule;
	}

	public void setSchedule(List<String> schedule) {
		this.schedule = schedule;
	}

	public void addSchedule(String timeSlot) {
		this.schedule.add(timeSlot);
	}

	public void resetSchedule() {
		this.schedule = new ArrayList<String>();
	}
}
