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
@Table(name="checkings")

//This class inherits the Account class
public class CheckingAccount{

	@Id
	private int accountNumber;
	private String sortCode;
	private double balance;
	private Timestamp dateOpen;
	private String accountType;
	
	private String cardNumber;
	private int cardPin;
	
	//Foreign key
	private long clientId;
}
