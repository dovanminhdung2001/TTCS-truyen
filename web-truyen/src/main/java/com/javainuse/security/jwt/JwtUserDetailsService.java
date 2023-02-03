package com.javainuse.security.jwt;

import java.util.ArrayList;

import com.javainuse.entity.ImageEntity;
import com.javainuse.repo.ImageRepo;
import com.javainuse.service.impl.UserSvImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.javainuse.entity.UserEntity;
import com.javainuse.model.dto.UserDTO;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserSvImpl userSv;

	@Autowired
	private PasswordEncoder bcryptEncoder;
	@Autowired
	ImageRepo imageRepo;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity user = userSv.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + email);
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				new ArrayList<>());
	}
	
	public UserEntity save(UserDTO user) {
		if (userSv.findByEmail(user.getEmail()) == null) {
			UserEntity newUser = new UserEntity();
			newUser.setName(user.getName());
			newUser.setEmail(user.getEmail());
			newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
			newUser.setAvatar(defaultAvt());
			return userSv.save(newUser);
		} else {
			return new UserEntity();
		}
	}

	public ImageEntity defaultAvt() {
		return imageRepo.findById(1L).get();
	}
}