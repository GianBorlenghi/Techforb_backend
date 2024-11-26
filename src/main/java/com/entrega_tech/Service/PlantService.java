package com.entrega_tech.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.entrega_tech.Controller.AdviceController;
import com.entrega_tech.Exception.RequestException;
import com.entrega_tech.Model.Alert;
import com.entrega_tech.Model.AlertDTO;
import com.entrega_tech.Model.Plant;
import com.entrega_tech.Model.PlantDTO;
import com.entrega_tech.Model.Reading;
import com.entrega_tech.Model.ReadingDTO;
import com.entrega_tech.Model.Sensor;
import com.entrega_tech.Model.SensorDTO;
import com.entrega_tech.Model.SensorPlant;
import com.entrega_tech.Model.SensorPlantDTO;
import com.entrega_tech.Repository.IPlantRepository;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;

@Service
public class PlantService implements IPlantService {


    private ReadingDTO convertToReadingDTO(Reading reading) {
        ReadingDTO dto = new ReadingDTO();
        dto.setId_reading(reading.getId_reading());
        dto.setReading_value(reading.getReading_value());
        dto.setTimestamp(reading.getTimestamp());
        dto.setStatus(reading.getStatus());
        return dto;
    }
    private AlertDTO convertToAlertDTO(Alert alert) {
        AlertDTO dto = new AlertDTO();
        dto.setId_alert(alert.getId_alert());
        dto.setAlert_type(alert.getAlert_type());
        dto.setAlert_status(alert.getAlert_status());
        dto.setAlert_at(alert.getAlert_at());
        return dto;
    }

    private SensorPlantDTO convertToSensorPlantDTO(SensorPlant sensorPlant) {
        SensorPlantDTO dto = new SensorPlantDTO();
        dto.setId_sensor_plant(sensorPlant.getId());
        dto.setStatus(sensorPlant.isStatus());
        
        List<AlertDTO> alertDTOs = new ArrayList<>();
        for (Alert alert : sensorPlant.getAlerts()) {
            alertDTOs.add(convertToAlertDTO(alert));  
        }
        dto.setAlert(alertDTOs); 

        List<ReadingDTO> readingsDTO = new ArrayList<>();
        for (Reading reading : sensorPlant.getReading()) {
            readingsDTO.add(convertToReadingDTO(reading));
        }
        dto.setReadings(readingsDTO);
        return dto;
    }

 
    public PlantDTO convertToPlantDTO(Plant plant) {
        PlantDTO dto = new PlantDTO();
        dto.setId_plant(plant.getId_plant());
        dto.setPlant_name(plant.getPlant_name()); 
        dto.setCountry(plant.getCountry());
        List<SensorPlantDTO> sensorPlantDTOs = new ArrayList<>();
        for (SensorPlant sensorPlant : plant.getSensors()) {
            sensorPlantDTOs.add(convertToSensorPlantDTO(sensorPlant));
        }
        dto.setSensors(sensorPlantDTOs);
        return dto;
    }
 
	
	@Autowired
	IPlantRepository plantRepo;
	LocalDateTime time = LocalDateTime.now();

	@Override
	@Transactional
	public void createPlant(@Valid Plant plant, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			throw new ValidationException("Datos de entrada no válidos.");
		}
		
		plant.setCreated_at(time);
		
		plantRepo.save(plant);

	}

	@Override
	@Transactional
	public ResponseEntity<?> updatePlant(Long id, String plant_name) {


		Optional<Plant> existingPlant = plantRepo.findById(id);
		if (!existingPlant.isPresent()) {
			throw new RequestException("Planta no encontrada", HttpStatus.NOT_FOUND, "P-400");
		}
		Plant plantToUpdate = existingPlant.get();
		plantToUpdate.setPlant_name(plant_name);
		
		plantRepo.save(plantToUpdate);
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<?> deletePlant(Long id) {
		Optional<Plant> existingPlant = plantRepo.findById(id);
		if (id == null) {
			throw new RequestException("ID no enviado", HttpStatus.BAD_REQUEST, "P-400");

		}
		if (!existingPlant.isPresent()) {
			throw new RequestException("Planta no encontrada", HttpStatus.NOT_FOUND, "P-400");
		}
		
		plantRepo.deleteById(id);

		return null;
	}

	@Override
	@Transactional(readOnly = true)
    public List<PlantDTO> allPlants() {
        List<Plant> plants = (List<Plant>) plantRepo.findAll();
        List<PlantDTO> plantDTOs = plants.stream()
            .map(this::convertToPlantDTO)
            .collect(Collectors.toList());
       
        return plantDTOs;
    }

	@Override
	@Transactional(readOnly=true)
	public PlantDTO getPlantById(Long id) {
        if (id == null) {
            throw new RequestException("ID no enviado", HttpStatus.BAD_REQUEST, "P-400");
        }

        Optional<Plant> plantRequest = plantRepo.findById(id);

        if (!plantRequest.isPresent()) {
            throw new RequestException("Planta no encontrada", HttpStatus.NOT_FOUND, "P-400");
        }

        return convertToPlantDTO(plantRequest.get());
    }

	
}
