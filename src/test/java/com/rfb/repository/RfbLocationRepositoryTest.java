package com.rfb.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.DayOfWeek;
import java.util.List;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rfb.RfbloyaltyApp;
import com.rfb.bootstrap.RfbBootstrap;
import com.rfb.domain.RfbLocation;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RfbloyaltyApp.class})
public class RfbLocationRepositoryTest extends AbstractRepositoryTest{
	@Before
	public void setUp() {
		RfbBootstrap rfbBootstrap = new RfbBootstrap(rfbLocationRepository, rfbEventRepository, rfbEventAttendanceRepository, 
													userRepository, passwordEncoder, authorityRepository, rfbUserRepository);
	}

	public void findAllByRunDayOfWeek() {
		List<RfbLocation> mondayLocations = rfbLocationRepository.findAllByRunDayOfWeek(DayOfWeek.MONDAY.getValue());
		List<RfbLocation> tuesdayLocations = rfbLocationRepository.findAllByRunDayOfWeek(DayOfWeek.TUESDAY.getValue());
		List<RfbLocation> wedensdayLocations = rfbLocationRepository.findAllByRunDayOfWeek(DayOfWeek.WEDNESDAY.getValue());
	
		assertEquals(2, mondayLocations.size());
		assertEquals(2, tuesdayLocations.size());
		assertEquals(1, wedensdayLocations.size());
	}
}
