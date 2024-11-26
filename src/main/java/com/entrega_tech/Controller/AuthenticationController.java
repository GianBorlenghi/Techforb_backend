package com.entrega_tech.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entrega_tech.Service.AuthService;
import com.entrega_tech.Service.IUserService;
import com.entrega_tech.Model.*;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

	@Autowired
	private AuthService authService;
	@Autowired
	private IUserService userServ;

	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> reg(@RequestBody LoginDTO loginDTO) {

		return ResponseEntity.ok(authService.authenticate(loginDTO));

	}

	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> reg(@RequestBody User user) {

		return ResponseEntity.ok(authService.register(user));

	}

	@PostMapping("/update/{id}")
	public ResponseEntity<?> updateProfile(@PathVariable Long id, @RequestBody User user) {
	    userServ.updateProfile(id, user);
	    return ResponseEntity.ok().build();
	}
	
	@GetMapping("/findById/{id}")
	public User findUserById(@PathVariable Long id) {
		return userServ.findUserById(id);
		
	}
}