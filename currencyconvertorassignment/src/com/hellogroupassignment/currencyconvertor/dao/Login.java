package com.hellogroupassignment.currencyconvertor.dao;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Login {
    
	private int id;
	@Pattern(regexp=".*\\@.*\\..*",message="Not a valid email address")
	private String email;
	@Size(max=100,min=8,message="Minimum length should be 8 characters")
	private String password;

	
	public Login() {
		super();
	}

	public Login(String email, String password) {

		this.email = email;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Login [id=" + id + ", email=" + email + ", password=" + password + "]";
	}
	
	

}
