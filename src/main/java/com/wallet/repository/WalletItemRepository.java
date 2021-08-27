package com.wallet.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.wallet.models.WalletItem;

public interface WalletItemRepository extends JpaRepository<WalletItem, Long> {

	Optional<WalletItem> findByIdAndDate(Long vWalletItemmId, Date date);

	Optional<WalletItem> findByIdAndDateGreaterThanEqualAndDateLessThanEqual(Long vWalletItemmId, Date date,
			Date date2);

	Page<WalletItem> findByWalletIdAndDateGreaterThanEqualAndDateLessThanEqual(Long walleId, Date date, Date date2,
			Pageable pageable);

}
