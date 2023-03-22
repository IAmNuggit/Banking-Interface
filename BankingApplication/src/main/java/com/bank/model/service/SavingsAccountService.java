package com.bank.model.service;

import com.bank.entity.SavingsAccount;

public interface SavingsAccountService {

	//public Boolean createSavingsAccount(String accountNumber,String sortCode,double balance,String dateOpen,String accountType,double withdrawalLimit,double annualInterest, long ClientId);
	public SavingsAccount createSavingsAccount(long ClientId);
	public SavingsAccount loginSavingsAccount(long ClientId);
	
	public SavingsAccount checkSavingsAccount(int accountNumber);
	
}
