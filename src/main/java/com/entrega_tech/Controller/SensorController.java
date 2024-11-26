package com.entrega_tech.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entrega_tech.Model.Sensor;
import com.entrega_tech.Model.SensorDTO;
import com.entrega_tech.Service.ISensorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", allowedHeaders = "*")

@RestController
@RequestMapping("/api/v1/sensor")
@RequiredArgsConstructor
public class SensorController {

	@Autowired
	ISensorService sensorService;
	@PostMapping("/create")
	public void createSensor(@Valid @RequestBody Sensor sensor, BindingResult bindingResult) {
	
			sensorService.createSensor(sensor, bindingResult);
	}
	

	@PostMapping("/update/{id}")
	public ResponseEntity<String> updateSensor(
			@PathVariable Long id, 
            @Valid @RequestBody Sensor sensor, 
            BindingResult bindingResult){
		 
	                sensorService.updateSensor(id, sensor, bindingResult);
	                return ResponseEntity.ok("Sensor actualizado exitosamente.");
	           
		 }
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteSensor(@PathVariable Long id){
		sensorService.deleteSensor(id);
		return ResponseEntity.ok("Sensor eliminado correctamente");
	}
	
	@GetMapping("/findAllSensors")
	public List<SensorDTO> findAllSensors(){
		
		return sensorService.allSensor();

	}
	
	@GetMapping("/findSensorById/{id}")
	public Sensor findSensorById(@PathVariable Long id){
		
		return sensorService.getSensorById(id);
	}
}
