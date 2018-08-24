package com.split.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Account {
	
	@Id
	@GeneratedValue
	@Column(name = "AccountId")
	BigDecimal AccountId;
	
	@Column(name = "Comment")
	String comment;
	
	@Column(name = "Amount")
	long amount;
	
	@Column(name =" LastUpdatedDate")
	@Temporal(TemporalType.DATE)
	Date lastupdateddate;
	
	@Column(name = "IsActive")
	BigDecimal isactive;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name ="Id")
	user user;

	
	public BigDecimal getAccountId() {
		return AccountId;
	}

	public void setAccountId(BigDecimal accountId) {
		AccountId = accountId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public Date getLastupdateddate() {
		return lastupdateddate;
	}

	public void setLastupdateddate(Date lastupdateddate) {
		this.lastupdateddate = lastupdateddate;
	}

	public BigDecimal getIsactive() {
		return isactive;
	}

	public void setIsactive(BigDecimal isactive) {
		this.isactive = isactive;
	}

	public user getUser() {
		return user;
	}

	public void setUser(user user) {
		this.user = user;
	}
	
	
	
	

}
