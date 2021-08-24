package com.wallet.repositoy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.wallet.models.Wallet;
import com.wallet.repository.WalletRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class WalletRepositoryTest {

	@Autowired
	WalletRepository repository;

	@Before
	public void setUp() {
		Wallet wallet = new Wallet();
		wallet.setName("carteira-1");
//		wallet.setValue(1000.0);
		repository.save(wallet);

	}

	@Test
	public void testCreateWallet() {
		Wallet wallet = new Wallet();
		wallet.setName("carteira-2");
//		wallet.setValue(1000.0);
		repository.save(wallet);
		  		
	}
	
	/***
	 * Deleta tudo que o repositório gerencia DEPOIS da execução do test.
	 */
	@After
	public void tearDown() {
		repository.deleteAll();
	}
}
