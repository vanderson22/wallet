package com.wallet.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Realiza a criptografia de um password
 */
public class Bcrypt {

	public static String getHash(String password) {

		return new BCryptPasswordEncoder().encode(password);
	}

}