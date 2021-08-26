package com.wallet.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestBcrypt {

	private static final String SENHA = "senha";
	@MockBean
	BCryptPasswordEncoder bp;

//	@Before
//	public void setUp() {
//		bp = new BCryptPasswordEncoder();
//	}

	@Test()
	public void TestGethash() {

		BDDMockito.when(bp.encode(Mockito.any(String.class))).thenReturn(SENHA);

		String hash = Bcrypt.getHash(SENHA, bp);
		assertEquals(SENHA, hash);
	}

}
