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

    /*@Scheduled(fixedRate = 10000)
    public void scheduleReadingUpdates() {
        alertService.crearAlerta(); 
    }*/
    
}
