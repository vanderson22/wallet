package com.wallet.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wallet.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
