package com.wallet.models;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.wallet.models.enums.TypeEnum;

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

	@NotNull
	@JoinColumn(referencedColumnName = "id", name = "wallet")
	@ManyToOne(fetch = FetchType.EAGER)
	private Wallet wallet;
	@NotNull
	private Date date;
	@NotNull
	@Enumerated(value = EnumType.STRING)
	private TypeEnum type;
	private String description;
	@NotNull
	private BigDecimal value;

}
