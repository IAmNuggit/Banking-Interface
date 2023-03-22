package com.bank.model.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.entity.CheckingAccount;
																	
public interface CheckingAccountDao extends JpaRepository<CheckingAccount, String> {

	public CheckingAccount findByClientId(long clientId);
	public CheckingAccount findByAccountNumber(int accountNumber);


}

