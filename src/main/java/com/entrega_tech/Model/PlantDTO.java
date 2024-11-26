package com.entrega_tech.Model;

import java.util.List;

public class PlantDTO {
	 private Long id_plant;
	    private String plant_name;  
	    private List<SensorPlantDTO> sensors;  
	    private Country country;
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
		public List<SensorPlantDTO> getSensors() {
			return sensors;
		}
		public void setSensors(List<SensorPlantDTO> sensors) {
			this.sensors = sensors;
		}
		public Country getCountry() {
			return country;
		}
		public void setCountry(Country country) {
			this.country = country;
		}
	    
	    
}
