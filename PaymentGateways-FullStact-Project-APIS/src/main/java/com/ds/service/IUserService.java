package com.ds.service;

import com.ds.dto.LoginRequest;
import com.ds.model.User;

public interface IUserService {
	String registerUser(User user);
	
	String login(LoginRequest request);
	User getUserByUserName(String username);

}
