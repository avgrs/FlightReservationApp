package com.flightreservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flightreservation.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String emailId);

}
