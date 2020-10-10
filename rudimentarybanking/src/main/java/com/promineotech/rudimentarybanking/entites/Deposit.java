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
import com.promineotech.rudimentarybanking.util.DepositStatus;

@Entity
public class Deposit {

	private Long id;
	private double amount;
	private String checkFrontPicUrl; //do I need anything special for get/set including url?
	private String checkBackPicUrl; //same as above
	private LocalDate dateDeposited;
	private LocalDate dateAvailable;
	
	
	private DepositStatus status;
	
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

	public String getCheckFrontPicUrl() {
		return checkFrontPicUrl;
	}

	public void setCheckFrontPicUrl(String checkFrontPicUrl) {
		this.checkFrontPicUrl = checkFrontPicUrl;
	}

	public String getCheckBackPicUrl() {
		return checkBackPicUrl;
	}

	public void setCheckBackPicUrl(String checkBackPicUrl) {
		this.checkBackPicUrl = checkBackPicUrl;
	}

	public LocalDate getDateDeposited() {
		return dateDeposited;
	}

	public void setDateDeposited(LocalDate dateDeposited) {
		this.dateDeposited = dateDeposited;
	}

	public DepositStatus getStatus() {
		return status;
	}

	public void setStatus(DepositStatus status) {
		this.status = status;
	}

	public LocalDate getDateAvailable() {
		return dateAvailable;
	}

	public void setDateAvailable(LocalDate dateAvailable) {
		this.dateAvailable = dateAvailable;
	}

}
