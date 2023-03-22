package com.bank.entity;

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
@Table(name="transactions")
public class Transactions {

	@Id
	private long transactionId;
	private String transactionReference;
	private int dispatchingAccountNumber,
	recievingAccountNumber;
	private double amount;
	private String transactionStatus;
	private long clientId;
}
