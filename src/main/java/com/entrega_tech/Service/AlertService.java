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

	@Override
	@Transactional
	public void crearAlerta() {

		
		
		List<SensorPlant> listSensorPlant = (List<SensorPlant>) sensorPlantRepo.findAll();
		for (SensorPlant s : listSensorPlant) {
			Sensor sensor = s.getSensor();
			List<Reading> readings = s.getReading();
			if(readings == null) break;
			

			for (Reading reading : readings) {
				Double readingValue = reading.getReading_value();
				
		        if (s.isStatus() && 
		                (
		                 "ROJA".equals(reading.getStatus()) || 
		                 "MEDIA".equals(reading.getStatus()))) {
		                continue; 
		            }
				
				if (s.isStatus()) {
					Alert alert = new Alert();

				if (readingValue >= sensor.getCritical_value() && s.isStatus()) {
					if(alertExist(s,"ROJA") >3)continue;
					alert.setAlert_at(LocalDateTime.now());
					alert.setAlert_status("ENVIADA");
					alert.setAlert_type("ROJA");
					reading.setStatus("ROJA");
					alert.setSensor(s);
					alertRepo.save(alert);
				} else if (readingValue >= sensor.getNormal_value() * 0.5
						&& readingValue < sensor.getNormal_value() * 0.9 && s.isStatus()) {
					if(alertExist(s,"MEDIA") >3)continue;
					alert.setAlert_at(LocalDateTime.now());
					alert.setAlert_status("ENVIADA");
					alert.setAlert_type("MEDIA");
					reading.setStatus("MEDIA");

					alert.setSensor(s);
					alertRepo.save(alert);
				} else if (readingValue >= sensor.getNormal_value() * 0.9 && readingValue < sensor.getNormal_value()) { 
					reading.setStatus("NORMAL");

				}else if (readingValue > sensor.getNormal_value() && readingValue < sensor.getCritical_value() && s.isStatus()) {
				    if (alertExist(s, "MEDIA") > 3) continue;
				    alert.setAlert_at(LocalDateTime.now());
				    alert.setAlert_status("ENVIADA");
				    alert.setAlert_type("MEDIA");
				    reading.setStatus("MEDIA");
				    alert.setSensor(s);
				    alertRepo.save(alert);
				}
				
			}
	
				
				}
			}
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


