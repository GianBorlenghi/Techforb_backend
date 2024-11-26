package com.entrega_tech.Service;

import java.util.List;

import org.springframework.validation.BindingResult;

import com.entrega_tech.Model.Plant;
import com.entrega_tech.Model.Reading;

import jakarta.validation.Valid;

public interface IReadingService {

	public List<Reading> allReadings();
	
	public void updateReadings();
	
	public void getReadingsById(Long id);
	
	public void addReading(@Valid Reading reading,BindingResult bindingResult);
	
	public void deleteReading(Long id);
	
	public void generateRandomReading();
}
