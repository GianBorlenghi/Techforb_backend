package com.entrega_tech.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;

import com.entrega_tech.Exception.RequestException;
import com.entrega_tech.Model.Plant;
import com.entrega_tech.Model.Reading;
import com.entrega_tech.Model.SensorPlant;
import com.entrega_tech.Model.SensorReadDTO;
import com.entrega_tech.Repository.ISensorPlantRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;

@Service
public class SensorPlantService implements ISensorPlantService {

	@Autowired
	ISensorPlantRepository sensorRepo;

	@Override
	@Transactional
	public void createPlantSensor(@Valid SensorPlant sensorPlant, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			throw new ValidationException("Datos de entrada no válidos.");
		}

		sensorRepo.save(sensorPlant);

	}

	@Override
	public void updatePlantSensor(Long id, Boolean status) {

		System.out.println(status);
		if (status == null) {
			throw new ValidationException("Status no puede ser nulo.");
		}
		Optional<SensorPlant> sensorPlant = sensorRepo.findById(id);

		if (!sensorPlant.isPresent()) {
			throw new RequestException("Sensor no encontrado", HttpStatus.NOT_FOUND, "P-400");
		}

		SensorPlant sensorPlantReq = sensorPlant.get();
		sensorPlantReq.setStatus(status);
		sensorRepo.save(sensorPlantReq);

	}

	@Override
	public void deleteSensorPlant(Long id) {
		Optional<SensorPlant> existingSensorPlant = sensorRepo.findById(id);
		if (id == null) {
			throw new RequestException("ID no enviado", HttpStatus.BAD_REQUEST, "P-400");

		}
		if (!existingSensorPlant.isPresent()) {
			throw new RequestException("Planta no encontrada", HttpStatus.NOT_FOUND, "P-400");
		}

		sensorRepo.deleteById(id);

	}

	@Override
	public List<SensorPlant> getSensorPlant() {

		return (List<SensorPlant>) sensorRepo.findAll();
	}

	@Override
	public List<SensorReadDTO> getAlertsBySensor() {
	    List<SensorPlant> sp = (List<SensorPlant>) sensorRepo.findAll();
	    List<SensorReadDTO> sReadDTO = new ArrayList<>();

	    for (SensorPlant s : sp) {
	        int redRead = 0;
	        int mediumRead = 0;
	        int normalRead = 0;

	        for (Reading r : s.getReading()) {
	            switch (r.getStatus().toUpperCase()) {
	                case "NORMAL":
	                    normalRead++;
	                    break;
	                case "ROJA":
	                    redRead++;
	                    break;
	                case "MEDIA":
	                    mediumRead++;
	                    break;
	            }
	        }

	        String sensorType = s.getSensor().getType();

	        SensorReadDTO existingDTO = sReadDTO.stream()
	            .filter(dto -> dto.getType().equals(sensorType))
	            .findFirst()
	            .orElse(null);

	        if (existingDTO != null) {
	            existingDTO.setRedAlerts(existingDTO.getRedAlerts() + redRead);
	            existingDTO.setMediumAlerts(existingDTO.getMediumAlerts() + mediumRead);
	            existingDTO.setNormalAlerts(existingDTO.getNormalAlerts() + normalRead);
	        } else {
	            sReadDTO.add(new SensorReadDTO(sensorType, redRead, mediumRead, normalRead));
	        }
	    }
	    return sReadDTO;

	}

}
