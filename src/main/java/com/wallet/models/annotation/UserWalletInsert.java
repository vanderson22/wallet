package com.wallet.models.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.wallet.services.validator.UserWalletValidator;

// A classe que vai validar.
@Constraint(validatedBy = UserWalletValidator.class)
//Anotar o Elemento
@Target({ ElementType.TYPE })
//vai funcionar em runtime
@Retention(RetentionPolicy.RUNTIME)
public @interface UserWalletInsert {
	public String message() default "Ocorreu um erro de validação ao tentar inserir.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}

 