package com.wallet.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wallet.models.UserWallet;

public interface UserWalletRepository  extends JpaRepository<UserWallet, Long>{

}
