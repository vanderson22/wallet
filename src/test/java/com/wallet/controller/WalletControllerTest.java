package com.wallet.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wallet.models.User;
import com.wallet.models.UserWallet;
import com.wallet.models.Wallet;
import com.wallet.models.dto.UserWalletDto;
import com.wallet.services.UserService;
import com.wallet.services.UserWalletService;
import com.wallet.services.WalletService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class WalletControllerTest {

	 @MockBean
	 UserWalletService service;
	 @MockBean
	 WalletService wService;
	 @MockBean
	 UserService uService;
	 
	 @Autowired
	 MockMvc mvc;  
	 
	  
	 
	 @Test
	 public void TestSaveUserWallet() throws JsonProcessingException, Exception {
		  BDDMockito
	         .given(uService.findById(Mockito.any(Long.class)))
	         .willReturn( Optional.of( getMockUser() ));
			   
			 BDDMockito
	         .given(wService.findById(Mockito.any(Long.class)))
	         .willReturn(Optional.of( getMockWallet() ));
			 
			 
			 BDDMockito
	         .given(service.save(Mockito.any(UserWallet.class)))
	         .willReturn(getMockUserWallet());
			 
			 
		  mvc.perform( MockMvcRequestBuilders
				  					.post("/user_wallets")
				  					.content(getJsonPayLoad(getMockUser() , getMockWallet()))
				  					.contentType(MediaType.APPLICATION_JSON)
				  					.accept(MediaType.ALL))
		     .andExpect(status().isCreated())
		     .andExpect( jsonPath("$.data.id").value(1L));
		 
	 }

	 @Test
	 public void TestSaveValidDto() throws JsonProcessingException, Exception {
		  BDDMockito
         .given(uService.findById(Mockito.any(Long.class)))
         .willReturn( Optional.of( getMockUser() ));
		   
		 BDDMockito
         .given(wService.findById(Mockito.any(Long.class)))
         .willReturn(Optional.of( getMockWallet() ));
		 
		 
		 BDDMockito
         .given(service.save(Mockito.any(UserWallet.class)))
         .willReturn(getMockUserWallet());
		 
	
		 
		  mvc.perform( MockMvcRequestBuilders
				  					.post("/user_wallets")
				  					.content(getJsonPayLoad(getMockUser() , getMockWallet()))
				  					.contentType(MediaType.APPLICATION_JSON)
				  					.accept(MediaType.ALL))
		     .andExpect(status().isCreated())
		     .andExpect( jsonPath("$.data.id").value(1L));
		 
	 }
 
	 
	 
	private UserWallet getMockUserWallet() {
		UserWallet uw = new UserWallet();
		uw.setId(1L);
		User u = new User();
		u.setId(1L);
		
		Wallet w = new Wallet();
		w.setId(1L);
    
		uw.setUsers(u);
        uw.setWallet(w);
		
		 return uw;
	}

	
	private Wallet getMockWallet() {
		 
		Wallet w = new Wallet();
		w.setId(1L);
       
		 return w;
	}
	
	private User getMockUser() {
		 
		User w = new User();
		w.setId(1L);
       
		 return w;
	}


	

	private byte[] getJsonPayLoad( User user , Wallet wallet) throws JsonProcessingException {
		UserWalletDto userWalletDto = new UserWalletDto();
		userWalletDto.setUser(1L);
		userWalletDto.setWallet(1L);
		 
		 ObjectMapper om = new ObjectMapper();
		 String json = om.writeValueAsString(userWalletDto);	
		
		return json.getBytes();
           
	}
	
}
