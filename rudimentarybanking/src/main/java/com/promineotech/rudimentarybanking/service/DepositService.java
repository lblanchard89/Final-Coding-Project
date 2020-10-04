package com.promineotech.rudimentarybanking.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.rudimentarybanking.entites.Account;
import com.promineotech.rudimentarybanking.entites.Deposit;
import com.promineotech.rudimentarybanking.entites.User;
import com.promineotech.rudimentarybanking.repository.AccountRepository;
import com.promineotech.rudimentarybanking.repository.DepositRepository;
import com.promineotech.rudimentarybanking.repository.UserRepository;
import com.promineotech.rudimentarybanking.util.DepositStatus;

@Service
public class DepositService {
	
	private static final Logger logger = LogManager.getLogger(DepositService.class);
	private final int DEPOSIT_DAYS = 1;
	
	@Autowired
	private DepositRepository repo;
	
	@Autowired
	private AccountRepository accountRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	// do I need a create deposit and a submit deposit? I'm having difficulty understanding how this should work exactly.
	public Deposit submitNewDeposit(Long accountId, Long userId) throws Exception {   //<------I may need to add in the information of the deposit here(amount, check pictures)
		try {
			User user = userRepo.findOne(userId);
			Account account = accountRepo.findOne(accountId);
			Deposit deposit = initializeNewDeposit(userId, account);
			return repo.save(deposit);
		} catch (Exception e) {
			logger.error("Exception occured while trying to create a new deposit for account: " + accountId, e);
			throw e;
		}
	}
	
	//cancel deposit
	
	//complete deposit

	private Deposit initializeNewDeposit(Long userId, Account account) {
		Deposit deposit = new Deposit();
		deposit.setUser(userId);
		deposit.setAccount(account);
		// How do I set up the actual deposit info(amount, pictures of check)
		deposit.setStatus(DepositStatus.PENDING);
		return deposit;
		
		
		
	}

}