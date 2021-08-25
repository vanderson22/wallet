package com.wallet.controller;

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
	public ResponseEntity<Response<UserWallet>> create(@Valid @RequestBody UserWalletDto dto, BindingResult errors) {

		UserWallet userWallet = service.save(convertDtoToEntity(dto));

		Response<UserWallet> resp = new Response<>();
		if (errors.hasErrors()) {
			errors.getAllErrors().forEach(e -> resp.getErrors().add(e.getDefaultMessage()));

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
		}
		
		resp.setData(userWallet);

		return ResponseEntity.status(HttpStatus.CREATED).body(resp);
	}

	private UserWallet convertDtoToEntity(UserWalletDto dto) {
		UserWallet userWallet = new UserWallet();
		User user     =  userService.findById(dto.getUser() ).get()  ;
		Wallet wallet =  walletService.findById(dto.getWallet() ).get();

		userWallet.setUsers(user);
		userWallet.setWallet(wallet);

		return userWallet;
	}

}