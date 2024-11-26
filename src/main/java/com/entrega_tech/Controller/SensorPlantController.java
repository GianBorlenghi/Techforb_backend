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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entrega_tech.Model.Sensor;
import com.entrega_tech.Model.SensorPlant;
import com.entrega_tech.Model.SensorReadDTO;
import com.entrega_tech.Service.ISensorPlantService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/sensorPlant")
@RequiredArgsConstructor
public class SensorPlantController {

	@Autowired
	ISensorPlantService sensorService;
	
	@PostMapping("/create")
	public ResponseEntity<?> createSensor(@Valid @RequestBody SensorPlant sensorPlant, BindingResult bindingResult) {
	
		sensorService.createPlantSensor(sensorPlant, bindingResult);
		return ResponseEntity.ok("Creado exitosamente");
	}
	
	@PostMapping("/update/{id}")
	public ResponseEntity<String> updateSensor(
			@PathVariable Long id, 
			@RequestBody Boolean status){
		 
	                sensorService.updatePlantSensor(id, status);
	                if(status) {
	                return ResponseEntity.ok("Sensor habilitado exitosamente.");
	                }else {
		                return ResponseEntity.ok("Sensor deshabilitado exitosamente.");
	                }
		 }
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteSensorPlant(
			@PathVariable Long id){
		 
	                sensorService.deleteSensorPlant(id);
	                return ResponseEntity.ok("Relacion sensor y planta borrado exitosamente.");
	           
		 }
	
	@GetMapping("/getAll")
	public List<SensorPlant> getAllSensorPlant(){
		return sensorService.getSensorPlant();
	}
	
	@GetMapping("/getTypeReads")
	public List<SensorReadDTO> getTypeRead(){
		return sensorService.getAlertsBySensor();
	}
}
