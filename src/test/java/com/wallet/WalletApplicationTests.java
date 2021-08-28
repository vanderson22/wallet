package com.wallet;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.wallet.util.Bcrypt;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
class TestWalletApplication {

	@Test
	void TestGetHash() {
		Bcrypt.getHash(null, null);
		
	}

}
