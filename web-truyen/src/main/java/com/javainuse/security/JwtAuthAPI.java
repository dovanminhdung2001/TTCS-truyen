package com.javainuse.security;

import com.javainuse.entity.UserEntity;
import com.javainuse.service.impl.UserSvImpl;
import com.javainuse.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.javainuse.security.jwt.JwtUserDetailsService;


import com.javainuse.security.jwt.JwtTokenUtil;
import com.javainuse.security.model.JwtRequest;
import com.javainuse.security.model.JwtResponse;
import com.javainuse.model.UserDTO;

import java.util.Date;

@RestController
@CrossOrigin
public class JwtAuthAPI {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;
	@Autowired
	UserSvImpl userSv;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		UserEntity user = userSv.findByEmail(authenticationRequest.getEmail());

		if (user != null && user.getEnable() == false) {
			if (user.getDisableTo() == null) {
				return ResponseEntity.badRequest().body("This acc is block forever");
			} else if (DateUtils.today().before(user.getDisableTo())) {
				long totalMinute = user.getDisableTo().getTime() / 60000 - DateUtils.today().getTime() / 60000;
				long minute = totalMinute % 60;
				long hour = (totalMinute / 60) % 24;
				long day = totalMinute / 24 / 60 ;
				return ResponseEntity.badRequest().body(String.format("This acc is block: %dd %dh %dm", day, hour, minute));
			}
		}

		authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getEmail());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(user.getId(), token));
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
		return ResponseEntity.ok(userDetailsService.save(user));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}