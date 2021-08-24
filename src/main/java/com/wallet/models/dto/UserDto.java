package com.wallet.models.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class UserDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotNull
	@Length(min = 3 , message = "A senha deve conter no minimo $1 caracteres") 
	private String password;
	@Length(min = 3, max = 50, message = "Nome deve conter entre 3 e 50 caracteres")
	private String name;
	@Email(message = "Email inválido")
	@NotNull
	private String email;
	@NotNull
	private String cpf;

}
