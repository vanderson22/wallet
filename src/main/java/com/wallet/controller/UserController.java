package com.wallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.models.User;
import com.wallet.models.dto.UserDto;
import com.wallet.models.response.Response;
import com.wallet.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
 
	@Autowired
	private UserService service;

	@PostMapping
	public ResponseEntity<Response<UserDto>> create(/*@Valid */ @RequestBody UserDto dto, BindingResult result) {
		
		  Response<UserDto> response = new Response<UserDto>();
		  
		 User s = service.save(convertDtoToUser(dto));
		 response.setData(convertUserToDto(s));
		 return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	public User convertDtoToUser(UserDto dto) {
		User u = new User();
		u.setEmail(dto.getEmail());
		u.setName(dto.getName());
		u.setPassword(dto.getPassword());
		
		return u;
	}
	public UserDto convertUserToDto(User u) {
		UserDto dto = new UserDto();
		dto.setEmail(u.getEmail());
		dto.setName(u.getName());
		dto.setPassword(u.getPassword());
		
		return dto;
	}
}
