package com.wallet.models;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "wallet_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalletItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JoinColumn(referencedColumnName = "id", name = "wallet")
	@ManyToOne(fetch = FetchType.LAZY)
	private Wallet wallet;
	private Date date;
	private String type;
	private String description;
	private BigDecimal value;

}
