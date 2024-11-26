package com.entrega_tech.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "countries")
public class Country {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_country;
	
	@Basic
	@NotBlank
	private String name;

	@Basic
	@NotBlank
	private String flag_url;
	
	@OneToMany(mappedBy = "country")
	@JsonManagedReference
    private List<Plant> plants;	



	public Country(Long id_country, @NotBlank String name, @NotBlank String flag_url, List<Plant> plants) {
		super();
		this.id_country = id_country;
		this.name = name;
		this.flag_url = flag_url;
		this.plants = plants;
	}

	public List<Plant> getPlants() {
		return plants;
	}

	public void setPlants(List<Plant> plants) {
		this.plants = plants;
	}

	public Country() {
		super();
	}

	public Long getId_country() {
		return id_country;
	}

	public void setId_country(Long id_country) {
		this.id_country = id_country;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFlag_url() {
		return flag_url;
	}

	public void setFlag_url(String flag_url) {
		this.flag_url = flag_url;
	}

	
	
}
