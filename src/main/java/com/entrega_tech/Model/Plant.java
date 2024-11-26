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
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

@Entity
@Table(name="plants")
@JsonIgnoreProperties({"plantSensors"})
public class Plant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_plant;
	
	@Basic
	@NotBlank
	private String plant_name;
	

    @OneToMany(mappedBy = "plant",cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonManagedReference(value = "sensor_plant - plant")
    private List<SensorPlant> plantSensors = new ArrayList<>();

	
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.REFRESH} )
	@JoinColumn(name="id_country", nullable = false)
	@JsonBackReference
	private Country country;
	
	@Past
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss",iso = ISO.NONE)
	@JsonFormat(shape = JsonFormat.Shape.ANY, pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime created_at;

	public Plant(Long id_plant, @NotBlank String plant_name/*, List<SensorPlant> plantSensors*/, Country country,
			@Past LocalDateTime created_at) {
		super();
		this.id_plant = id_plant;
		this.plant_name = plant_name;
		//this.plantSensors = plantSensors;
		this.country = country;
		this.created_at = created_at;
	}

	public Plant() {
		super();
	}

	public Long getId_plant() {
		return id_plant;
	}

	public void setId_plant(Long id_plant) {
		this.id_plant = id_plant;
	}

	public String getPlant_name() {
		return plant_name;
	}

	public void setPlant_name(String plant_name) {
		this.plant_name = plant_name;
	}

	public List<SensorPlant> getSensors() {
		return plantSensors;
	}

	public void setSensors(List<SensorPlant> plantSensors) {
		this.plantSensors = plantSensors;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	
	
}
