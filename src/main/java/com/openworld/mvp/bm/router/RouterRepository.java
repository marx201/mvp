package com.openworld.mvp.bm.router;

import com.openworld.mvp.api.router.RouterDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface RouterRepository extends JpaRepository<RouterBE, Long> {

    @Query("select r from RouterBE r where r.macAddress = :macAddress")
    Optional<RouterBE> findByMacAddress(final String macAddress);

}
