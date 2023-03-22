package com.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.bank.entity.CheckingAccount;
import com.bank.entity.Clients;
import com.bank.entity.SavingsAccount;
import com.bank.entity.Transactions;
import com.bank.model.service.CheckingAccountService;
import com.bank.model.service.ClientService;
import com.bank.model.service.SavingsAccountService;
import com.bank.model.service.TransactionService;

@Controller
@SessionAttributes({"clients","savingsAccount","checkingsAccount","transactionHistory"})
public class ClientController {

	@Autowired
	private ClientService clientService;
	
	@Autowired
	private SavingsAccountService savingsAccountService;
	
	@Autowired
	private CheckingAccountService checkingAccountService;
	
	@Autowired
	private TransactionService transactionService;
	
	@RequestMapping("/")
	public ModelAndView getLoginPage() {
		return new ModelAndView("Login","command",new Clients());
	}
	
	@RequestMapping("/loginUser")
	public ModelAndView loginCheck(@ModelAttribute("command") Clients clients, Transactions transactions) {
		ModelAndView modelAndView=new ModelAndView();
		Clients client=clientService.loginClient(clients);
		if(client!=null) {
			SavingsAccount savingsAccount= savingsAccountService.loginSavingsAccount(client.getClientId());
			CheckingAccount checkingAccount= checkingAccountService.loginCheckingsAccount(client.getClientId());
	        List<Transactions> transactionList = (List<Transactions>) transactionService.getTransactionHistory(client.getClientId());
	        modelAndView.addObject("transactionHistory", transactionList);
			modelAndView.addObject("savingsAccount", savingsAccount);  //request Scope
			modelAndView.addObject("checkingsAccount", checkingAccount);  //request Scope
			modelAndView.addObject("clients", client);  //request Scope
			modelAndView.setViewName("Menu");
		}
		else {
			modelAndView.addObject("message", "Login Failed!");
			modelAndView.setViewName("Login");
		}
		return modelAndView;
	}
	
	@RequestMapping("/register")
	public ModelAndView getRegisterPage() {
		return new ModelAndView("Register","command",new Clients());
	}
	
	@RequestMapping("/registerUser")
	public ModelAndView registerCheck(@ModelAttribute("command") Clients clients) {
		ModelAndView modelAndView=new ModelAndView();
		String message=null;
		Clients client = clientService.findByUserName(clients.getUserName());
		if(client != null) {
			modelAndView.setViewName("Register");
			message="User Name Already Exists. Try Another User Name";
		}
		else if(clientService.addClient(clients)){
			message="Client Added, Please Login";
			modelAndView.addObject("clients", clients);  //request Scope
			modelAndView.setViewName("Login");
		}
		else {
			message="ERROR, Client Not Added";
			modelAndView.setViewName("Register");
		}
		modelAndView.addObject("message", message);
		return modelAndView;
	}
	
	@RequestMapping("/menu")
	public ModelAndView getMenuPage() {
		return new ModelAndView("Menu");
	}
	
	@RequestMapping("/savings")
	public ModelAndView getSavingsPage() {
		return new ModelAndView("Savings","command",new Clients());
	}
	@RequestMapping("/checkings")
	public ModelAndView getCheckingsPage() {
		return new ModelAndView("Checkings","command",new Clients());
	}
	@RequestMapping("/transfer")
	public ModelAndView getTransferPage() {
		return new ModelAndView("transfer","command",new Transactions());
	}
	@RequestMapping("/transferBalance")
	public ModelAndView transferBalance(@ModelAttribute("command") Transactions transactions) {
		ModelAndView modelAndView=new ModelAndView();
		String message=null;
		SavingsAccount savingsAccount = savingsAccountService.checkSavingsAccount(transactions.getDispatchingAccountNumber());
		CheckingAccount checkingAccount = checkingAccountService.checkCheckingAccount(transactions.getDispatchingAccountNumber());
		if(savingsAccount != null) {
			modelAndView.setViewName("Transfer");
			if(savingsAccount.getBalance()>= transactions.getAmount()) {
			transactionService.savingsWithdrawBalance(transactions.getDispatchingAccountNumber(), transactions.getAmount());
			transactionService.checkingsAddBalance(transactions.getRecievingAccountNumber(), transactions.getAmount());
			transactions.setClientId(savingsAccount.getClientId());
			transactions.setTransactionStatus("Success");
			transactionService.logTransaction(transactions);
			message="Transfer Success";
			}
			else
				message="Savings Account has Insufficient Balance";
		}
		else if(checkingAccount != null){
			modelAndView.setViewName("Transfer");
			if(checkingAccount.getBalance()>= transactions.getAmount()) {
			transactionService.checkingsWithdrawBalance(transactions.getDispatchingAccountNumber(), transactions.getAmount());
			transactionService.savingsAddBalance(transactions.getRecievingAccountNumber(), transactions.getAmount());
			transactions.setClientId(checkingAccount.getClientId());
			transactions.setTransactionStatus("Success");
			transactionService.logTransaction(transactions);
			message="Transfer Success";
			}
			else
				message="Checkings Account has Insufficient Balance";
		}
		else {
			message="NO ACCOUNT FOUND";
		}
		modelAndView.addObject("message", message);
		return modelAndView;
	}
}
