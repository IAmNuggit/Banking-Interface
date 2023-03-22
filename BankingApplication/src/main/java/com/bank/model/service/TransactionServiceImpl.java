package com.bank.model.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bank.entity.Transactions;
import com.bank.model.persistence.TransactionsDao;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	TransactionsDao transactionsDao;

	@Override
	public void savingsWithdrawBalance(int dispatchingAccountNumber, double amount) {
		transactionsDao.savingsWithdrawBalance(dispatchingAccountNumber, amount);

	}
	
	@Override
	public void savingsAddBalance(int recievingAccountNumber, double amount) {
		transactionsDao.savingsAddBalance(recievingAccountNumber, amount);

	}
	
	@Override
	public void checkingsAddBalance(int recievingAccountNumber, double amount) {
		transactionsDao.checkingAddBalance(recievingAccountNumber, amount);
	}

	@Override
	public void checkingsWithdrawBalance(int dispatchingAccountNumber, double amount) {
		transactionsDao.checkingWithdrawBalance(dispatchingAccountNumber, amount);
	}

	@Override
	public Transactions logTransaction(Transactions transactions) {
		return transactionsDao.save(transactions);
	}

	@Override
	public Collection<Transactions> getTransactionHistory(long clientId) {
		return transactionsDao.getTransactions(clientId);
	}

}
