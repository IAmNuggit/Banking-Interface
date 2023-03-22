package com.bank.model.service;

import com.bank.entity.Clients;

public interface ClientService {

	public Clients loginClient(Clients Client);
	public Boolean addClient(Clients Client);
	public Clients findByUserName(String userName);
}
