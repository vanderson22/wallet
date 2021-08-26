package com.wallet.util;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class TestBcrypt {
	
	
	@Test
	public void TestGethash() {
		
		String hash = Bcrypt.getHash("senha");
		String hash2 = Bcrypt.getHash("senha");
		
		assertNotNull(hash);
		assertNotEquals(hash, hash2);
	}

}
