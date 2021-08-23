package com.wallet.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wallet.models.User;
import com.wallet.repositoy.UserRepository;
import com.wallet.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository repository;

	@Override
	public Optional<User> findByEmail(String string) {
		return repository.findByEmail(string);
	}

}
