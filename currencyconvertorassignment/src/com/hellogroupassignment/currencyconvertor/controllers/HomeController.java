package com.hellogroupassignment.currencyconvertor.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hellogroupassignment.currencyconvertor.dao.Currencies;
import com.hellogroupassignment.currencyconvertor.dao.Currency;
import com.hellogroupassignment.currencyconvertor.dao.Login;
import com.hellogroupassignment.currencyconvertor.service.CurrenciesService;
import com.hellogroupassignment.currencyconvertor.service.LoginService;

//Home Controller

@Controller
public class HomeController {
	private LoginService loginService;
	private CurrenciesService currencyService;
	private List<Currencies> currencies;

	@Autowired
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	@Autowired
	public void setCurrencyService(CurrenciesService currencyService) {
		this.currencyService = currencyService;
	}

	@RequestMapping("/")
	public String showHome() {

		return "home";
	}

	@RequestMapping(value = "/currencylist")
	public String showCurrencies(Model model) {
		List<Currencies> currency = currencyService.getCurrent();

		model.addAttribute("currency", currency);
		model.addAttribute("currencies", new Currencies());

		return "currencylist";
	}

	@RequestMapping(value = "/currencymanagement", method = RequestMethod.POST)
	public String addCurrency(Model model, @Valid Currencies currencies, BindingResult result) {

		BigDecimal convertedValueToUSD = null;
		String getCurrency = currencies.getCurrency();
		String currencyCode = currencies.getCurrencyCode().toUpperCase().trim();
		BigDecimal valueToUSD = currencies.getValueToUSD();

		try {
			convertedValueToUSD = new BigDecimal(String.valueOf(valueToUSD));
		} catch (NumberFormatException e) {
			model.addAttribute("errorMsg", "A number format error has occurred please check your values.");
			return "currencylist";
		}

		System.out.println(currencies);
		currencyService.addCurrency(new Currencies(getCurrency, convertedValueToUSD, currencyCode));

		
		List<Currencies> currency = currencyService.getCurrent();

		model.addAttribute("currency", currency);

		return "currencylist";
	}

	@RequestMapping("/login")
	public String login(Model model) {
		model.addAttribute("login", new Login());

		return "login";
	}

	@RequestMapping(value = "/convertcurrency", method = RequestMethod.POST)
	public String doLogin(Model model, @Valid Login login, BindingResult result) {

		System.out.println(login);

		if (result.hasErrors()) {
			System.out.println("Form does not validate");
			
			return "login";

		}
		try {

			loginService.getCurrentUser(login);

		} catch (EmptyResultDataAccessException ex) {
			model.addAttribute("errorMsg", "Your email password combination is not valid.");
			return "login";
		}

		currencies = currencyService.getCurrent();
		System.out.println(currencies);
		model.addAttribute("currencies", currencies);
		return "convertcurrency";
	}

	@RequestMapping(value = "/convertcurrency", produces = "application/json")
	@ResponseBody
	public String convertcurrency(Model model, @RequestBody String postedData) {
		BigDecimal convertedValue = null;
		BigDecimal valueToUSD = null;
		String currencyTo;
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = null; 

		convertedValue = currencyService.conversionCalculation(postedData);
		currencyTo = currencyService.getCurrencyTo(postedData);

		currencies = currencyService.getCurrent();

		for (Currencies currency : currencies) {

			if (currency.getCurrency().equals(currencyTo)) {

				valueToUSD = currency.getValueToUSD();
			}
		}
          
		//mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		//mapper.disable(Feature.FAIL_ON_EMPTY_BEANS);
		Currency postBackValues = new Currency();
		postBackValues.setPostCurrencyTo(currencyTo);
		postBackValues.setPostValueToUSD(valueToUSD);
		postBackValues.setPostconvertedValue(convertedValue);
		System.out.println();
		System.out.println(convertedValue + " " + currencyTo + " " +valueToUSD);
		
		//return "{\"msg\":\"success\"}";
		System.out.println();
		try {
			jsonInString = mapper.writeValueAsString(postBackValues);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
       return jsonInString;
	}
	
	
	@RequestMapping(value = "/convertcurrency")
	public String convertCurrency(Model model) {

		currencies = currencyService.getCurrent();

		System.out.println(currencies);

		model.addAttribute("currencies", currencies);
		
		return "convertcurrency";
	}

}
