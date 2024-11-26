package com.entrega_tech.Service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entrega_tech.Configuration.JwtService;
import com.entrega_tech.Exception.RequestException;
import com.entrega_tech.Exception.UserNotFoundException;
import com.entrega_tech.Model.AuthenticationResponse;
import com.entrega_tech.Model.LoginDTO;
import com.entrega_tech.Model.Role;
import com.entrega_tech.Model.User;
import com.entrega_tech.Repository.IUserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService{

	@Autowired
	private IUserService userServ;

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	private final JwtService jwtService = new JwtService();

	public AuthenticationResponse authenticate(LoginDTO loginDTO) {
		authManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
		LocalDateTime time = LocalDateTime.now();
		var user = userServ.findByUsername(loginDTO.getUsername());

		
		if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
			throw new BadCredentialsException("Contraseña invalida.");
		} 
		

		var jwtToken = jwtService.generateToken(user);
		user.setLast_connection(time);
		userServ.save(user);

		return new AuthenticationResponse(jwtToken, "Bearer", user.getId_user());

	}

	public AuthenticationResponse register(User user) {

		int existe = userServ.findUserByMail(user.getUsername());
		if (existe > 0) {
			throw new RequestException("El mail ya está registrado.", HttpStatus.INTERNAL_SERVER_ERROR, "500");
		} else {
			
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setLast_connection(LocalDateTime.now());
			user.setRole(Role.ROLE_USER);
			userServ.save(user);

			var jwtToken = jwtService.generateToken(user);

			return new AuthenticationResponse(jwtToken, "Bearer", user.getId_user());
		}
	}

	public String getUsername() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		}

		@SuppressWarnings("null")
		String userName = userDetails.getUsername();
		return userName;
	}
}

	
