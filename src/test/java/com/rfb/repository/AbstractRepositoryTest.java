package com.rfb.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AbstractRepositoryTest {

	@Autowired
	RfbLocationRepository rfbLocationRepository;
	
	@Autowired
	RfbEventRepository rfbEventRepository;
	
	@Autowired
	RfbEventAttendanceRepository rfbEventAttendanceRepository;
	
	@Autowired
	RfbUserRepository rfbUserRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	AuthorityRepository authorityRepository;
	
	@Autowired
	UserRepository userRepository;
}
