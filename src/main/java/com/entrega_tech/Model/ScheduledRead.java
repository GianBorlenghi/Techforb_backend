package com.entrega_tech.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.entrega_tech.Service.IAlertService;
import com.entrega_tech.Service.IReadingService;
@Component

public class ScheduledRead {

	@Autowired
    private IAlertService alertService;

    @Scheduled(fixedRate = 3000) // Cada 30 segundos
    public void scheduleReadingUpdates() {
        alertService.crearAlerta(); // Llama al servicio para actualizar lecturas
    }
    
}
