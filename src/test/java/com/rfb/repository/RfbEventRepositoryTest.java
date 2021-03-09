package com.rfb.repository;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rfb.RfbloyaltyApp;
import com.rfb.bootstrap.RfbBootstrap;
import com.rfb.domain.RfbEvent;
import com.rfb.domain.RfbLocation;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RfbloyaltyApp.class})
public class RfbEventRepositoryTest extends AbstractRepositoryTest{

	@Before
	public void setUp() {
		RfbBootstrap rfbBootstrap = new RfbBootstrap(rfbLocationRepository, rfbEventRepository, rfbEventAttendanceRepository, 
													userRepository, passwordEncoder, authorityRepository, rfbUserRepository);
	}
	
	@Test
	public void findAllByRfbLocationAndEventDate() throws Exception {
		RfbLocation aleAndTheWitch = rfbLocationRepository.findByLocationName("St Pete -Ale and the Witch");
		
		assertNotNull(aleAndTheWitch);
		
		RfbEvent event = rfbEventRepository.findByRfbLocationAndEventDate(aleAndTheWitch, LocalDate.now());
		
		assertNotNull(event);
	}
}
