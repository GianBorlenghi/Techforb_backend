package com.entrega_tech.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

	@Autowired
	private JwtAuthenticationFilter jwtAuthFilter;
	
	@Autowired
	private AuthenticationProvider authenticationProvider;
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		/*http.cors().and().csrf().disable();
		http.httpBasic().disable();
		http.cors();

		http
		.csrf()
		.disable()
		
		.authorizeHttpRequests()
		.anyRequest()
		.authenticated()
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authenticationProvider(authenticationProvider)
.csrf().disable()


		
		.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
*/		http.httpBasic().disable();

		http.cors();
        http
        .csrf().disable()
        .cors().and()

        .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()

        .authorizeHttpRequests()
            .requestMatchers("/api/v1/auth/login", "/api/v1/auth/register").permitAll()
            .requestMatchers("api/v1/reading/generateRandomRead").authenticated()// 
            .anyRequest().authenticated()  
            .and()

        .authenticationProvider(authenticationProvider)
		.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

		
		return http.build();
	}
}
