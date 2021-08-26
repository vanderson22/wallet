package com.wallet.repositoy;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.wallet.models.User;
import com.wallet.models.UserWallet;
import com.wallet.models.Wallet;
import com.wallet.repository.UserRepository;
import com.wallet.repository.UserWalletRepository;
import com.wallet.repository.WalletRepository;
import com.wallet.util.Bcrypt;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles({ "test" })
public class UserWalletRepositoryTest {

	private static final String CPF = "123456789-12";

	private static final String USUARIO_1 = "usuario-1 ";

	private static final String EMAIL = "User@mail.com";

	@Autowired
	WalletRepository walletRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserWalletRepository userWalletRepository;

	private User save;

	private Wallet save2;

	@Before
	public void setUp() {
		Wallet wallet = new Wallet();
		wallet.setName("carteira-1");
		wallet.setValue(new BigDecimal(100000.00032));

		User user = new User();
		user.setEmail(EMAIL);
		user.setName(USUARIO_1);
		user.setPassword(Bcrypt.getHash("SENHA"));
		user.setCpf(CPF);

		save = userRepository.save(user);
		save2 = walletRepository.save(wallet);

		System.out.println("Usuário salvo  : " + save);
		System.out.println("Carteira salva : " + save2);

	}

	@Test
	public void testCreateUserWallet() {

		UserWallet userWallet = new UserWallet();
		userWallet.setUsers(save);
		userWallet.setWallet(save2);

		UserWallet uWalletSave = userWalletRepository.save(userWallet);

		assertEquals(save.getId() , uWalletSave.getUsers().getId() ) ;
		assertEquals(save2.getId() , uWalletSave.getWallet().getId() ) ;
	}

	@After
	public void tearDown() {
		// primeiro remove o conjunto e depois o restante.
		userWalletRepository.deleteAll();
		walletRepository.deleteAll();
		userRepository.deleteAll();

	}
}
