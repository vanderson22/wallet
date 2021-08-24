package com.wallet.services;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

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
import com.wallet.repository.UserRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
//Chama o serviço, porém quando o serviço chama repositório,
// a resposta deste é mocado.
public class UserServiceTest {
	
	@MockBean
	UserRepository repository;
	
	@Autowired
	UserService service;

	@Before
	public void setUp() {
		BDDMockito
					.given(repository.findByEmail(Mockito.anyString()))
					.willReturn(Optional.of(new User()));

	}

	@Test
	public void testFindByEmail() {
		Optional<User> user = service.findByEmail("email@teste.com");
		assertTrue(user.isPresent());
	}

}
