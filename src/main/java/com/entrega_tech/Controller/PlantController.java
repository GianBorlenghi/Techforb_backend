package com.entrega_tech.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entrega_tech.Model.Plant;
import com.entrega_tech.Model.PlantDTO;
import com.entrega_tech.Service.IPlantService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/plant")
@RequiredArgsConstructor
public class PlantController {

	@Autowired
	IPlantService plantServ;

	@PostMapping("/create")
	public void createPlant(@Valid @RequestBody Plant plant, BindingResult bindingResult) {

		plantServ.createPlant(plant, bindingResult);

	}

	@PostMapping("/update/{id}")
	public ResponseEntity<String> updatePlant(
			@PathVariable Long id, 
			@RequestBody String plant_name){
		 
	                plantServ.updatePlant(id,plant_name );
	                return ResponseEntity.ok("Planta actualizada exitosamente.");
	           
		 }

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deletePlant(@PathVariable Long id){
		plantServ.deletePlant(id);
		return ResponseEntity.ok("Planta eliminada correctamente");
	}
	
	@GetMapping("/findAllPlants")
	public List<PlantDTO> findAllPlants(){
		
		return plantServ.allPlants();

	}
	
	@GetMapping("/getPlantById/{id}")
	public PlantDTO findPlantById(@PathVariable Long id){
		
		return plantServ.getPlantById(id);
	}
}

