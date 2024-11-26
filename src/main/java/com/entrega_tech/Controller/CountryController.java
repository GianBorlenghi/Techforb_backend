package com.entrega_tech.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entrega_tech.Exception.BusinessException;
import com.entrega_tech.Exception.RequestException;
import com.entrega_tech.Model.Country;
import com.entrega_tech.Service.ICountryService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/country")
@RequiredArgsConstructor
public class CountryController {

	@Autowired
	ICountryService countryServ;
	
    @PostMapping("/loadCountries")
	public HttpStatus loadCountries() {
        try {
        	countryServ.loadCountries();
            return HttpStatus.CREATED;
        } catch (Exception e) {
            throw new BusinessException("Paises no encontrados", "P-400", HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/getAllCountries")
	public List<Country> getAllCountries() {
    	return countryServ.getAllCountries();
    }
}
