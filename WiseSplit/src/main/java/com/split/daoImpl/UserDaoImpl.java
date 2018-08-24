package com.split.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.split.dao.UserDao;
import com.split.entity.user;
import com.split.util.LoggerExt;

@Component
public class UserDaoImpl implements UserDao {

	@Autowired
	EntityManager em;

	@Autowired
	LoggerExt logger;

	@Override
	@Transactional
	public void saveUserInformation(user user) {

		try {
			em.persist(user);
		} catch (Exception e) {
			logger.debug("UserDaoImpl", "InsideException");
			System.out.println("Exeption in dao" + e);
		}

	}

	@Override
	public user getUsers(String email) {
		// TODO Auto-generated method stub
		user user = null;
		try {
			Query query = em.createQuery("from user where emailId = :emailId and isActive=1")
					.setParameter("emailId", email);

			List<user> userList = query.getResultList();
			
			if (null != userList.get(0)) {
				user = userList.get(0);
			}

		} catch (Exception exp) {
			logger.debug("DAO Impl Class", "Exception occured while calling getUsers");
			System.out.println("Exception occured while calling getUsers" + exp);
		}
		return user;
	}

	@Override
	@Transactional
	public String updateUser(user user) {

		em.merge(user);
		
		return null;
	}

	@Override
	@Transactional
	public user getUser(int userId) {
		int Id =userId;
		user userObj = null;
		
		userObj= em.find(user.class, Id);
		if(null != userObj) {
		return userObj;
		}
		return userObj;
		
	}

	@Override
	public List<user> getAllUsers() {

		
		List<user> userList;
		Query query =em.createQuery("from user where isActive= 1");
		
		userList =query.getResultList();
		
		return userList;
	}



}
