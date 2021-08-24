package com.wallet.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity(name = "users")
@Data
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
 
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	@Column(nullable=false)
    private String password;
	@Column(nullable = false)
    private String name;
	@Column(nullable = false)
    private String email;
	@Column(nullable = false)
    private String cpf;
	
	
    
    
}
