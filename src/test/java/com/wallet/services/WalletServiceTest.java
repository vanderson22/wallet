package com.wallet.services;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.wallet.models.Wallet;
import com.wallet.repository.WalletRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class WalletServiceTest {

	private static final String CARTEIRA_1 = "carteira-1";

	@MockBean
	WalletRepository repository;

	@Autowired
	WalletService service;

	@Test
	public void testSaveWallet() {
		Wallet wallet2 = getWallet();

		Wallet wallet = new Wallet();
		wallet.setName(CARTEIRA_1);
		wallet.setValue(new BigDecimal(1553.123));
		
		BDDMockito
		        .when(repository.save(Mockito.any(Wallet.class)))
		        .thenReturn(wallet2);
	    Wallet walletSaved = service.save(wallet);
		
		assertEquals(walletSaved.getId(), wallet2.getId());
		
	}

	
	
	private Wallet getWallet() {

		Wallet wallet = new Wallet();
		wallet.setId(2L);
		wallet.setName(CARTEIRA_1);
		wallet.setValue(new BigDecimal(123.123123));

		return wallet;
	}

}
