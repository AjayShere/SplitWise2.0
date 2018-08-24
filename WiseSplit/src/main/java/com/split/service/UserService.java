package com.split.service;

import org.springframework.stereotype.Component;

import com.split.bean.userBean;
import com.split.entity.user;

@Component
public interface UserService {
	
	public String saveUserInformation(userBean userbean);

	
	public String updateUser(userBean userbean);
	
	public String deleteUser(String emailId);
	
	public user getUser(int userId);
}
