package com.entrega_tech.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import com.entrega_tech.Exception.RequestException;
import com.entrega_tech.Model.Plant;
import com.entrega_tech.Model.Reading;
import com.entrega_tech.Model.Sensor;
import com.entrega_tech.Model.SensorPlant;
import com.entrega_tech.Repository.IAlertRepository;
import com.entrega_tech.Repository.IReadingRepository;
import com.entrega_tech.Repository.ISensorPlantRepository;
import com.entrega_tech.Repository.ISensorRepository;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;

@Service
public class ReadingService implements IReadingService {

	@Autowired
	IReadingRepository readRepo;

	@Autowired
	ISensorRepository sensorRepository;

	@Autowired
	ISensorPlantRepository sensorPlantRepository;

	@Autowired
	IAlertService alertService;

	@Override
	@Transactional(readOnly = true)
	public List<Reading> allReadings() {
		List<Reading> allReadings = (List<Reading>) readRepo.findAll();
		if (allReadings.isEmpty()) {
			throw new RequestException("Aún no hay lecturas cargadas en el sistema", HttpStatus.NOT_FOUND, "p-400");
		}
		return allReadings;
	}

	@Override
	@Transactional
	public void updateReadings() {
		List<Sensor> sensors = (List<Sensor>) sensorRepository.findAll();
		for (Sensor sensor : sensors) {
			List<SensorPlant> sensorPlants = sensorPlantRepository.findBySensor2(sensor);

			for (SensorPlant sensorPlant : sensorPlants) {
				Reading reading = new Reading();
				reading.setReading_value(Math.random() * 100);
				reading.setTimestamp(LocalDateTime.now());
				reading.setStatus("ACTIVE");
				reading.setSensor(sensorPlant);

				readRepo.save(reading);
				Long idSensorPlant = sensorPlant.getId();
				System.out.println("Lectura actualizada para SensorPlant ID: " + idSensorPlant);
			}
		}

		System.out.println("Lecturas actualizadas: " + LocalDateTime.now());
	}

	@Override
	public void getReadingsById(Long id) {

	}

	@Override
	@Transactional
	public void addReading(@Valid Reading reading, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			throw new ValidationException("Datos de entrada no válidos.");
		}

		Optional<SensorPlant> sensorPlantOpt = sensorPlantRepository.findById(reading.getSensor().getId());

		SensorPlant sensorPlant = sensorPlantOpt.get();
		if (!sensorPlant.isStatus()) {
			throw new RequestException("El sensor está desactivado, no puede realizar lecturas",
					HttpStatus.INTERNAL_SERVER_ERROR, "p-400");
		}

		reading.setTimestamp(LocalDateTime.now());

		System.out.println(LocalDateTime.now());
		reading.setSensor(sensorPlant);

		readRepo.save(reading);

	}

	@Override
	public void deleteReading(Long id) {

		Optional<Reading> existingRead = readRepo.findById(id);
		if (id == null) {
			throw new RequestException("ID no enviado", HttpStatus.BAD_REQUEST, "P-400");

		}
		if (!existingRead.isPresent()) {
			throw new RequestException("Lectura no encontrada", HttpStatus.NOT_FOUND, "P-400");
		}

		readRepo.deleteById(id);

	}

	@Override
	@Transactional
	public void generateRandomReading() {
		Reading reading = new Reading();
		List<SensorPlant> allSensorPlants = (List<SensorPlant>) sensorPlantRepository.findAll();

		if (allSensorPlants.isEmpty()) {
			throw new RequestException("No se encontraron sensores en la base de datos", HttpStatus.NOT_FOUND, "p-404");
		}

		Random random = new Random();
		SensorPlant randomSensor = allSensorPlants.get(random.nextInt(allSensorPlants.size()));

		if (!randomSensor.isStatus()) {
			throw new RequestException("El sensor está desactivado, no puede realizar lecturas",
					HttpStatus.INTERNAL_SERVER_ERROR, "p-400");
		}

		double normalValue = randomSensor.getSensor().getNormal_value();
		double criticalValue = randomSensor.getSensor().getCritical_value();

		double readingValue = generateRandomReading(normalValue, criticalValue);
		reading.setReading_value(readingValue);
		reading.setSensor(randomSensor);
		reading.setTimestamp(LocalDateTime.now());
		reading.setStatus("ACTIVE");
		readRepo.save(reading);
		alertService.crearAlerta(reading);
		

	}

	private double generateRandomReading(double normalValue, double criticalValue) {
		Random random = new Random();
		double readingValue = 0.0;

		readingValue = normalValue + random.nextDouble() * (criticalValue - normalValue);
		readingValue = Math.round(readingValue * 100.0) / 100.0; 
		return readingValue;
	}
}
