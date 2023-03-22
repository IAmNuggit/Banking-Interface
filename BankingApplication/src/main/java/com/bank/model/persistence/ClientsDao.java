package com.bank.model.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bank.entity.Clients;
														
public interface ClientsDao extends JpaRepository<Clients, Long> {

	public Clients getClientByUserNameAndUserPassword(String userName,String userPassword);
	
	@Query("Select c from Clients c where c.userName = :userName")
	Clients findByUserName(@Param("userName") String userName);
	
	//public Boolean allNull(String userName,String userPassword,String fullName,String email,String address,String mobile);
	
	@Query("select clientId from Clients WHERE userName = :username")
	public long getCurrentClientId(@Param("username") String userName);
}

