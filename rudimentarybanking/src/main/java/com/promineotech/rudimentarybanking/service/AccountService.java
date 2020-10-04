package com.promineotech.rudimentarybanking.service;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.rudimentarybanking.entites.Account;
import com.promineotech.rudimentarybanking.repository.AccountRepository;

@Service
public class AccountService {
	
	private static final Logger logger = LogManager.getLogger(AccountService.class);
	
	@Autowired
	private AccountRepository repo;
	
	public Iterable<Account> getAccount() {
		return repo.findAll();
	}
	
	public Account createAccount(Account account) {
		return repo.save(account);
	}
	
	public double showBalance(Long id) throws Exception {
		try {
			Account account = repo.findOne(id); 
			return account.getBalance();
			} catch (Exception e) {
				logger.error("Exception occured while trying to retrieve balance of account: " + id, e);
				throw e;
		}
	}
	
	public Account updateAccount(Account account, Long id) throws Exception {	
		try{
			Account oldAccount = repo.findOne(id);
			oldAccount.setUser(account.getUser()); // not sure how this will work when adding additional users to the account
			oldAccount.setAccountType(account.getAccountType());
			oldAccount.setAccountTier(account.getAccountTier());
			oldAccount.setBalance(account.getBalance());
			return repo.save(oldAccount);
		} catch (Exception e) {
			logger.error("Exception occured while trying to update the account with an id of: " + id, e);
			throw new Exception("Unable to update the account.");
		}
	}
	
	//find a way to add in accumulated interest
	
	@Transactional
	public void closeAccount(Long id) throws Exception {
		try {
			Account account = repo.findOne(id);
			if (account.getBalance() == 0);
			repo.delete(id); //make it so account cannot be closed unless the balance = zero
		} catch (Exception e) {
			logger.error("Exception occured while trying to close an account with the id of: " + id, e);
			throw new Exception("Unable to close the account.");
		}
	}	
}