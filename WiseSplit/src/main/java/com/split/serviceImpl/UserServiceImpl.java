package com.split.serviceImpl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.split.bean.userBean;
import com.split.dao.UserDao;
import com.split.entity.user;
import com.split.service.UserService;
import com.split.util.CommonUtil;
import com.split.util.LoggerExt;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Autowired
	LoggerExt logger;

	@Override
	public String saveUserInformation(userBean userbean) {

		logger.debug("UserServiceImple", "inside UserServiceImpl");

		String status = "USER IS ALREADY PRESENT";
		user userObj = new user();

		userObj.setEmailId(userbean.getEmailId());
		userObj.setFirstName(userbean.getFirstname());

		if (null != userbean.getLastname()) {
			userObj.setLastName(userbean.getLastname());
		}
		userObj.setLstupdatdate(CommonUtil.getSystemDate());

		// boolean flag = userDao.getUsers(userbean.getEmailId());

		// if(flag == false) {
		userObj.setIsActive(BigDecimal.ONE);
		userDao.saveUserInformation(userObj);
		status = "NEW USER CREATED";
		// }
		logger.debug("UserServiceImple", "End UserServiceImpl");
		return status;

	}

	@Override
	public String updateUser(userBean userbean) {
		user user = null;
		if(null != userbean.getEmailId()) {
		user= userDao.getUsers(userbean.getEmailId());
		}
		
		if(null != user) {
		user.setFirstName(userbean.getFirstname());
		user.setLastName(userbean.getLastname());
		}
		
		userDao.updateUser(user);
		
		
		return null;
	}

	@Override
	public String deleteUser(String emailId) {
		// TODO Auto-generated method stub
		user user = null;
		if(null != emailId) {
		user= userDao.getUsers(emailId);
		}
		
		if(null != user) {
			user.setIsActive(BigDecimal.ZERO);
			userDao.updateUser(user);
		}
		return null;
	}

	@Override
	public user getUser(int userId) {
		
        
		
		
		return null;
	}

}
