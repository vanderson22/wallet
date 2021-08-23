package com.wallet.controller;

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
public class UserControllerTest {

	 private static final String PASSWORD = "123@#ASE!@";

	private static final String TESTE = "teste";

	private static final String USER_MAIL_COM = "user@mail.com";

	private static final String URL = "/user";

	//utilizado para mocar o serviço.
	@MockBean
	 UserService service;
	 
	 @Autowired
	 MockMvc mvc;
	 
	 @Test
	 public void testSaver() throws Exception {
		 //quando eu salvar 
		 BDDMockito
		            .given(service.save(Mockito.any(User.class)))
		            .willReturn(getMockUser());
		 
		 mvc.perform(MockMvcRequestBuilders
				 							.post(URL)
				 							.content(getJsonPayLoad())
				 							.contentType(MediaType.APPLICATION_JSON)
				 							.accept(MediaType.ALL)
			  ).andExpect(status()
						             .isCreated());
	         
	 }
	 
	  public User getMockUser() {
		  User u = new User();
		  u.setEmail(USER_MAIL_COM);
		  u.setName(TESTE);
		  u.setPassword(PASSWORD);
		  return u;
	  }
	  
		public String getJsonPayLoad() throws JsonProcessingException {
		  UserDto dto = new UserDto();
		  dto.setEmail(USER_MAIL_COM);
		  dto.setName(TESTE);
		  dto.setPassword(PASSWORD);
   
		  
		   ObjectMapper om = new ObjectMapper();
            return    om.writeValueAsString(dto);		  
	  }
}
