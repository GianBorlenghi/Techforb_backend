package com.entrega_tech.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.entrega_tech.Exception.RequestException;
import com.entrega_tech.Model.Alert;
import com.entrega_tech.Model.Plant;
import com.entrega_tech.Model.Reading;
import com.entrega_tech.Model.Sensor;
import com.entrega_tech.Model.SensorDTO;
import com.entrega_tech.Model.SensorPlant;
import com.entrega_tech.Repository.IAlertRepository;
import com.entrega_tech.Repository.IReadingRepository;
import com.entrega_tech.Repository.ISensorPlantRepository;
import com.entrega_tech.Repository.ISensorRepository;

import jakarta.transaction.Transactional;

@Service
public class AlertService implements IAlertService{

	int alertExist(SensorPlant s,String alert_type) {
		
       return alertRepo.findByIdAndType(alert_type,s.getId());
		
	}
	
	
	@Autowired
	IAlertRepository alertRepo;
	@Autowired
	ISensorPlantRepository sensorPlantRepo;
	@Autowired
	ISensorRepository sensorRepo;
@Autowired	
IReadingRepository readingRepo;
	@Override
	@Transactional
	public void crearAlerta(Reading read) {

		
		
		List<SensorPlant> listSensorPlant = (List<SensorPlant>) sensorPlantRepo.findAll();
		for (SensorPlant s : listSensorPlant) {
			Sensor sensor = s.getSensor();
			/*List<Reading> readings = s.getReading();
			if(readings == null) break;*/
			

			//for (Reading reading : readings) {
				Double readingValue = read.getReading_value();
			    
				
				if (s.isStatus()) {
				    
				    double min_value = sensor.getNormal_value();
				    double max_value = sensor.getCritical_value();
				    
				    double rangoNormalSuperior = min_value + (max_value - min_value) * 0.5;
				    double rangoMediaSuperior = min_value + (max_value - min_value) * 0.75;
				    /*System.out.println("Valor de Normal: " + min_value);
				    System.out.println("Valor Crítico: " + max_value);
				    System.out.println("Rango Normal Superior: " + rangoNormalSuperior);
				    System.out.println("Rango Media Superior: " + rangoMediaSuperior);
				    System.out.println("Valor de Lectura Generado: " + readingValue);*/
				    
				    if (readingValue > rangoMediaSuperior) {
				    	read.setStatus("ROJA");
				        Alert alert = new Alert();
				        alert.setAlert_at(LocalDateTime.now());
				        alert.setAlert_status("ENVIADA");
				        alert.setAlert_type("ROJA");
				        alert.setSensor(s);
				        alertRepo.save(alert);
				    } else if (readingValue > rangoNormalSuperior) {
				    	read.setStatus("MEDIA");
				        Alert alert = new Alert();
				        alert.setAlert_at(LocalDateTime.now());
				        alert.setAlert_status("ENVIADA");
				        alert.setAlert_type("MEDIA");
				        alert.setSensor(s);
				        alertRepo.save(alert);
				    } else {
				        read.setStatus("NORMAL");
				    }
				     
				}
		}
			//}
	
				
			//	}
		
	    System.out.println(read.getStatus());

			}
	
		
	

	@Transactional
	@Override
	public ResponseEntity<?> updateAlert(Long id, Alert alert2) {

		Optional<Alert> alert = alertRepo.findById(id);
		if(!alert.isPresent()) {
			throw new RequestException("Alerta no encontrada", HttpStatus.NOT_FOUND, "P-400");
		}
		
		Alert alertToUpdate = alert.get();
		alertToUpdate.setAlert_status(alert2.getAlert_status());
		System.out.println(alertToUpdate);
		alertRepo.save(alertToUpdate);
		return ResponseEntity.ok(HttpStatus.OK);
	}


	@Override
	@org.springframework.transaction.annotation.Transactional(readOnly=true)
	public List<Alert> allAlerts() {


		List<Alert> allAlert = (List<Alert>) alertRepo.findAll();
		if(allAlert.isEmpty()) {
			throw new RequestException("Aún no hay alertas", HttpStatus.NOT_FOUND, "p-400");
		}
	
		return allAlert;	
		
	}
	
	}




