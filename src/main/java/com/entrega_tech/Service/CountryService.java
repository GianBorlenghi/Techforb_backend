package com.entrega_tech.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.entrega_tech.Model.Country;
import com.entrega_tech.Repository.ICountryRepository;

import jakarta.transaction.Transactional;

@Service
public class CountryService implements ICountryService{

	@Autowired
	ICountryRepository countryRepo;
	@Transactional
	public void loadCountries() {
        String url = "https://restcountries.com/v2/all?fields=name,flags";
        RestTemplate restTemplate = new RestTemplate();       
        List<Map<String, Object>> countries = restTemplate.getForObject(url, List.class);

        List<Map<String, Object>> limitedCountries = countries.stream()
                .limit(50)
                .collect(Collectors.toList());
            for (Map<String, Object> countryData : limitedCountries) {
                String name = (String) countryData.get("name");
                String flagUrl = (String) ((Map) countryData.get("flags")).get("png");

                Country coun = new Country();
                coun.setFlag_url(flagUrl);
                coun.setName(name);
                countryRepo.save(coun);
            
        
        }
	}

	@Transactional
	public List<Country> getAllCountries() {
		
		return (List<Country>) countryRepo.findAll();
	}
}
	

