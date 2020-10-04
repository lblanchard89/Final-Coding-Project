package com.promineotech.rudimentarybanking.entites;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.promineotech.rudimentarybanking.util.TransferStatus;

@Entity
public class Transfer {

	private String id;
	private double amount;
	private LocalDate date;
	private TransferStatus status;
	
	@JsonIgnore
	private Account account;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	@ManyToMany
	@JoinColumn(name = "fromAccountId")
	public Account getFromAccount() {
		return account;
	}
	
	public void setFromAccountId(Account account) {
		this.account = account;
	}
	
	@ManyToMany
	@JoinColumn(name = "toAccountId")
	public Account getToAccount() {
		return account;
	}
	
	public void setToAccountId(Account account) {
		this.account = account;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}

	public TransferStatus getStatus() {
		return status;
	}

	public void setStatus(TransferStatus status) {
		this.status = status;
	}
}
