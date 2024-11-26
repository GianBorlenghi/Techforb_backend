package com.entrega_tech.Model;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

@Entity
@Table(name="sensor_readings")
//@JsonIgnoreProperties({"sensor_r"})
public class Reading {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_reading;
	
	@Basic
    @NotNull(message = "El valor de la lectura no puede ser nulo")
	private double reading_value;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss",iso = ISO.NONE)
	@JsonFormat(shape = JsonFormat.Shape.ANY, pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime timestamp;
	
	@Basic
	@NotBlank
	private String status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_sensor_plant", nullable = false)
	@JsonBackReference(value = "read - sensor")
	private SensorPlant sensor_r;
	
	
	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_sensor_plant/*id_sensor", nullable = false)
	@JsonBackReference
	private SensorPlant sensor_r;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_sensor2", nullable = false)
	@JsonBackReference
	private Sensor sensor2;*/
	

	public Reading(Long id_reading, @NotBlank double reading_value, @Past LocalDateTime timestamp,
			@NotBlank String status, SensorPlant sensor_r) {
		super();
		this.id_reading = id_reading;
		this.reading_value = reading_value;
		this.timestamp = timestamp;
		this.status = status;
		//this.sensor_r = sensor_r;
	}

	public Reading() {
		super();
	}

	public Long getId_reading() {
		return id_reading;
	}

	public void setId_reading(Long id_reading) {
		this.id_reading = id_reading;
	}

	public double getReading_value() {
		return reading_value;
	}

	public void setReading_value(double reading_value) {
		this.reading_value = reading_value;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public SensorPlant getSensor() {
		return sensor_r;
	}

	public void setSensor(SensorPlant sensor_r) {
		this.sensor_r = sensor_r;
	}	
	
	

}
