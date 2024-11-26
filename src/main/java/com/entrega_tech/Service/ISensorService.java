package com.entrega_tech.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.entrega_tech.Model.Plant;
import com.entrega_tech.Model.Sensor;
import com.entrega_tech.Model.SensorDTO;

import jakarta.validation.Valid;

public interface ISensorService {

	public void createSensor(Sensor sensor, BindingResult bindingResult);
	
	public ResponseEntity<?> updateSensor(Long id,Sensor sensor, BindingResult bindingResult);	
	public ResponseEntity<?>  deleteSensor(Long id);
	
	public List<SensorDTO> allSensor();
	
	public Sensor getSensorById(Long id);

}
