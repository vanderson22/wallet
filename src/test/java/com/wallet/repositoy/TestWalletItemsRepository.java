package com.wallet.repositoy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.wallet.models.Wallet;
import com.wallet.models.WalletItem;
import com.wallet.models.enums.TypeEnum;
import com.wallet.repository.WalletItemRepository;
import com.wallet.repository.WalletRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TestWalletItemsRepository {

	private static final Date DATE = new Date();
	private static final TypeEnum TYPE = TypeEnum.EN;
	private static final String DESCRIPTION = "Conta de Luz";
	private static final BigDecimal VALUE = BigDecimal.valueOf(54);

	@Autowired
	private WalletItemRepository repository;

	@Autowired
	private WalletRepository wRepository;
	private Long walletID;
	private Long vWalletItemmId;

	@Before
	public void setUp() {

		Wallet vWallet = new Wallet();
		vWallet.setName("Carteira - 1 ");
		vWallet.setValue(BigDecimal.valueOf(500));
		vWallet = wRepository.save(vWallet);

		WalletItem wi = new WalletItem(null, vWallet, DATE, TYPE, DESCRIPTION, VALUE);
		WalletItem wiSaved = repository.save(wi);

		walletID = vWallet.getId();
		vWalletItemmId = wiSaved.getId();
	}

	@After
	public void tearDown() {

		repository.deleteAll();
		wRepository.deleteAll();

	}

	@Test(expected = ConstraintViolationException.class)
	public void testSaveInvalidWalletItem() {

		WalletItem wi = new WalletItem(null, null, DATE, TYPE, DESCRIPTION, VALUE);
		repository.save(wi);
	}

	@Test
	public void testSave() {

		Wallet w = new Wallet();
		w.setName("Carteira - 1 ");
		w.setValue(BigDecimal.valueOf(500));
		// salva wallet
		Wallet savedWallet = wRepository.save(w);

		WalletItem wi = new WalletItem(null, savedWallet, DATE, TYPE, DESCRIPTION, VALUE);
		WalletItem wiSaved = repository.save(wi);

		TypeEnum typeEnum = TypeEnum.valueOf("EN");
		String DESC = typeEnum.getValue();

		assertNotNull(wiSaved);
		assertEquals(DESCRIPTION, wiSaved.getDescription());
		assertEquals(VALUE, wiSaved.getValue());
		assertEquals(TYPE, wiSaved.getType());
	}

	@Test
	public void testEnum() {

		assertEquals(TypeEnum.valueOf("EN"), TYPE);
	}
}
