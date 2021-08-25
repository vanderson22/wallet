package com.wallet.services;

import java.util.Optional;

import com.wallet.models.Wallet;

public interface WalletService {

	Wallet save(Wallet wallet);

	Optional<Wallet> findById(Long wallet);

}
