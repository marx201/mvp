package com.openworld.mvp.bm.router;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AliveTimeStampRepository extends JpaRepository<AliveTimeStampBE, Long> {
}
