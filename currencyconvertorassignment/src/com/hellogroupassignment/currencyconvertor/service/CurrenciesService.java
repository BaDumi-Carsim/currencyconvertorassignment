package com.hellogroupassignment.currencyconvertor.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hellogroupassignment.currencyconvertor.dao.Currencies;
import com.hellogroupassignment.currencyconvertor.dao.CurrenciesDao;

@Service("currencyService")
public class CurrenciesService {

	private CurrenciesDao currenciesDao;

	@Autowired
	public void setCurrenciesDao(CurrenciesDao currenciesDao) {
		this.currenciesDao = currenciesDao;
	}

	public List<Currencies> getCurrent() {
		return currenciesDao.getCurrencies();
	}

	public void addCurrency(Currencies currencies) {
		currenciesDao.insertCurrency(currencies);

	}

	public BigDecimal conversionCalculation(String postedData) {
		String currencyFrom;
		String amountValue;
		String currencyTo;
		BigDecimal currencyToValueToUSD = null;
		BigDecimal convertedAmountValue = null;
		List<Currencies> currencies;
		BigDecimal convertedValue = null;

		String currencyValues = postedData;
		System.out.println(currencyValues);
		currencyValues = currencyValues.replace("\"", "");
		String[] result = currencyValues.split("_");
		currencyFrom = result[0];
		amountValue = result[1];
		currencyTo = result[2];

		currencies = currenciesDao.getCurrencies();
		for (Currencies currency : currencies) {
			if (currency.getCurrency().toLowerCase().trim().equals(currencyTo.toLowerCase().trim())) {
				currencyToValueToUSD = currency.getValueToUSD();
			}
		}

		for (Currencies currency : currencies) {
			if (currency.getCurrency().toLowerCase().trim().equals(currencyFrom.toLowerCase().trim())) {

				try {
					convertedAmountValue = new BigDecimal(amountValue);

					convertedValue = (currencyToValueToUSD.divide(currency.getValueToUSD(), 2, RoundingMode.HALF_UP))
							.multiply(convertedAmountValue);

					System.out.println(convertedValue);

				} catch (NumberFormatException e) {
					System.out.println(e.getMessage());
				}
			}
		}

		return convertedValue;

	}

	public String getCurrencyTo(String postedData) {
		String currencyTo;
		String[] splitPost;
        
		postedData = postedData.replace("\"", "");
		splitPost = postedData.split("_");
		currencyTo = splitPost[2];
	
		return currencyTo;
			
	}
}
