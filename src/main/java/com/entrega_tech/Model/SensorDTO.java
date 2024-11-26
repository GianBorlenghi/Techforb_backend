package com.entrega_tech.Model;

import java.time.LocalDateTime;
import java.util.List;

public class SensorDTO {

	private Long id_sensor;
	private String type;
	private LocalDateTime last_modification;
	private List<Reading> readings;
	private double normal_value;
	private double critical_value;
	private Plant plant;
	private boolean status;
	
	
	
	public SensorDTO() {
		super();
	}
	public Plant getPlant() {
		return plant;
	}
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public void setPlant(Plant plant) {
		this.plant = plant;
	}
	public Long getId_sensor() {
		return id_sensor;
	}
	public void setId_sensor(Long id_sensor) {
		this.id_sensor = id_sensor;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public LocalDateTime getLast_modification() {
		return last_modification;
	}
	public void setLast_modification(LocalDateTime last_modification) {
		this.last_modification = last_modification;
	}
	public List<Reading> getReadings() {
		return readings;
	}
	public void setReadings(List<Reading> readings) {
		this.readings = readings;
	}
	public double getNormal_value() {
		return normal_value;
	}
	public void setNormal_value(double normal_value) {
		this.normal_value = normal_value;
	}
	public double getCritical_value() {
		return critical_value;
	}
	public void setCritical_value(double critical_value) {
		this.critical_value = critical_value;
	}
	
	
	
	
}
