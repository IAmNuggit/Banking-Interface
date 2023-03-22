package com.bank.model.service;

import com.bank.entity.CheckingAccount;

public interface CheckingAccountService {

	//public Boolean createSavingsAccount(String accountNumber,String sortCode,double balance,String dateOpen,String accountType,double withdrawalLimit,double annualInterest, long ClientId);
	public CheckingAccount createCheckingsAccount(long ClientId);
	public CheckingAccount loginCheckingsAccount(long ClientId);

	public CheckingAccount checkCheckingAccount(int accountNumber);

}
