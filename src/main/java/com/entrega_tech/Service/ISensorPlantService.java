package com.entrega_tech.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;

import com.entrega_tech.Model.SensorPlant;
import com.entrega_tech.Model.SensorReadDTO;

import jakarta.validation.Valid;

public interface ISensorPlantService {

	public void createPlantSensor(@Valid SensorPlant sensorPlant,BindingResult bindingResult);
	public void updatePlantSensor(Long id, Boolean status);
	public void deleteSensorPlant(Long id);
	public List<SensorPlant> getSensorPlant();
	public  List<SensorReadDTO> getAlertsBySensor();
}
