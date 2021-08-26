package com.wallet.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

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
import com.wallet.models.Wallet;
import com.wallet.models.dto.WalletDto;
import com.wallet.services.WalletService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TestUserWalletController {

	 @MockBean
	 WalletService service;
	 
	 @Autowired
	 MockMvc mvc;  
	 
	 @Test
	 public void TestCreateWallet() throws JsonProcessingException, Exception {
		 BDDMockito
         .given(service.save(Mockito.any(Wallet.class)))
         .willReturn(getMockWallet());
		 
		  mvc.perform( MockMvcRequestBuilders
				  					.post("/wallets")
				  					.content(getJsonPayLoad( "carteira-1" , new BigDecimal(1032.22)))
				  					.contentType(MediaType.APPLICATION_JSON)
				  					.accept(MediaType.ALL))
		     .andExpect(status().isCreated())
		     .andExpect( jsonPath("$.data.id").value(1L));
		 
	 }


	private Wallet getMockWallet() {
		 Wallet dto = new Wallet();
		 dto.setId(1L);
		 dto.setName("carteira-1");
		 dto.setValue( new BigDecimal(1032.22));
		
		 return dto;
	}


	private byte[] getJsonPayLoad(String nome, BigDecimal value) throws JsonProcessingException {
		 WalletDto dto = new WalletDto();
		 dto.setName(nome);
		 dto.setValue(value);
		 
		 ObjectMapper om = new ObjectMapper();
         String json = om.writeValueAsString(dto);		  
            
         System.out.println(json); 
         return json.getBytes();    
	}
	
}
