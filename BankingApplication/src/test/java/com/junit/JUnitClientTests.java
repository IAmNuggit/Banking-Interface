package com.junit;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;

import com.bank.entity.Clients;
import com.bank.model.persistence.ClientsDao;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@ContextConfiguration
class ClientTests {
	
	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired
	private ClientsDao clientsDao;

	@Test
	public void testCreateClient() {
		Clients clients = new Clients();
		clients.setClientId(0);
		clients.setUserName("Chandler2001");
		clients.setUserPassword("Wiley");
		clients.setFullName("ChandlerHaines");
		clients.setEmail("chandlerhaines@gmail.com");
		clients.setAddress("5 Movie Star way");
		clients.setMobile("07889675897");

		Clients savedclient = clientsDao.save(clients);
		
		Clients existClient = entityManager.find(clients.getClass(), savedclient.getClientId());
		
		assertThat(clients.getEmail()).isEqualTo(existClient.getEmail());
	}

}
