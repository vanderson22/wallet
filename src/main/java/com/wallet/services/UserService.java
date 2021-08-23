package com.wallet.services;

import java.util.Optional;

import com.wallet.models.User;

public interface UserService {

	Optional<User> findByEmail(String string);

	User save(User user);

}
