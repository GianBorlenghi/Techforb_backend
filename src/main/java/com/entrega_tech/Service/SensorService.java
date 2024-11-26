package com.entrega_tech.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.entrega_tech.Exception.RequestException;
import com.entrega_tech.Model.Plant;
import com.entrega_tech.Model.PlantDTO;
import com.entrega_tech.Model.Reading;
import com.entrega_tech.Model.ReadingDTO;
import com.entrega_tech.Model.Sensor;
import com.entrega_tech.Model.SensorDTO;
import com.entrega_tech.Model.SensorPlant;
import com.entrega_tech.Repository.ISensorRepository;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;

@Service
public class SensorService implements ISensorService{
	
	private SensorDTO converToSensorDTO(Sensor sensor) {
		SensorDTO dto = new SensorDTO();
        dto.setType(sensor.getType());
        dto.setId_sensor(sensor.getId_sensor());
        dto.setLast_modification(sensor.getLast_modification());
        dto.setNormal_value(sensor.getNormal_value());
        dto.setCritical_value(sensor.getCritical_value());
        for(SensorPlant ps : sensor.getPlantSensors()) {
        	dto.setReadings(ps.getReading());
        	dto.setPlant(ps.getPlant());
        	dto.setStatus(ps.isStatus());
        }
        return dto;
    }
	
	
	
	@Autowired
	ISensorRepository sensorRepo;
	LocalDateTime time = LocalDateTime.now();

	@Override
	@Transactional
	public void createSensor(@Valid @RequestBody Sensor sensor, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new ValidationException("Datos de entrada no válidos.");
		}
		sensor.setLast_modification(time);
		sensorRepo.save(sensor);
	}

	@Override
	@Transactional
	public ResponseEntity<?> updateSensor(Long id,@Valid Sensor sensor, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new ValidationException("Datos de entrada no válidos.");
		}

		Optional<Sensor> existingSensor = sensorRepo.findById(id);
		if (!existingSensor.isPresent()) {
			throw new RequestException("Sensor no encontrado", HttpStatus.NOT_FOUND, "P-400");
		}
		Sensor sensorToUpdate = existingSensor.get();
		sensorToUpdate.setLast_modification(time);
		sensorToUpdate.setType(sensor.getType());
		sensorToUpdate.setNormal_value(sensor.getNormal_value());
		sensorToUpdate.setCritical_value(sensor.getCritical_value());
		
		
		sensorRepo.save(sensorToUpdate);
		return ResponseEntity.ok(HttpStatus.OK);		
	}

	@Override
	@Transactional
	public ResponseEntity<?> deleteSensor(Long id) {

		Optional<Sensor> existingSensor = sensorRepo.findById(id);
		if (id == null) {
			throw new RequestException("ID no enviado", HttpStatus.BAD_REQUEST, "P-400");

		}
		if (!existingSensor.isPresent()) {
			throw new RequestException("Sensor no encontrado", HttpStatus.NOT_FOUND, "P-400");
		}

		sensorRepo.deleteById(id);

		return ResponseEntity.ok("Sensor borrado");	}

	@Override
	@Transactional(readOnly=true)
	public List<SensorDTO> allSensor() {
		
		List<Sensor> allSensors = (List<Sensor>) sensorRepo.findAll();
		if(allSensors.isEmpty()) {
			throw new RequestException("Aún no hay sensores cargados en el sistema", HttpStatus.NOT_FOUND, "p-400");
		}
		List<SensorDTO> sensorDTOs = allSensors.stream()
	            .map(this::converToSensorDTO)
	            .collect(Collectors.toList());
		return sensorDTOs;	}

	@Override
	@Transactional(readOnly=true)

	public Sensor getSensorById(Long id) {

			
		Optional<Sensor> existingSensor = sensorRepo.findById(id);
		if (id == null) {
			throw new RequestException("ID no enviado", HttpStatus.BAD_REQUEST, "P-400");

		}
		if (!existingSensor.isPresent()) {
			throw new RequestException("Sensor no encontrado", HttpStatus.NOT_FOUND, "P-400");
		}
		return existingSensor.get();
	}	

}
