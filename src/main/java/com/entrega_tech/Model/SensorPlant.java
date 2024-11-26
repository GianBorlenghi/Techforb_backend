package com.entrega_tech.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
@JsonIgnoreProperties({"reading"})
@Entity
@Table(name="plant_sensor")

public class SensorPlant {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_sensor_plant;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_plant")
    @JsonBackReference(value = "sensor_plant - plant")
    private Plant plant;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_sensor")
    @JsonBackReference(value = "sensor - plant") 
    private Sensor sensor2;

    @Basic
    @NotNull
    private Boolean status;
    
	@OneToMany(mappedBy = "sensor_r" ,fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JsonManagedReference(value = "read - sensor")
	private List<Reading> reading;

	@OneToMany(mappedBy = "sensor" ,fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JsonManagedReference(value = "alert - sensor")
	private List<Alert> alerts;

	public SensorPlant(Long id_sensor_plant, Plant plant, Sensor sensor2,     @NotNull Boolean status, List<Reading> reading) {
		super();
		this.id_sensor_plant = id_sensor_plant;
		this.plant = plant;
		this.sensor2 = sensor2;
		this.status = status;
		this.reading = reading;
	}

	public SensorPlant() {
		super();
	}

	public Long getId() {
		return id_sensor_plant;
	}

	public void setId(Long id_sensor_plant) {
		this.id_sensor_plant = id_sensor_plant;
	}

	public Plant getPlant() {
		return plant;
	}

	public void setPlant(Plant plant) {
		this.plant = plant;
	}

	public Sensor getSensor() {
		return sensor2;
	}

	public void setSensor(Sensor sensor2) {
		this.sensor2 = sensor2;
	}

	public Boolean isStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public List<Reading> getReading() {
		return reading;
	}

	public void setReading(List<Reading> reading) {
		this.reading = reading;
	}

	public List<Alert> getAlerts() {
		return alerts;
	}

	public void setAlerts(List<Alert> alerts) {
		this.alerts = alerts;
	}
    
    
}
