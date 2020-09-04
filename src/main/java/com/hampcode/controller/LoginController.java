package com.hampcode.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.hampcode.model.entity.Login;

@Named
@ViewScoped
public class LoginController implements Serializable {

	private Login login;  
	
	@PostConstruct
	public void init() {
		login = new Login();
		
	}
	
	
	
	
	

	
	
	
	
	
	


	public Login getLogin() {
		return login;
	}














	public void setLogin(Login login) {
		this.login = login;
	}














	private static final long serialVersionUID = 1L;

}
