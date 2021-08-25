package com.wallet.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wallet.models.Wallet;
import com.wallet.repository.WalletRepository;
import com.wallet.services.WalletService;

@Service
public class WalletServiceImp implements WalletService {
	@Autowired
	WalletRepository repository;

	@Override
	public Wallet save(Wallet wallet) {

		return repository.save(wallet);
	}

	@Override
	public Optional<Wallet> findById(Long wallet) {
		return repository.findById(wallet);
	}

}
