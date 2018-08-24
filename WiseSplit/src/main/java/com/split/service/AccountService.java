package com.split.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.split.bean.AccountBean;
import com.split.bean.AddRequestBean;
import com.split.bean.RequestBean;


@Component
public interface AccountService {

	
	public void addAmount(AddRequestBean addRequestBean);
	
	public void getResult();
	public List<AccountBean> getAccountByDate(RequestBean requestBean);
	
}
