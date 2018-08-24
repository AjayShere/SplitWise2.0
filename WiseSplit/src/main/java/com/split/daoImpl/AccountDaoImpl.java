package com.split.daoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.split.bean.AccountBean;
import com.split.bean.AddRequestBean;
import com.split.bean.RequestBean;
import com.split.dao.AccountDao;
import com.split.entity.Account;

@Component
public class AccountDaoImpl implements AccountDao {

	@Autowired
	EntityManager em;

	@Override
	@Transactional
	public void saveAccount(Account account) {
		
		System.out.println("in dao to save Account"  +account.getAmount());

		em.persist(account);

	}

	@Override
	public void saveAmount(AddRequestBean AddRequestBean) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public List<Account> getResult() {

		List<Account> results;
		System.out.println("in dao");

		Query query = em.createQuery("from Account where isactive = 1");
		results = query.getResultList();

		System.out.println("response" + results.get(0));
		System.out.println("dao out");

		return results;
	}

	@Override
	@Transactional
	public List<Object> getAccount() {

		List<Object> Accountresults = new ArrayList<>();
		System.out.println("in dao");
		String hqlQuery = "select sum(amount), user.Id , user.FirstName from Account where isactive = 1 group by user.Id ";

		Accountresults = em.createQuery(hqlQuery).getResultList();

		System.out.println("dao out");

		return Accountresults;
	}
	
	@Override
	@Transactional
	public List<Account> getAccounts() {

		List<Account> Accountresults = new ArrayList<>();
		System.out.println("in dao");
		String hqlQuery = "select sum(amount), user.Id , user.FirstName from Account where isactive = 1 group by user.Id ";

		Accountresults = em.createQuery(hqlQuery).getResultList();

		System.out.println("dao out");

		return Accountresults;
	}

	@Override
	@Transactional
	public List<AccountBean> getAccountByDate(RequestBean requestBean) {

		List<AccountBean> Accountresults = new ArrayList<>();

		System.out.println("in dao");
		System.out.println("from date   " + requestBean.getFromDate());
		System.out.println("to date   " + requestBean.getToDate());

		String hqlQuery = "select sum(amount) ,user.Id from Account where last_updated_date  between  :date and sysdate() group by user.Id ";

		Accountresults = em.createQuery(hqlQuery).setParameter("date", requestBean.getFromDate()).getResultList();
		
		System.out.println(Accountresults);
		System.out.println("dao out");

		return Accountresults;
	}

}
