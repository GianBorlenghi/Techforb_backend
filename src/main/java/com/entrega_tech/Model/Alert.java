package com.entrega_tech.Model;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

@Entity
@Table(name="alerts")
public class Alert {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_alert;
	
	@Basic
	@NotBlank
	private String alert_type;
	

	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss",iso = ISO.NONE)
	@JsonFormat(shape = JsonFormat.Shape.ANY, pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime alert_at;
	
	@Basic
	@NotBlank
	private String alert_status;
	
	@ManyToOne
	@JoinColumn(name="id_sensor_plant", nullable = false)
	@JsonBackReference(value = "alert - sensor")
	private SensorPlant sensor;

	public Alert(Long id_alert, @NotBlank String alert_type, @Past @NotNull LocalDateTime alert_at,
			@NotBlank String alert_status, SensorPlant sensor) {
		super();
		this.id_alert = id_alert;
		this.alert_type = alert_type;
		this.alert_at = alert_at;
		this.alert_status = alert_status;
		this.sensor = sensor;
	}

	public Alert() {
		super();
	}

	public Long getId_alert() {
		return id_alert;
	}

	public void setId_alert(Long id_alert) {
		this.id_alert = id_alert;
	}

	public String getAlert_type() {
		return alert_type;
	}

	public void setAlert_type(String alert_type) {
		this.alert_type = alert_type;
	}

	public LocalDateTime getAlert_at() {
		return alert_at;
	}

	public void setAlert_at(LocalDateTime alert_at) {
		this.alert_at = alert_at;
	}

	public String getAlert_status() {
		return alert_status;
	}

	public void setAlert_status(String alert_status) {
		this.alert_status = alert_status;
	}

	public SensorPlant getSensor() {
		return sensor;
	}

	public void setSensor(SensorPlant sensor) {
		this.sensor = sensor;
	}

}
