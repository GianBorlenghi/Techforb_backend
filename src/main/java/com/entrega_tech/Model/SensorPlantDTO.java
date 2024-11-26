package com.entrega_tech.Model;

import java.util.List;

public class SensorPlantDTO {
	private Long id_sensor_plant;
    private boolean status;
    private List<ReadingDTO> readings;
    private List<AlertDTO> alert;
	public Long getId_sensor_plant() {
		return id_sensor_plant;
	}
	public void setId_sensor_plant(Long id_sensor_plant) {
		this.id_sensor_plant = id_sensor_plant;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public List<ReadingDTO> getReadings() {
		return readings;
	}
	public void setReadings(List<ReadingDTO> readings) {
		this.readings = readings;
	}
	public List<AlertDTO> getAlert() {
		return alert;
	}
	public void setAlert(List<AlertDTO> alert) {
		this.alert = alert;
	}
    
}
