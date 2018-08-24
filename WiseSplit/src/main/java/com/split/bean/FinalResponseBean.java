package com.split.bean;

public class FinalResponseBean {
	
	
	private int amount;
	
	private int Id;

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	@Override
	public String toString() {
		return "FinalResponseBean [amount=" + amount + ", Id=" + Id + "]";
	}
}
