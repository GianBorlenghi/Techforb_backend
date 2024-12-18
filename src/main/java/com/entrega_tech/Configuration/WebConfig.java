package com.entrega_tech.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{

	@Bean
	public WebMvcConfigurer corsConfig() {
		return new WebMvcConfigurer() {
		
			public void addCorsMappings(CorsRegistry registry) {
	
				registry.addMapping("/**")
                //.allowedOrigins("http://localhost:4200")
				.allowedOrigins("*")

                .allowedMethods(HttpMethod.GET.name(), HttpMethod.POST.name(),
                    HttpMethod.DELETE.name(), HttpMethod.PUT.name())
                .allowedHeaders("*");
				
			}
		};
	}
}
