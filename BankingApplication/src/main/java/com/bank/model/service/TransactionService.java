package com.bank.model.service;

import java.util.Collection;

import com.bank.entity.Transactions;

public interface TransactionService {
	
	public void savingsAddBalance(int recievingAccountNumber, double amount);
	public void savingsWithdrawBalance(int dispatchingAccountNumber,double amount );
	public void checkingsAddBalance(int recievingAccountNumber, double amount);
	public void checkingsWithdrawBalance(int dispatchingAccountNumber, double amount);

	public Transactions logTransaction(Transactions transactions);
	
	public Collection<Transactions> getTransactionHistory(long clientId);

}
