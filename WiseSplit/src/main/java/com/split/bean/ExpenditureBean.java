package com.split.bean;

import java.util.List;

public class ExpenditureBean {
	
	private List<AccountBean> accountBean;
	
	private long perHeadAmount;
	
	List<Long> expenditure;

	public List<AccountBean> getAccountBean() {
		return accountBean;
	}

	public void setAccountBean(List<AccountBean> accountBean) {
		this.accountBean = accountBean;
	}

	public long getPerHeadAmount() {
		return perHeadAmount;
	}

	public void setPerHeadAmount(long perHeadAmount) {
		this.perHeadAmount = perHeadAmount;
	}
	

}
