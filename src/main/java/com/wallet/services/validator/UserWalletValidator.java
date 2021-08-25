package com.wallet.services.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.wallet.models.annotation.UserWalletInsert;
import com.wallet.models.dto.UserWalletDto;

public class UserWalletValidator implements ConstraintValidator<UserWalletInsert	, UserWalletDto> {

	@Override
	public boolean isValid(UserWalletDto value, ConstraintValidatorContext context) {
	 
		
		return true;
	}
 

}
