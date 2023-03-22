package com.bank.model.service;

import java.util.Date;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.entity.SavingsAccount;
import com.bank.model.persistence.SavingsAccountDao;

@Service
public class SavingsAccountServiceImpl implements SavingsAccountService {

	@Autowired
	SavingsAccountDao savingsAccountDao;

	@Override
	public SavingsAccount createSavingsAccount(long ClientId) {
		Timestamp timestamp = new Timestamp(new Date().getTime());
		
		SavingsAccount savingsAccount = new SavingsAccount();
		//Auto Increment within MySQL
		savingsAccount.setAccountNumber(0);
		
		savingsAccount.setSortCode("902127");
		savingsAccount.setBalance(200);
		savingsAccount.setDateOpen(timestamp);
		savingsAccount.setAccountType("Savings");
		savingsAccount.setWithdrawalLimit(1000);
		savingsAccount.setAnnualInterest(5.50);
		savingsAccount.setClientId(ClientId);
		return savingsAccountDao.save(savingsAccount);
	}

	@Override
	public SavingsAccount loginSavingsAccount(long ClientId) {
		return savingsAccountDao.findByClientId(ClientId);
	}

	@Override
	public SavingsAccount checkSavingsAccount(int accountNumber) {
		return savingsAccountDao.findByAccountNumber(accountNumber);

	}
}
