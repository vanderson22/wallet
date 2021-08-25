package com.wallet.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Realiza a criptografia de um password
 * está no pacote security do spring
 * uma vez setado o pacote, precisa criar uma classe de configuração de seguranã
 * SecurityConf** 
 */
public class Bcrypt {

	public static String getHash(String password) {

		return new BCryptPasswordEncoder().encode(password);
	}

}