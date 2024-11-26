package com.entrega_tech.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entrega_tech.Model.Alert;
import com.entrega_tech.Model.Plant;
import com.entrega_tech.Repository.IAlertRepository;
import com.entrega_tech.Service.IAlertService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/alert")
@RequiredArgsConstructor
public class AlertController {
	
	@Autowired
	IAlertService alertServ;
	@PostMapping("/update/{id}")
	public ResponseEntity<String> updateAlert(
	        @PathVariable Long id,
	        @RequestBody Alert alert) {  // Espera el objeto Alert con el campo 'status'
	    try {
	        alertServ.updateAlert(id, alert);
	        return ResponseEntity.ok("Alerta actualizada exitosamente.");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body("Ha ocurrido un error inesperado: " + e.getMessage());
	    }
	}
	
	@GetMapping("/getAllAlert")
	public List<Alert> allAlerts(){
		return alertServ.allAlerts();
	}
}
