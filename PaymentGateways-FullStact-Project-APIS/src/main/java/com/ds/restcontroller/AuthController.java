package com.ds.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ds.dto.LoginRequest;
import com.ds.dto.UserDTO;
import com.ds.dto.UserRegisterDTO;
import com.ds.model.User;
import com.ds.service.UserServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
	
	@Autowired
	private UserServiceImpl userService;

	@PostMapping("/register") // sign up the user
	public ResponseEntity<String> registerUser(@RequestBody UserRegisterDTO dto) {
		User user=new User();
		user.setUsername(dto.getUsername());
		user.setPassword(dto.getPassword());
		user.setEmail(dto.getEmail());
		return ResponseEntity.status(201).body(userService.registerUser(user));
	}

	@PostMapping("login")//login with registred credentials
	public ResponseEntity<String> login(@RequestBody LoginRequest request){
		 String result=userService.login(request);
		 if("Login Successful....".equals(result)) {
			 return ResponseEntity.ok(result);
		 }else {
			 return ResponseEntity.status(401).body(result);
		 }
	}
	
	@PostMapping("/me") // to get current user(to know who is login in dashboard)
	public ResponseEntity<UserDTO> getCurrentUser(Authentication auth) {
		User user = userService.getUserByUserName(auth.getName());
		UserDTO dto=new UserDTO(
		user.getId(),
		user.getUsername(),
		user.getRole(),
		user.getEmail(),
		user.getCreatedAt()
		);
		
		return ResponseEntity.ok(dto);
	}
}
