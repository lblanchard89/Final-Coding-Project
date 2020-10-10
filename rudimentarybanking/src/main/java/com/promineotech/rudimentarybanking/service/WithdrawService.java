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
import com.promineotech.rudimentarybanking.entites.Withdraw;
import com.promineotech.rudimentarybanking.repository.AccountRepository;
import com.promineotech.rudimentarybanking.repository.UserRepository;
import com.promineotech.rudimentarybanking.repository.WithdrawRepository;
import com.promineotech.rudimentarybanking.util.DepositStatus;
import com.promineotech.rudimentarybanking.util.WithdrawStatus;

@Service
public class WithdrawService {

	private static final Logger logger = LogManager.getLogger(WithdrawService.class);
	
	@Autowired
	private WithdrawRepository repo;
	
	@Autowired
	private AccountRepository accountRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	private Withdraw createWithdraw(Account accountId, User userId) {
		Withdraw withdraw = new Withdraw();
		withdraw.setUser(withdraw.getUser());
		withdraw.setAccount(withdraw.getAccount());
		withdraw.setId(withdraw.getId());
		withdraw.setAmount(withdraw.getAmount());
		withdraw.setDateWithdrawn(LocalDate.now());
		withdraw.setStatus(WithdrawStatus.PENDING);
		return repo.save(withdraw);
	}
	
	@Transactional
	public Withdraw completeWithdraw(Long withdrawId) throws Exception {
		try {
			Withdraw withdraw = repo.findOne(withdrawId);
			withdraw.setStatus(WithdrawStatus.WITHDRAWN);  // don't know how to remove amount withdrawn from balance
			return repo.save(withdraw);
		} catch (Exception e) {
			logger.error("Exception occured while trying to create a new withdraw: " + withdrawId, e);
			throw e;
		}
	}
	
	public Withdraw submitWithdraw(Long accountId, Long userId) throws Exception {
		try {
			Account account = accountRepo.findOne(accountId);
			User user = userRepo.findOne(userId);
			Withdraw withdraw = createWithdraw(account, user);
			return repo.save(withdraw);
		} catch (Exception e) {
			logger.error("Exception occured while trying to submit a new withdraw for account: " + accountId, e);
			throw e;
		}
	}
	
	public Withdraw cancelWithdraw(Long withdrawId) throws Exception {
		try {
			Withdraw withdraw = repo.findOne(withdrawId);
			withdraw.setStatus(WithdrawStatus.CANCELED);
			return repo.save(withdraw);
		} catch (Exception e) {
			logger.error("Exception occured when trying to cancel deposit: " + withdrawId, e);
			throw new Exception("Unable to cancel deposit");
		}
	}
}


// I need to set the an updated balance equal to he current balance minus the amount withdrawn
// I need to save the withdrawn amount, so that it can be transfered