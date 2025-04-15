package com.mycompany.hospital.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.hospital.models.Appointment;
import com.mycompany.hospital.models.Doctor;
import com.mycompany.hospital.models.HospitalData;
import com.mycompany.hospital.models.Patient;

class JsonService {
	private String fileName = "hospital.json";
	private ObjectMapper objectMapper;
	

	public JsonService() {
		objectMapper = new ObjectMapper();
	}

	public HospitalData loadData() throws IOException {
		File file = new File(fileName);
		if(!file.exists()){
			return new HospitalData(new ArrayList<Patient>(), 
									new ArrayList<Doctor>(), 
									new ArrayList<Appointment>());
		}
		return objectMapper.readValue(file, HospitalData.class);
	}

	public void saveData(HospitalData data) throws IOException {
		objectMapper.writeValue(new File(fileName), data);
	}
}
