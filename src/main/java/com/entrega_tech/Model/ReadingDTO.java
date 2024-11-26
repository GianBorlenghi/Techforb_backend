package com.entrega_tech.Model;

import java.time.LocalDateTime;

public class ReadingDTO {
    private Long id_reading;
    private double reading_value;
    private LocalDateTime timestamp;
    private String status;
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
    
    
}
