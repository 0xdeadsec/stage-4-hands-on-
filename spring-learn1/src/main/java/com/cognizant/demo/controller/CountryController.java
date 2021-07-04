/**
 * 
 */
package com.cognizant.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.demo.Country;
import com.cognizant.demo.service.CountryNotFoudException;
import com.cognizant.demo.service.CountryService;

/**
 * @author whoami
 *
 */
@RestController
public class CountryController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);
	
	@Autowired
	private CountryService cservice;
	
	/*
	 * http://localhost:8083/country
	 */
	@RequestMapping(value = "/country",method = RequestMethod.GET)
	public Country getCountryIndia() {
		LOGGER.info("START");
		ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
		Country country = (Country) context.getBean("in", Country.class);
		LOGGER.info("END");
		return country;
	}
	/*
	 * http://localhost:8083/countries
	 */
	@GetMapping(value = "/countries")
	public List<Country> getAllCountries(){
		LOGGER.info("START");
		ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
		List<Country> list = context.getBean("countryList", ArrayList.class);
		LOGGER.info("END");
		return list;
	}
	/*
	 * http://localhost:8083/countries/in
	 */
	@GetMapping(value = "/countries/{code}")
	public Country getCountry(@PathVariable String code) throws CountryNotFoudException {
		LOGGER.info("START");
		return cservice.getCountry(code);
	}
}
