package com.promineotech.rudimentarybanking.service;

import java.time.LocalDate;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	private final int DAYS_UNTIL_AVAILABLE = 1;
	
	@Autowired
	private DepositRepository repo;
	
	@Autowired
	private AccountRepository accountRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	
	
	public Deposit submitDeposit(Long accountId, Long userId) throws Exception {
		try {
			Account account = accountRepo.findOne(accountId);
			User user = userRepo.findOne(userId);
			Deposit deposit = createDeposit(account, user);
			return repo.save(deposit);
		} catch (Exception e) {
			logger.error("Exception occured while trying to submit a new deposit for account: " + accountId, e);
			throw e;
		}
	}
	
	@Transactional
	public Deposit completeDeposit(Long depositId) throws Exception {
		try {
			Deposit deposit = repo.findOne(depositId);
			deposit.setStatus(DepositStatus.DEPOSITED);
			return repo.save(deposit);
		} catch (Exception e) {
			logger.error("Exception occured while trying to create a new deposit: " + depositId, e);
			throw e;
		}
	}
	
	public Deposit cancelDeposit(Long depositId) throws Exception {
		try {
			Deposit deposit = repo.findOne(depositId);
			deposit.setStatus(DepositStatus.CANCELED);
			return repo.save(deposit);
		} catch (Exception e) {
			logger.error("Exception occured when trying to cancel deposit: " + depositId, e);
			throw new Exception("Unable to cancel deposit");
		}
	}
	
	private Deposit createDeposit(Account accountId, User userId) {
		Deposit deposit = new Deposit();
		deposit.setUser(deposit.getUser());
		deposit.setAccount(deposit.getAccount());
		deposit.setId(deposit.getId());
		deposit.setAmount(deposit.getAmount());
		deposit.setCheckFrontPicUrl(deposit.getCheckFrontPicUrl());
		deposit.setCheckBackPicUrl(deposit.getCheckBackPicUrl());
		deposit.setDateDeposited(LocalDate.now());
		deposit.setDateAvailable(LocalDate.now().plusDays(DAYS_UNTIL_AVAILABLE));
		deposit.setStatus(DepositStatus.PENDING);
		return repo.save(deposit);
		}

}
