package com.wallet.repositoy;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.wallet.models.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserRepositoryTest {

	private static final String EMAIL = "setup@teste.com";
	@Autowired
	UserRepository repository;

	@Before
	public void setUp() {
		User u = new User();
		u.setName("Set up user");
		u.setPassword("123456");
		u.setEmail(EMAIL);
		u.setCpf("123123123-12");

		repository.save(u);

	}

	/***
	 * Deleta tudo que o repositório gerencia após a execução do test.
	 */
	@After
	public void tearDown() {
		repository.deleteAll();
	}

	@Test
	public void testSave() {
		User u = new User();
		u.setName("Teste");
		u.setPassword("123456");
		u.setEmail(EMAIL);
		u.setCpf("123123123");

		User response = repository.save(u);

		assertNotNull(response);
	}

	@Test
	public void testFindByEmail() {
		Optional<User> response = repository.findByEmail(EMAIL);

		assertTrue(response.isPresent());
		assertTrue(response.get().getEmail().equals(EMAIL));
	}
}
