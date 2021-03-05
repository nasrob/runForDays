package com.rfb.bootstrap;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.rfb.domain.RfbEvent;
import com.rfb.domain.RfbEventAttendance;
import com.rfb.domain.RfbLocation;
import com.rfb.domain.RfbUser;
import com.rfb.repository.AuthorityRepository;
import com.rfb.repository.RfbEventAttendanceRepository;
import com.rfb.repository.RfbEventRepository;
import com.rfb.repository.RfbLocationRepository;
import com.rfb.repository.RfbUserRepository;
import com.rfb.repository.UserRepository;

@Component
public class RfbBootstrap implements CommandLineRunner {
	
	private final RfbLocationRepository rfbLocationRepository;
	private final RfbEventRepository rfbEventRepository;
	private final RfbEventAttendanceRepository rfbEventAttendanceRepository;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthorityRepository authorityRepository;
	private final RfbUserRepository rfbUserRepository;
	
	public RfbBootstrap(RfbLocationRepository rfbLocationRepository,
						RfbEventRepository rfbEventRepository,
						RfbEventAttendanceRepository rfbEventAttendanceRepository,
						UserRepository userRepository,
						PasswordEncoder passwordEncoder,
						AuthorityRepository authorityRepository,
						RfbUserRepository rfbUserRepository) {
		
		this.rfbLocationRepository = rfbLocationRepository;
		this.rfbEventRepository = rfbEventRepository;
		this.rfbEventAttendanceRepository = rfbEventAttendanceRepository;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.authorityRepository = authorityRepository;
		this.rfbUserRepository = rfbUserRepository;
	}
	
	@Transactional
	@Override
	public void run(String... args) throws Exception {
		// init RFB Locations
		if (rfbLocationRepository.count() == 0) {
			// only load data if no data loaded
			initData();
		}
	}

	private void initData() {
		RfbUser rfbUser = new RfbUser();
		rfbUser.setUsername("John");
//		rfbUser.setPassword(passwordEncoder.encode("admin"));
//		rfbUser.setLogin("John");
//		rfbUser.setEmail("john@rfb.com");
//		rfbUser.setActivated(true);
		
		rfbUserRepository.save(rfbUser);
		
		RfbLocation aleAndWitch = getRfbLocation("St Pete - Ale and the Witch", DayOfWeek.MONDAY.getValue());
		rfbUser.setHomeLocation(aleAndWitch);
		rfbUserRepository.save(rfbUser);
		
		RfbEvent aleEvent = getRfbEvent(aleAndWitch);
		getRfbEventAttendance(rfbUser, aleEvent);
		
		RfbLocation ratc = getRfbLocation("St Pete - Right Around the corner", DayOfWeek.TUESDAY.getValue());
		RfbEvent ratcEvent = getRfbEvent(ratc);
		getRfbEventAttendance(rfbUser, ratcEvent);
		
		RfbLocation stLambertBrew = getRfbLocation("St Lambert - St Lambert Brewing", DayOfWeek.WEDNESDAY.getValue());
		RfbEvent stLambertBrewEvent = getRfbEvent(stLambertBrew);
		getRfbEventAttendance(rfbUser, stLambertBrewEvent);
		
		RfbLocation yardOfAle = getRfbLocation("Paris - Yard of Ale", DayOfWeek.THURSDAY.getValue());
		RfbEvent yardOfAleEvent = getRfbEvent(yardOfAle);
		getRfbEventAttendance(rfbUser, yardOfAleEvent);
		
	}

	private void getRfbEventAttendance(RfbUser rfbUser, RfbEvent rfbEvent) {
		RfbEventAttendance rfbAttendance = new RfbEventAttendance();
		rfbAttendance.setRfbEvent(rfbEvent);
		rfbAttendance.setRfbUser(rfbUser);
		rfbAttendance.setAttendanceDate(LocalDate.now());
		
		System.out.println(rfbAttendance.toString());
		
		rfbEventAttendanceRepository.save(rfbAttendance);
		rfbEventRepository.save(rfbEvent);	
	}

	private RfbEvent getRfbEvent(RfbLocation rfbLocation) {
		RfbEvent rfbEvent = new RfbEvent();
		rfbEvent.setEventCode(UUID.randomUUID().toString());
		rfbEvent.setEventDate(LocalDate.now());
		rfbLocation.addRfbEvent(rfbEvent);
		rfbLocationRepository.save(rfbLocation);
		rfbEventRepository.save(rfbEvent);
		return rfbEvent;
	}

	private RfbLocation getRfbLocation(String location, int value) {
		RfbLocation rfbLocation = new RfbLocation();
		rfbLocation.setLocationName(location);
		rfbLocation.setRunDayOfWeek(value);
		rfbLocationRepository.save(rfbLocation);
		return rfbLocation;
	}

}
