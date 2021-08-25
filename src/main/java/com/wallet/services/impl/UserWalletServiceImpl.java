package com.wallet.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wallet.models.UserWallet;
import com.wallet.repository.UserWalletRepository;
import com.wallet.services.UserWalletService;

@Service
public class UserWalletServiceImpl implements UserWalletService {
	@Autowired
	UserWalletRepository repository;

	@Override
	public UserWallet save(UserWallet userWallet) {
		return repository.save(userWallet);
	}

}
