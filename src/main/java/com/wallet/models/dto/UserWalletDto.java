package com.wallet.models.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UserWalletDto  implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	@NotNull(message = "Informe o id do usuário")
	private Long user;
	@NotNull(message = "Informe o id da carteira")
    private Long wallet;
}
