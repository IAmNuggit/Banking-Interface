package com.bank.model.persistence;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bank.entity.Transactions;
																	//	↓ Not sure correct
public interface TransactionsDao extends JpaRepository<Transactions, String> {
	
	@Modifying
	@Query("UPDATE SavingsAccount SET balance = balance - :amount WHERE accountNumber=:DAN")
	public void savingsWithdrawBalance(@Param("DAN")int dispatchingAccountNumber,@Param("amount")double amount);
	
	@Modifying
	@Query("UPDATE SavingsAccount SET balance = balance + :amount WHERE accountNumber=:RAN")
	public void savingsAddBalance(@Param("RAN")int recievingAccountNumber,@Param("amount")double amount);
	
	@Modifying
	@Query("UPDATE CheckingAccount SET balance = balance - :amount WHERE accountNumber=:DAN")
	public void checkingWithdrawBalance(@Param("DAN")int dispatchingAccountNumber,@Param("amount")double amount);
	
	@Modifying
	@Query("UPDATE CheckingAccount SET balance = balance + :amount WHERE accountNumber=:RAN")
	public void checkingAddBalance(@Param("RAN")int recievingAccountNumber,@Param("amount")double amount);
	
	@Query("FROM Transactions WHERE clientId = :clientID")
	public Collection<Transactions> getTransactions(@Param("clientID") long clientId);
}

