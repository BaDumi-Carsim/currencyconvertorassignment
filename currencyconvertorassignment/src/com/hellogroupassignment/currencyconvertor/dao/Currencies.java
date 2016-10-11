package com.hellogroupassignment.currencyconvertor.dao;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Currencies{

	private int id;

	@NotNull
	@Size(min = 3, message = "minimum of 3 characters")
	private String currency;

	@NotNull(message = "This value field cannot be null")
	private BigDecimal valueToUSD;

	@Size(min = 3, max = 3, message = "Please enter 3 letter currency code")
	private String currencyCode;

	public Currencies() {

	}

	public Currencies(int id, String currency, BigDecimal valueToUSD, String currencyCode) {

		this.id = id;
		this.currency = currency;
		this.valueToUSD = valueToUSD;
		this.currencyCode = currencyCode;
	}

	public Currencies(String currency, BigDecimal valueToUSD, String currencyCode) {
		super();
		this.currency = currency;
		this.valueToUSD = valueToUSD;
		this.currencyCode = currencyCode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public BigDecimal getValueToUSD() {
		return valueToUSD;
	}

	public void setValueToUSD(BigDecimal valueToUSD) {
		this.valueToUSD = valueToUSD;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	@Override
	public String toString() {
		return "Currencies [id=" + id + ", currency=" + currency + ", valueToUSD=" + valueToUSD + ", currencyCode="
				+ currencyCode + "]";
	}

}
