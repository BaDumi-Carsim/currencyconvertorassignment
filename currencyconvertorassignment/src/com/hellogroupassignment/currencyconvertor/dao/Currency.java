package com.hellogroupassignment.currencyconvertor.dao;

import java.io.Serializable;
import java.math.BigDecimal;

public class Currency implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 969349128526852328L;
	private String postCurrencyTo;
	private BigDecimal postValueToUSD;
	private BigDecimal postconvertedValue;

	public void setPostCurrencyTo(String postCurrencyTo) {
		this.postCurrencyTo = postCurrencyTo;
	}

	public void setPostValueToUSD(BigDecimal postValueToUSD) {
		this.postValueToUSD = postValueToUSD;
	}

	public void setPostconvertedValue(BigDecimal postconvertedValue) {
		this.postconvertedValue = postconvertedValue;
	}

	public String getPostCurrencyTo() {
		return postCurrencyTo;
	}

	public BigDecimal getPostValueToUSD() {
		return postValueToUSD;
	}

	public BigDecimal getPostconvertedValue() {
		return postconvertedValue;
	}

	@Override
	public String toString() {
		return "Currency [postCurrencyTo=" + postCurrencyTo + ", postValueToUSD=" + postValueToUSD
				+ ", postconvertedValue=" + postconvertedValue + "]";
	}

	
	 
}
