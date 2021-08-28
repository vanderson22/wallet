package com.wallet.repositoy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

		assertNotNull(wiSaved);
		assertEquals(DESCRIPTION, wiSaved.getDescription());
		assertEquals(VALUE, wiSaved.getValue());
		assertEquals(TYPE, wiSaved.getType());
	}

	@Test
	public void testUpdate() {

		WalletItem wi = repository.findById(vWalletItemmId).get();
		wi.setDescription("Descrição atualizada");
		WalletItem wiSaved = repository.save(wi);

		WalletItem walletItemAtualizado = repository.findById(vWalletItemmId).get();

		assertEquals(walletItemAtualizado.getDescription(), wiSaved.getDescription());
	}

	@Test
	public void testRemove() {

		repository.deleteById(vWalletItemmId);
		wRepository.deleteById(walletID);

		Optional<Wallet> optWallet = wRepository.findById(walletID);
		Optional<WalletItem> optWalletItem = repository.findById(vWalletItemmId);

		assertFalse(optWallet.isPresent());
		assertFalse(optWalletItem.isPresent());
	}

	@Test
	public void testFindByIdAndDate() {

		Optional<WalletItem> findById = repository.findByIdAndDate(vWalletItemmId, DATE);

		assertTrue(findById.isPresent());

	}

	@Test
	public void testFindByIdAndDateGreaterThanEqualAndDateLessThanEqual() {

		Optional<WalletItem> findById = repository.findByIdAndDateGreaterThanEqualAndDateLessThanEqual(vWalletItemmId,
				DATE, new Date());
		assertTrue(findById.isPresent());

	}

	@Test
	public void testFindByIdAndDateGreaterThanEqualAndDateLessThanEqualPage() {

		Pageable pageable = Pageable.ofSize(10);
		Page<WalletItem> page = repository.findByWalletIdAndDateGreaterThanEqualAndDateLessThanEqual(walletID, DATE,
				new Date(), pageable);

		WalletItem walletItem = repository.findById(vWalletItemmId).get();

		assertFalse(page.getContent().isEmpty());
		assertEquals(walletItem, page.getContent().get(0));

	}

	@Test
	public void testFindByType() {
		List<WalletItem> walletItem = repository.findByWalletIdAndType(walletID, TYPE);

		assertNotNull(walletItem);
		assertEquals(walletItem.get(0).getId(), vWalletItemmId);
		assertEquals(walletItem.get(0).getId(), vWalletItemmId);

	}

	@Test
	public void testSumByWallet() {

		Wallet vWallet = wRepository.findById(walletID).get();
		WalletItem walletItem = repository.findById(vWalletItemmId).get();

		WalletItem wi = new WalletItem(null, vWallet, DATE, TYPE, DESCRIPTION, BigDecimal.valueOf(150.80));

		BigDecimal soma = repository.sumByWalletId(walletID);

		assertEquals(wi.getValue().add(walletItem.getValue()), soma);

	}

	@Test
	public void testFinByIdTypeSD() {
		Wallet vWallet = new Wallet();
		vWallet.setName("Carteira - 1 ");
		vWallet.setValue(BigDecimal.valueOf(500));
		vWallet = wRepository.save(vWallet);

		WalletItem wi = new WalletItem(null, vWallet, DATE, TypeEnum.SD, DESCRIPTION, VALUE);
		WalletItem wiSaved = repository.save(wi);

		List<WalletItem> walletItem = repository.findByWalletIdAndType(wiSaved.getWallet().getId(), TypeEnum.SD);

		assertEquals(walletItem.get(0).getType(), TypeEnum.SD);
		assertEquals(walletItem.get(0).getId(), wiSaved.getId());

	}

	@Test
	public void testFinByIdTypeEmpty() {

		List<WalletItem> walletItem = repository.findByWalletIdAndType(walletID, TypeEnum.SD);

		assertTrue(walletItem.isEmpty());

	}

	@Test(expected = RuntimeException.class)
	public void testUpdateException() {
		try {

			repository.findById(2L).get();
		} catch (Exception e) {
			throw new RuntimeException("Id não encontrado");
		}

	}

	@Test
	public void testEnum() {

		assertEquals(TypeEnum.valueOf("EN"), TYPE);
	}
}
