package com.split.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.split.bean.AccountBean;
import com.split.bean.AddRequestBean;
import com.split.bean.RequestBean;
import com.split.entity.Account;

@Component
public interface AccountDao {
	
	public void saveAccount(Account account);
	
	public void  saveAmount(AddRequestBean AddRequestBean);
	
	public List<Account> getResult();
	
	public List<Object> getAccount();

	List<AccountBean> getAccountByDate(RequestBean  requestDate);

	List<Account> getAccounts();

}
