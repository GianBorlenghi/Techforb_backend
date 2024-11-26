package com.entrega_tech.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

@Entity
@Table(name="sensors")
@JsonIgnoreProperties({"plantSensors"})
public class Sensor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_sensor;
	
	@Basic
	@NotBlank
	private String type;
	

	@Past
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss",iso = ISO.NONE)
	@JsonFormat(shape = JsonFormat.Shape.ANY, pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime last_modification;
	

    @OneToMany(mappedBy = "sensor2", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonManagedReference(value = "sensor - plant") 
    private List<SensorPlant> plantSensors = new ArrayList<>();
	
    @Basic
    @NotNull
    private double normal_value;
    
    @Basic
    @NotNull
    private double critical_value;
	




	public Sensor(Long id_sensor, @NotBlank String type, @Past LocalDateTime last_modification,
			List<SensorPlant> plantSensors, @NotBlank double normal_value, @NotBlank double critical_value) {
		super();
		this.id_sensor = id_sensor;
		this.type = type;
		this.last_modification = last_modification;
		this.plantSensors = plantSensors;
		this.normal_value = normal_value;
		this.critical_value = critical_value;
	}

	public Sensor() {
		super();
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

	public List<SensorPlant> getPlantSensors() {
		return plantSensors;
	}

	public void setPlantSensors(List<SensorPlant> plantSensors) {
		this.plantSensors = plantSensors;
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
