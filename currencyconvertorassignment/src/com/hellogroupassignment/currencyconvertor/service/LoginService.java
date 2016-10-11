package com.hellogroupassignment.currencyconvertor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hellogroupassignment.currencyconvertor.dao.Login;
import com.hellogroupassignment.currencyconvertor.dao.LoginDao;

@Service("loginService")
public class LoginService {

	private LoginDao loginDao;

	@Autowired
	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}

	public void getCurrentUser(Login login) {
		loginDao.getLoginDetails(login);
	}

}
