package com.bank.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.entity.Clients;
import com.bank.model.persistence.ClientsDao;



@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientsDao clientsDao;
	
	@Autowired
	private SavingsAccountService savingsAccountService;
	@Autowired
	private CheckingAccountService checkingAccountService;

	@Override
	public Clients loginClient(Clients client) {
		return clientsDao.getClientByUserNameAndUserPassword(client.getUserName(), client.getUserPassword());
	}

	@Override
	public Boolean addClient(Clients client) {
		clientsDao.save(client);
		//Create Clients savings and checkings account
		savingsAccountService.createSavingsAccount(clientsDao.getCurrentClientId(client.getUserName()));
		checkingAccountService.createCheckingsAccount(clientsDao.getCurrentClientId(client.getUserName()));
		return true;
	}

	@Override
	public Clients findByUserName(String userName) {
		return clientsDao.findByUserName(userName);

	}
}
