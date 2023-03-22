package com.bank.entity;


import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="savings")

//This class inherits the Account class
public class SavingsAccount{

	//List properties specific to a Savings account
	
	@Id
	private int accountNumber;
	private String sortCode;
	private double balance;
	private Timestamp dateOpen;
	private String accountType;
	private double withdrawalLimit;
	private double annualInterest;
	//Foreign key
	private long clientId;



}
