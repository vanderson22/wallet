package com.wallet.services.validator;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.wallet.models.User;
import com.wallet.models.annotation.UserWalletInsert;
import com.wallet.models.dto.UserWalletDto;
import com.wallet.services.UserService;

public class UserWalletValidator implements ConstraintValidator<UserWalletInsert, UserWalletDto> {

	@Autowired
	private UserService uService;

	@Override
	public boolean isValid(UserWalletDto value, ConstraintValidatorContext context) {
		boolean isValidDto = true;

		if (value.getUser() == null || value.getWallet() == null) {
			isValidDto = false;
		} else {
			Optional<User> optional = uService.findById(value.getUser());

			if (!optional.isPresent() || !optional.get().getId().equals(value.getUser())) {
				isValidDto = false;
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(
						"Não foi possível encontrar carteira ou usuário informados").addConstraintViolation();
			}
		}

		return isValidDto;
	}

}
