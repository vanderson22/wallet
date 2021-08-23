package com.wallet.repositoy;

import static org.junit.Assert.assertNotNull;

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

	@Autowired
	UserRepository repository;

	@Test
	public void testSave() {
		 User u = new User();
		 u.setName("Teste");
		 u.setPassword("123456");
		 u.setEmail("teste@teste.com");
		 u.setCpf("123123123");
	
		 User response =  repository.save(u);
		
		assertNotNull(response);
	}
	
}
