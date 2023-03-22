package com.bank.model.persistence;

import org.springframework.data.jpa.repository.JpaRepository;


import com.bank.entity.SavingsAccount;
																		
public interface SavingsAccountDao extends JpaRepository<SavingsAccount, String> {
	
	public SavingsAccount findByClientId(long ClientId);
	public SavingsAccount findByAccountNumber(int accountNumber);

	
}

