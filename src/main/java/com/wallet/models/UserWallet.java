package com.wallet.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity(name ="users_wallet")
@Data
public class UserWallet  implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	
	@JoinColumn(name = "users" , referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.LAZY) //trazer apenas o id
	private User users;
	
	@JoinColumn(name = "wallet" , referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.LAZY) //trazer apenas o id
	private Wallet wallet;
}
