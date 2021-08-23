package com.wallet.repositoy;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wallet.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

	/*Find By ? Email By cpf By ... */
	Optional<User> findByEmail(String email);

}
