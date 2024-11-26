package com.entrega_tech.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.entrega_tech.Model.Plant;
import com.entrega_tech.Model.Reading;
import com.entrega_tech.Service.IPlantService;
import com.entrega_tech.Service.IReadingService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
@CrossOrigin(origins = "http://localhost:4200/", allowedHeaders = "*")

@RestController
@RequestMapping("/api/v1/reading")
@RequiredArgsConstructor
public class ReadingController {

	@Autowired
	IReadingService readServ;

	@PostMapping("/create")
	public void createRead(@Valid @RequestBody Reading reading, BindingResult bindingResult) {

		readServ.addReading(reading, bindingResult);

	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteRead(@PathVariable Long id) {
		readServ.deleteReading(id);
	}
	
	@GetMapping("/getAllReadings")
	public List<Reading> readingList(){
		return readServ.allReadings();
	}

	@PostMapping("/generateRandomRead")
	public ResponseEntity<String> generateRandomRead(){
		System.out.println("G");
		readServ.generateRandomReading();
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
