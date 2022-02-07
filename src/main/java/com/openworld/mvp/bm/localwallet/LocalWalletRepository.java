package com.openworld.mvp.bm.localwallet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocalWalletRepository extends JpaRepository<LocalWalletBE, Long> {
    //    @Query("select c from CustomerBE c where c.secretKey = :secretKey")
    @Query("select c from LocalWalletBE c where router_id = :routerId")
    Optional<LocalWalletBE> findByRouterId(final Long routerId);
}
