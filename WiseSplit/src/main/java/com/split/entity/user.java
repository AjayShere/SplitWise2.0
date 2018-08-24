package com.split.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Table(name ="user")
@Entity
public class user {
	

    @Id
    @GeneratedValue
	@Column(name = "Id")
	private int Id;
	
	@Column(name = "FirstName")
	private String FirstName;
	
	@Column(name = "LastName")
	private String LastName;
	
	@Column(name = "LastUpdated")
	private Date lstupdatdate;
	
	@Column(name = "IsActive")
	private BigDecimal isActive;
	
	@Column(name = "EmailId")
	private String emailId;
	
	@OneToMany(mappedBy="user" , cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	List<Account> account;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public Date getLstupdatdate() {
		return lstupdatdate;
	}

	public void setLstupdatdate(Date date) {
		this.lstupdatdate = date;
	}

	public BigDecimal getIsActive() {
		return isActive;
	}

	public void setIsActive(BigDecimal isActive) {
		this.isActive = isActive;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public List<Account> getAccount() {
		return account;
	}

	public void setAccount(List<Account> account) {
		this.account = account;
	}

	
}
