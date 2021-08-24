package com.wallet.security.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConf extends WebSecurityConfigurerAdapter{

	@Autowired
	private Environment env;

	
	@Override
	protected void configure(HttpSecurity auth) throws Exception {
		
		//desabilita recursos para permitir o h2 em ambiente test.
		if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
			auth.headers().frameOptions().disable();
		}

		   auth.csrf()
		       .disable()
		       			.exceptionHandling()
		       			.and()
		       			.sessionManagement()
	       					.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		       			.and()
				       .authorizeRequests()
				       	.antMatchers("**")
				       	.permitAll()
				       		.anyRequest().authenticated();
		       
	}
	
	
	
}
