package com.rfb.repository;

import com.rfb.domain.RfbLocation;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the RfbLocation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RfbLocationRepository extends JpaRepository<RfbLocation, Long> {

	List<RfbLocation> findAllByRunDayOfWeek(int dayOfWeek);
	
	RfbLocation findByLocationName(String name);
}
