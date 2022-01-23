package com.openworld.mvp.bm.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerBE, Long> {

    @Query("select c from CustomerBE c where c.secretKey = :secretKey")
    Optional<CustomerBE> findBySecret(final String secretKey);
}
