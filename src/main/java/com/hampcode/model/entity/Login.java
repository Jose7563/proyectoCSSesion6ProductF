package com.hampcode.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "logins")
public class Login {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String userName;

	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Login(String userName) {
		super();
		this.userName = userName;
	}

	
	
	
}
