package com.entrega_tech.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.entrega_tech.Model.Alert;
import com.entrega_tech.Model.Reading;

public interface IAlertService {

	public void crearAlerta(Reading read);
	public ResponseEntity<?> updateAlert(Long id,Alert alert);
	public List<Alert> allAlerts();
}
