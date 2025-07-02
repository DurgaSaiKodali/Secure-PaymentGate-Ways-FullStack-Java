package com.ds.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ds.dto.LoginRequest;
import com.ds.exception.ResourceAlreadyExistsException;
import com.ds.exception.ResourceNotFoundException;
import com.ds.model.User;
import com.ds.repository.IUserRepository;

@Service
public class UserServiceImpl implements IUserService {

	private final IUserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;

	
	public UserServiceImpl(IUserRepository userRepository, BCryptPasswordEncoder passwordEncoder,
			AuthenticationManager authenticationManager) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
	}

	@Override
	public String registerUser(User user) {
		if (userRepository.findByUsername(user.getUsername()).isPresent()) {
			throw new ResourceAlreadyExistsException("UserName Already Exist");
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole("USER");
		userRepository.save(user);
		return "User Registered Successfully .... ";
	}

	@Override
	public User getUserByUserName(String username) {
		return userRepository.findByUsername(username)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));
	}

	@Override
	public String login(LoginRequest request) { 
		Authentication auth = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

		if (auth.isAuthenticated()) {
			return "Login successful for user: " + request.getUsername();
		} else {
			throw new ResourceNotFoundException(" Invalid username or password ");
		}
	}
}
