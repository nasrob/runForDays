package com.rfb.service;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.rfb.domain.RfbEvent;
import com.rfb.domain.RfbLocation;
import com.rfb.repository.RfbEventRepository;
import com.rfb.repository.RfbLocationRepository;

@Service
public class RfbEventCodeService {

	private final Logger log = LoggerFactory.getLogger(RfbEventCodeService.class);
	private final RfbLocationRepository rfbLocationRepository;
	private final RfbEventRepository rfbEventRepository;
	
	public RfbEventCodeService(RfbLocationRepository rfbLocationRepository, 
								RfbEventRepository rfbEventRepository) {
		
		this.rfbLocationRepository = rfbLocationRepository;
		this.rfbEventRepository = rfbEventRepository;
	}
	
	
	@Scheduled(cron = "0 * * * * ?") // run once per min
	// @Scheduled(cron = "0 0 * * * ?") // run once per hour, at top of hour
	public void generateRunEventCodes() {
		
		log.debug("Generating Events");
		
		List<RfbLocation> rfbLocations = rfbLocationRepository.findAllByRunDayOfWeek(LocalDate.now().getDayOfWeek().getValue());
		
		log.debug("Locations Found For Events: " + rfbLocations.size());
		
		rfbLocations.forEach(location -> {
			log.debug("Checking Events for Locations: " + location.getId());
			RfbEvent existingEvent = rfbEventRepository.findByRfbLocationAndEventDate(location, LocalDate.now());
			
			if (existingEvent == null) {
				log.debug("Event Not Found, Creating Event");
				
				// create event for day
				RfbEvent newEvent = new RfbEvent();
				newEvent.setRfbLocation(location);
				newEvent.setEventDate(LocalDate.now());
				newEvent.setEventCode(RandomStringUtils.randomAlphanumeric(10).toUpperCase());
				
				rfbEventRepository.save(newEvent);
				
				log.debug("Created Event : " + newEvent.toString());
				
			} else {
				log.debug("Event Exists for day");
			}
		});
	}
}
