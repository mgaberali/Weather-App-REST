package com.m.weatherapp.model.repository;

import com.m.weatherapp.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String>{
	
	User findByUserEmail(String userEmail);
	User findBySessionId(String sessionId);
}
