package com.ds.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ds.model.User;

public interface IUserRepository extends JpaRepository<User, Long> {
	 Optional<User> findByUsername(String username);
	    Optional<User> findByEmail(String email);

}
