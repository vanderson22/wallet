package com.wallet.repositoy;


import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.wallet.models.Wallet;
import com.wallet.repository.WalletRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles({ "test" })
public class WalletRepositoryTest {

	@Autowired
	WalletRepository repository;

	@BeforeAll
	public void setUp() {
		Wallet wallet = new Wallet();
		wallet.setName("carteira-1");
		wallet.setValue(new BigDecimal(100000.00032));
		Wallet save = repository.save(wallet);
		System.out.println("Após salvar : [" + save + "]");
	}

	@Test
	public void testCreateWallet() {
		Wallet wallet = new Wallet();
		wallet.setName("carteira-2");
		wallet.setValue(new BigDecimal(100000.00032));
		repository.save(wallet);

	}

	@Test
	public void testFindWalletById() {

		Optional<Wallet> walletOpt = repository.findById(1L);
		Long id = walletOpt.orElseGet(() -> new Wallet()).getId();

		assertNotNull( id) ;
	}

	/***
	 * Deleta tudo que o repositório gerencia DEPOIS da execução de TODOS os testes.
	 */
	@AfterAll
	public void tearDown() {
		repository.deleteAll();
	}
}
