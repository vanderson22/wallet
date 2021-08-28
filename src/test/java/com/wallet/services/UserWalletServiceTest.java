package com.wallet.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.wallet.models.User;
import com.wallet.models.UserWallet;
import com.wallet.models.Wallet;
import com.wallet.repository.UserRepository;
import com.wallet.repository.UserWalletRepository;
import com.wallet.repository.WalletRepository;
import com.wallet.services.impl.UserWalletServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserWalletServiceTest {

	@Autowired
	UserWalletService uwService;
	
	@Autowired
	UserWalletServiceImpl impl;

	@MockBean
	UserWalletRepository repository;

	@Autowired
	UserRepository userRepo;

	@Autowired
	WalletRepository walletRepo;

	UserWallet uWallet;

	UserWallet userWallet;

	@Before
	public void setUp() {

		User u = new User();
		u.setId(1L);
		u.setCpf("");
		u.setEmail("user@mail.com");
		u.setName("usuario-1");
		u.setPassword("pass");

		Wallet w = new Wallet();
		w.setName("carteira-1");
		w.setValue(new BigDecimal(3033));
		w.setId(1L);

		userWallet = new UserWallet();

		User user = userRepo.save(u);
		Wallet wallet = walletRepo.save(w);

		userWallet.setWallet(wallet);
		userWallet.setUsers(user);

		// usando usuario e carteira já salvos no database
		uWallet = new UserWallet();
		uWallet.setId(1L);
		uWallet.setUsers(user);
		uWallet.setWallet(wallet);
	}

	@Test
	public void TestUserWalletSave() {

		BDDMockito.when(repository.save(Mockito.any(UserWallet.class))).thenReturn(uWallet);

		UserWallet uwSaved = uwService.save(userWallet);
		System.out.println(uwSaved);

		assertNotNull(uwSaved);
		assertEquals(uWallet.getUsers().getId(), uwSaved.getUsers().getId());
		assertEquals(uWallet.getWallet().getId(), uwSaved.getWallet().getId());

	}

	@Test
	public void TestUserWalletSave2() {

		BDDMockito.when(repository.save(Mockito.any(UserWallet.class))).thenReturn(null);

		assertNull(uwService.save(userWallet));

	}
	
	@Test
	public void TestUserWalletSave3() {

		BDDMockito.when(repository.save(Mockito.any(UserWallet.class))).thenReturn(uWallet);

		assertNotNull(impl.save(userWallet));

	}

	@Test
	public void TestUserWalletSave4() {

		BDDMockito.when(repository.save(Mockito.any(null))).thenReturn(null);

		assertNull(impl.save(userWallet));

	}
	
	
}
