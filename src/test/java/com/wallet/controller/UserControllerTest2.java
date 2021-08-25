package com.wallet.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.wallet.models.dto.UserDto;
import com.wallet.services.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest()
@ActiveProfiles("test")
//new
@AutoConfigureMockMvc
public class UserControllerTest2 {

	private static final Long ID = 1L;
	private static final String PASSWORD = "123@#ASE!@";
	private static final String NAME = "teste";
	private static final String MAIL = "user@mail.com";
	private static final String URL = "/user";
	private static final String CPF = "123123123-12";

	//utilizado para mocar o serviço.
	@MockBean
	 UserService service;
	 
	 @Autowired
	 MockMvc mvc;
	 
	 @Test
	 public void testSaveUser() throws Exception {
		 //quando eu salvar 
		 BDDMockito
		            .given(service.save(Mockito.any(User.class)))
		            .willReturn(getMockUser());
		 
		 mvc.perform(MockMvcRequestBuilders
				 							.post(URL)
				 							.content(getJsonPayLoad(ID,  MAIL, NAME, PASSWORD, CPF))
				 							.contentType(MediaType.APPLICATION_JSON)
				 							.accept(MediaType.ALL) )
		       .andExpect(jsonPath("$.data.id").value(ID))	
		       .andExpect(jsonPath("$.data.name").value(NAME))		       
		       .andExpect(jsonPath("$.data.cpf").value(CPF))
		       .andExpect(jsonPath("$.data.password").doesNotExist())	
			   .andExpect(status().isCreated())
			   ;
	         
	 }
	 
	 @Test
	 public void testSaveInvalidUser() throws Exception {
		 //quando eu salvar 
		 BDDMockito
		            .given(service.save(Mockito.any(User.class)))
		            .willReturn(getMockUser());
		 
		 mvc.perform(MockMvcRequestBuilders
				 							.post(URL)
				 							.content(getJsonPayLoad(ID,  MAIL, null, PASSWORD, null))
				 							.contentType(MediaType.APPLICATION_JSON)
				 							.accept(MediaType.ALL) )
			   .andExpect(status().isBadRequest()) ;
	         
	 }

	 
	 @Test
	 public void testSaveInvalidEmail() throws Exception {
		 //quando eu salvar 
		 BDDMockito
		            .given(service.save(Mockito.any(User.class)))
		            .willReturn(getMockUser());
		 
		 mvc.perform(MockMvcRequestBuilders
				 							.post(URL)
				 							.content(getJsonPayLoad(ID,  "EMAIL", "12", PASSWORD, CPF))
				 							.contentType(MediaType.APPLICATION_JSON)
				 							.accept(MediaType.ALL) )
			   .andExpect(status().isBadRequest()) ;
	         
	 }

	 
	  public User getMockUser() {
		  User u = new User();
		  u.setId(ID);
		  u.setEmail(MAIL);
		  u.setName(NAME);
		  u.setPassword(PASSWORD);
		  u.setCpf(CPF);
		  return u;
	  }
	  
	  
	  
	  
		public String getJsonPayLoad(Long id ,String mail , String test , String password , String cpf) throws JsonProcessingException {
		  UserDto dto = new UserDto();
		  dto.setId(id);
		  dto.setEmail(mail);
		  dto.setName(test);
		  dto.setCpf(cpf);
		  dto.setPassword(password);
		  
		  ObjectMapper om = new ObjectMapper();
            return    om.writeValueAsString(dto);		  
	  }
}
