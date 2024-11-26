package com.entrega_tech.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.entrega_tech.Model.Plant;
import com.entrega_tech.Model.PlantDTO;

import jakarta.validation.Valid;

public interface IPlantService {


	public void createPlant(@Valid Plant plant, BindingResult bindingResult);

	ResponseEntity<?> updatePlant(Long id, String plant_name);
	
	ResponseEntity<?> deletePlant(Long id);
	
	public List<PlantDTO> allPlants();
	
	public PlantDTO getPlantById(Long id);

}
