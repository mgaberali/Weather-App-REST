package com.m.weatherapp.appconfig;

import com.m.weatherapp.model.entity.AdminUser;
import com.m.weatherapp.model.entity.User;
import com.m.weatherapp.model.repository.UserRepo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class UserService implements UserDetailsService {

	private UserRepo userRepo;

	UserService(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		// select user from db
		User user = userRepo.findByUserEmail(username);
		
		if(user == null)
			throw new UsernameNotFoundException("User is not exist");
		
		// define user list of authorities
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		// set user authorities
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		if(user instanceof AdminUser)
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		
		org.springframework.security.core.userdetails.User userDetails = new org.springframework.security.core.userdetails.User(user.getUserEmail(), user.getUserPassword(), authorities);
		
		return userDetails;
	}

}
