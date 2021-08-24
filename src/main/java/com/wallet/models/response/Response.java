package com.wallet.models.response;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Response<T extends Object> {
	private T data;
	private List<String> errors;

	public List<String> getErrors(){
	       if(errors == null) {
	    	  errors = new ArrayList<>(); 
	       }
	     
	   return   errors;
   }

}
