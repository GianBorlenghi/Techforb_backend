package com.entrega_tech.Service;

import java.util.List;

import com.entrega_tech.Model.User;


public interface IUserService{
	
	public User findByUsername(String username);
	
	public void save(User user);
	
	public int findUserByMail(String username);
	
	public void deleteUserById(Long id);
	
	public User findUserById(Long id);
	
	public List<String> viewConnectedUsers();
	
	public void defaultAdmin(User administrator);
	
	public void updateProfile(Long id,User user);
	
}