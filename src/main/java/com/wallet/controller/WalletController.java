package com.wallet.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.models.Wallet;
import com.wallet.models.dto.WalletDto;
import com.wallet.models.response.Response;
import com.wallet.services.WalletService;

@RestController
@RequestMapping("/wallets")
public class WalletController {

	public static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(WalletController.class);

	@Autowired
	private WalletService service;

	@PostMapping
	public ResponseEntity<Response<WalletDto>> create(@Valid @RequestBody WalletDto dto, BindingResult result) {

		Response<WalletDto> response = new Response<>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(e -> 
				response.getErrors().add(e.getDefaultMessage()));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		Wallet s = service.save(convertDtoToWallet(dto));
		response.setData(convertWalletToDto(s));
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	public Wallet convertDtoToWallet(WalletDto dto) {
		Wallet u = new Wallet();
		u.setId(dto.getId());
		u.setName(dto.getName());
		u.setValue(dto.getValue());
		return u;
	}

	public WalletDto convertWalletToDto(Wallet u) {
		WalletDto dto = new WalletDto();
		dto.setId(u.getId());
		dto.setName(u.getName());
		dto.setValue(u.getValue());
		return dto;
	}
}
