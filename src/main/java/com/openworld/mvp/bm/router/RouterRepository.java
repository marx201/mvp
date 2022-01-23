package com.openworld.mvp.bm.router;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RouterRepository extends JpaRepository<RouterBE, Long> {

    @Query("select r from RouterBE r where r.macAddress = :macAddress")
    Optional<RouterBE> findByMacAddress(final String macAddress);
}
