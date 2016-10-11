package com.hellogroupassignment.currencyconvertor.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component("currencyDao")
public class CurrenciesDao {

	private NamedParameterJdbcTemplate nameJdbcTemplate;

	@Autowired
	public void setDataSource(DataSource jdbc) {

		this.nameJdbcTemplate = new NamedParameterJdbcTemplate(jdbc);
	}

	// return the list of database currency objects
	public List<Currencies> getCurrencies() {

		return nameJdbcTemplate.query("select * from currencies", new RowMapper<Currencies>() {

			@Override
			public Currencies mapRow(ResultSet rs, int rowNum) throws SQLException {
				Currencies currencies = new Currencies();
				currencies.setId(rs.getInt("id"));
				currencies.setCurrency(rs.getString("currency"));
				currencies.setCurrencyCode(rs.getString("currencycode"));
				currencies.setValueToUSD(rs.getBigDecimal("valueToUSD"));
				return currencies;
			}
		});
	}

	// create a currency object in the database
	public boolean addCurrency(Currencies currencies) {
		BeanPropertySqlParameterSource parameter = new BeanPropertySqlParameterSource(currencies);

		return nameJdbcTemplate.update(
				"insert into currencies (currency,valueToUSD,currencycode) values (:currency,:valueToUSD,:currencycode)",
				parameter) == 1;

	}
	
	public void insertCurrency(Currencies currencies) {
		String query = "insert into currencies (currency,valueToUSD,currencycode) values (:currency,:valueToUSD,:currencycode)";
		Map<String,Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("currency", currencies.getCurrency());
		namedParameters.put("valueToUSD", currencies.getValueToUSD());
		namedParameters.put("currencycode", currencies.getCurrencyCode());
		nameJdbcTemplate.update(query, namedParameters);
	}

}
