package com.wallet.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.models.User;
import com.wallet.models.dto.UserDto;
import com.wallet.models.response.Response;
import com.wallet.services.UserService;
import com.wallet.util.Bcrypt;

@RestController
@RequestMapping("/user")
public class UserController {
 
	public static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService service;

	@GetMapping
	public  ResponseEntity<Response<UserDto>> findAll(){
//				service.findAll();
		return   null;
	}
	 
	@PostMapping
	public ResponseEntity<Response<UserDto>> create(@Valid  @RequestBody UserDto dto, BindingResult result) {
		
		  Response<UserDto> response = new Response<UserDto>();
		
		  if(result.hasErrors()) {
			   result
			   			.getAllErrors()
			   			.forEach( e -> { response.getErrors().add(e.getDefaultMessage()) ;    });
			   return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		  }
		 User s = service.save(convertDtoToUser(dto));
		 response.setData(convertUserToDto(s));
		 return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	public User convertDtoToUser(UserDto dto) {
		User u = new User();
		u.setEmail(dto.getEmail());
		u.setName(dto.getName());
		u.setPassword(Bcrypt.getHash(dto.getPassword()));
		u.setCpf( dto.getCpf());
		
		return u;
	}
	public UserDto convertUserToDto(User u) {
		UserDto dto = new UserDto();
		dto.setId(u.getId());
		dto.setEmail(u.getEmail());
		dto.setName(u.getName());
//		dto.setPassword(u.getPassword()); não retornar o password na resposta.
		dto.setCpf(u.getCpf());
		return dto;
	}
}
