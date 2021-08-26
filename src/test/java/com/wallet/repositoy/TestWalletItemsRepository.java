package com.wallet.repositoy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.wallet.models.Wallet;
import com.wallet.models.WalletItem;
import com.wallet.repository.WalletItemRepository;
import com.wallet.repository.WalletRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TestWalletItemsRepository {

	private static final Date DATE = new Date();
	private static final String TYPE = "ENTRADA";
	private static final String DESCRIPTION = "Conta de Luz";
	private static final BigDecimal VALUE = BigDecimal.valueOf(54);

	@Autowired
	private WalletItemRepository respository;

	@Autowired
	private WalletRepository wRepository;

	@Test
	public void testSave() {

		Wallet w = new Wallet();
		w.setName("Carteira - 1 ");
		w.setValue(BigDecimal.valueOf(500));
		// salva wallet
		Wallet savedWallet = wRepository.save(w);

		WalletItem wi = new WalletItem(null, savedWallet, DATE, TYPE, DESCRIPTION, VALUE);
		WalletItem wiSaved = respository.save(wi);

		assertNotNull(wiSaved);
		assertEquals(DESCRIPTION, wiSaved.getDescription());
		assertEquals(VALUE, wiSaved.getValue());
		assertEquals(TYPE, wiSaved.getType());
	}
}
