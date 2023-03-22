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
@Table(name="clients")
public class Clients {

	@Id
	private long clientId;
	private String userName,
	userPassword,
	fullName,
	email,
	address,
	mobile;
}
