package com.wallet.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wallet.models.WalletItem;
import com.wallet.models.enums.TypeEnum;

public interface WalletItemRepository extends JpaRepository<WalletItem, Long> {

	Optional<WalletItem> findByIdAndDate(Long vWalletItemmId, Date date);

	Optional<WalletItem> findByIdAndDateGreaterThanEqualAndDateLessThanEqual(Long vWalletItemmId, Date date,
			Date date2);

	Page<WalletItem> findByWalletIdAndDateGreaterThanEqualAndDateLessThanEqual(Long walleId, Date date, Date date2,
			Pageable pageable);

	List<WalletItem> findByWalletIdAndType(Long vWalletItemmId, TypeEnum type);

	@Query(value = " SELECT SUM(wi.value) FROM  WalletItem wi WHERE  wi.wallet.id = :walletID")
	BigDecimal sumByWalletId(@Param(value = "walletID") Long walletID);

}
