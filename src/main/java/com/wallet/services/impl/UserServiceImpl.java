package com.wallet.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.wallet.models.User;
import com.wallet.repository.UserRepository;
import com.wallet.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository repository;

	@Override
	public Optional<User> findByEmail(String string) {
		return repository.findByEmail(string);
	}

	@CacheEvict(value="User", allEntries=true)
	@Override
	public User save(User user) {
		return repository.save(user);
	}

	@Override
	public Optional<User> findById(Long user) {
		return repository.findById(user);
	}

	@Cacheable("User")
	@Override
	public List<User> findAll() {
		return repository.findAll();
	}

}
