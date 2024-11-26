package com.entrega_tech.Service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entrega_tech.Exception.UserNotFoundException;
import com.entrega_tech.Model.*;
import com.entrega_tech.Repository.*;

@Service
public class UserService implements IUserService{

	@Autowired
	private IUserRepository userRepository;

	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Override
	@Transactional(readOnly=true)
	public User findByUsername(String username) {
		
		User user = userRepository.findByUsernameUser(username);
		if (user == null) {
	        throw new UserNotFoundException("Usuario no registrado","401",HttpStatus.UNAUTHORIZED);
	    }
		return user;
	}


	@Override
	@Transactional
	public void save(User user) {

		userRepository.save(user);
		
	}


	@Override
	@Transactional(readOnly=true)
	public int findUserByMail(String username) {
		return userRepository.findByMail(username);
	}


	@Override
	@Transactional
	public void deleteUserById(Long id) {
		
		userRepository.deleteById(id);
		
	}


	@Override
	@Transactional(readOnly=true)
	public User findUserById(Long id) {
		return userRepository.findById(id).orElseThrow();
	}


	@Override
	@Transactional(readOnly=true)
	public List<String> viewConnectedUsers() {
		
		return userRepository.viewConnectedUsers();
	}


	@Override
	public void defaultAdmin(User administrator) {
	
		
		
	}


	@Override
	public void updateProfile(Long id,User user) {
		User user2 =  userRepository.findById(id).orElseThrow();
		
		user2.setCity(user.getCity());
		user2.setName(user.getName());
		user2.setPassword(passwordEncoder.encode(user.getPassword()));
		user2.setProvince(user.getProvince());
		user2.setSurname(user.getSurname());
		
		userRepository.save(user2);
		
	}
}



	


