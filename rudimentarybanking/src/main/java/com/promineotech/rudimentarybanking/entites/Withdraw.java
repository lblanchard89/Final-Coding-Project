package com.promineotech.rudimentarybanking.entites;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.promineotech.rudimentarybanking.util.WithdrawStatus;

@Entity
public class Withdraw {

	private Long id;
	private double amount;
	private LocalDate dateWithdrawn;
	
	private WithdrawStatus status;
	
	@JsonIgnore
	public Account account;
	public User user;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@OneToOne
	@JoinColumn(name = "userId")
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	@ManyToOne
	@JoinColumn(name = "accountId")
	public Account getAccount() {
		return account;
	}
	
	public void setAccount(Account account) {
		this.account = account;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDate getDateWithdrawn() {
		return dateWithdrawn;
	}

	public void setDateWithdrawn(LocalDate dateWithdrawn) {
		this.dateWithdrawn = dateWithdrawn;
	}

	public WithdrawStatus getStatus() {
		return status;
	}

	public void setStatus(WithdrawStatus status) {
		this.status = status;
	}
}