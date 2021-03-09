package com.rfb.repository;

import com.rfb.domain.RfbEvent;
import com.rfb.domain.RfbLocation;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the RfbEvent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RfbEventRepository extends JpaRepository<RfbEvent, Long> {

	RfbEvent findByRfbLocationAndEventDate(RfbLocation location, LocalDate date);
	
	RfbEvent findByEventCodeEqualsAndEventDateEqualsAndRfbLocationEquals(String eventCode, LocalDate eventDate, RfbLocation location);
	
	RfbEvent findByEventDateEqualsAndRfbLocationEquals(LocalDate eventDate, RfbLocation location);
}
