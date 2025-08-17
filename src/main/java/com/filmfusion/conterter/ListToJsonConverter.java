package com.filmfusion.conterter;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.AttributeConverter;

@Service
public class ListToJsonConverter implements AttributeConverter<List<String>, String> {

	private static final ObjectMapper objectmapper = new ObjectMapper();
	
	@Override
	public String convertToDatabaseColumn(List<String> list) {

		try {
			return objectmapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("error converting list to json " , e);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> convertToEntityAttribute(String json) {
		
		try {
			return objectmapper.readValue(json, List.class);
		} catch (IOException e) {
			throw new RuntimeException("Error converting JSON to list" , e);
		}
		
	}
	
	
	
}
