package com.promineotech.rudimentarybanking.entites;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.promineotech.rudimentarybanking.util.AccountTier;

@Entity
public class Account {
	
	private Long id;
	private String accountType;
	private double balance;
	private AccountTier accountTier;
	
	@JsonIgnore
	private ArrayList<User> users = new ArrayList<User>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToMany
	@JoinColumn(name = "userId")
	public ArrayList<User> getUsers() {
		return users;
	}
	
	public void setUsers(ArrayList<User> user) {
		this.users = user;
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
