package com.mycompany.hospital.models;

import java.util.Date;
import java.time.LocalDateTime;
import java.util.UUID;

public class Appointment {
	private String id;
	private String patientId;
	private String doctorId;
	private Date dateTime;
	private String diagnosis;
	private String treatment;

	public Appointment() {
		this.id = UUID.randomUUID().toString();
	}

	public Appointment(String patientId, String doctorId, Date dateTime) {
		this.id = UUID.randomUUID().toString();
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.dateTime = dateTime;
	}

	public String getId() {
		return this.id;
	}

	public String getPatientId() {
		return this.patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getDoctorId() {
		return this.doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public Date getTime() {
		return this.dateTime;
	}

	public void setTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getDiagnosis() {
		return this.diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getTreatment() {
		return this.treatment;
	}

	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}

	@Override
	public String toString() {
		return String.format("Приём: ID %d (Пациент: %d, Доктор: %d, Время: %s)",
				id, patientId, doctorId, dateTime);
	}
}
