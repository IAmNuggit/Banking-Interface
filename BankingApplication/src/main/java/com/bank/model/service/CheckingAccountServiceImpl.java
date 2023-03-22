package com.bank.model.service;

import java.util.Date;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.entity.CheckingAccount;
import com.bank.model.persistence.CheckingAccountDao;

@Service
public class CheckingAccountServiceImpl implements CheckingAccountService {

	@Autowired
	CheckingAccountDao checkingAccountDao;

	@Override
	public CheckingAccount createCheckingsAccount(long ClientId) {
		Timestamp timestamp = new Timestamp(new Date().getTime());
		
		CheckingAccount checkingAccount = new CheckingAccount();
		//Auto Increment within MySQL
		checkingAccount.setAccountNumber(0);
		
		checkingAccount.setSortCode("902127");
		checkingAccount.setBalance(200);
		checkingAccount.setDateOpen(timestamp);
		checkingAccount.setAccountType("Checking");
		checkingAccount.setCardNumber("5643872864729891");
		checkingAccount.setCardPin(1234);
		checkingAccount.setClientId(ClientId);
		return checkingAccountDao.save(checkingAccount);
	}

	@Override
	public CheckingAccount loginCheckingsAccount(long ClientId) {
		return checkingAccountDao.findByClientId(ClientId);

	}

	@Override
	public CheckingAccount checkCheckingAccount(int accountNumber) {
		return checkingAccountDao.findByAccountNumber(accountNumber);

	}
}
