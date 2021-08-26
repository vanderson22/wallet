package com.wallet.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.models.User;
import com.wallet.models.UserWallet;
import com.wallet.models.Wallet;
import com.wallet.models.dto.UserWalletDto;
import com.wallet.models.response.Response;
import com.wallet.services.UserService;
import com.wallet.services.UserWalletService;
import com.wallet.services.WalletService;

@RestController
@RequestMapping(value = "/user_wallets")
public class UserWalletController {

	private UserWalletService service;
	private WalletService walletService;
	private UserService userService;
	
	@Autowired
	public UserWalletController( UserService userService, WalletService walletService ,  UserWalletService service ) {
       this.userService = userService;
       this.walletService = walletService;
       this.service = service;
	}

	@PostMapping
	public ResponseEntity<Response<UserWalletDto>> create(@Valid @RequestBody UserWalletDto dto, BindingResult errors) {
		Response<UserWalletDto> resp = new Response<>();
		
		if (errors.hasErrors()) {
			errors.getAllErrors().forEach(e -> resp.getErrors().add(e.getDefaultMessage()));

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
		}
		//insere após validar que tudo está ok
		UserWallet userWallet = service.save(convertDtoToEntity(dto));

	
		resp.setData(convertEntityToDto(userWallet));

		return ResponseEntity.status(HttpStatus.CREATED).body(resp);
	}

	private UserWalletDto convertEntityToDto(UserWallet userWallet) {
		    UserWalletDto dto = new UserWalletDto();
		    dto.setId(userWallet.getId());
		    dto.setUser(userWallet.getUsers().getId());
		    dto.setWallet(userWallet.getWallet().getId());
		     
		return dto;
	}

	public UserWallet convertDtoToEntity(UserWalletDto dto) {
		UserWallet userWallet = new UserWallet();
		
		Optional<User> optional = userService.findById(dto.getUser() );
		User user     =    optional.isPresent() ? optional.get() : null;
		
		Optional<Wallet> optional2 = walletService.findById(dto.getWallet() ) ;
		Wallet wallet =   optional2.isPresent() ? optional2.get() : null;
		     
		userWallet.setUsers(user);
		userWallet.setWallet(wallet);

		return userWallet;
	}

}
