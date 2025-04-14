package com.mycompany.hospital.services;

import com.fasterxml.jackson.databind.ObjectMapper;

class JsonService {
	private String fileName = "hospital.json";
	private ObjectMapper objectMapper;
	

	public JsonService() {
		objectMapper = new ObjectMapper();
	}

}
