package com.entrega_tech.Model;

public class SensorReadDTO {
	private String type;
    private int redAlerts;
    private int mediumAlerts;
    private int normalAlerts;
	public SensorReadDTO(String type, int redAlerts, int mediumAlerts, int normalAlerts) {
		super();
		this.type = type;
		this.redAlerts = redAlerts;
		this.mediumAlerts = mediumAlerts;
		this.normalAlerts = normalAlerts;
	}
	public SensorReadDTO() {
		super();
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getRedAlerts() {
		return redAlerts;
	}
	public void setRedAlerts(int redAlerts) {
		this.redAlerts = redAlerts;
	}
	public int getMediumAlerts() {
		return mediumAlerts;
	}
	public void setMediumAlerts(int mediumAlerts) {
		this.mediumAlerts = mediumAlerts;
	}
	public int getNormalAlerts() {
		return normalAlerts;
	}
	public void setNormalAlerts(int normalAlerts) {
		this.normalAlerts = normalAlerts;
	}
    
    
}
