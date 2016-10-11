package com.hellogroupassignment.currencyconvertor.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component("loginDao")
public class LoginDao {

	private NamedParameterJdbcTemplate nameJdbcTemplate;

	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.nameJdbcTemplate = new NamedParameterJdbcTemplate(jdbc);
	}

	// return one user object from database
	public Login getLoginDetails(Login login) {

		BeanPropertySqlParameterSource parameter = new BeanPropertySqlParameterSource(login);

		return nameJdbcTemplate.queryForObject("select * from logindetails where email= :email and password=:password",
				parameter, new RowMapper<Login>() {

					@Override
					public Login mapRow(ResultSet rs, int rowNum) throws SQLException {
						Login login = new Login();
						login.setId(rs.getInt("id"));
						login.setEmail(rs.getString("email"));
						login.setPassword(rs.getString("password"));
						return login;
					}
				});
	}
}
