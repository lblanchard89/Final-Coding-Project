package com.promineotech.rudimentarybanking.entites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.promineotech.rudimentarybanking.util.AccountTier;

@Entity
public class Account {
	
	private Long id;
	private String accountType;
	private double balance;
	private AccountTier accountTier;
	
	@JsonIgnore
	private User user; // needs to be a list, change setter/getter and relationship to manytomany
	private User secondUser = new User(); //if not a list set up this way for the additional user
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name = "userId")
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	@ManyToOne
	@JoinColumn(name = "userId")
	public User getSecondUser() {
		return secondUser;
	}

	public void setSecondUser(User secondUser) {
		this.secondUser = secondUser;
	}
	

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public AccountTier getAccountTier() {
		return accountTier;
	}

	public void setAccountTier(AccountTier accountTier) {
		this.accountTier = accountTier;
	}


}
