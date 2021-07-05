/**
 * 
 */
package com.cognizant.springlearn2.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.springlearn2.model.Country;
import com.cognizant.springlearn2.service.CountryNotFoundException;
import com.cognizant.springlearn2.service.CountryService;

/**
 * @author whoami
 *
 */
@RestController
@RequestMapping(value = "/countries")
public class CountryController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);

	@Autowired
	private CountryService cservice;

	/*
	 * http://localhost:8083/country
	 */
	/*
	 * @RequestMapping(value = "/country",method = RequestMethod.GET) public Country
	 * getCountryIndia() { LOGGER.info("START"); ApplicationContext context = new
	 * ClassPathXmlApplicationContext("country.xml"); Country country = (Country)
	 * context.getBean("in", Country.class); LOGGER.info("END"); return country; }
	 */

	/*
	 * http://localhost:8083/countries
	 */
	@GetMapping
	public List<Country> getAllCountries() {
		LOGGER.info("START");
		ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
		List<Country> list = context.getBean("countryList", ArrayList.class);
		LOGGER.info("END");
		return list;
	}

	/*
	 * http://localhost:8083/countries/in
	 */
	@GetMapping("/{code}")
	public Country getCountry(@PathVariable String code) throws CountryNotFoundException {
		LOGGER.info("START");
		return cservice.getCountry(code);
	}
	/*
	 * http://localhost:8083/countries 
	 * { "code":"UK", "name":"United Kingdom" }
	 * 
	 */

	@PostMapping
	public Country addCountry(@RequestBody @Valid Country country) {
		LOGGER.info("Start");
		/*
		 * ValidatorFactory factory= Validation.buildDefaultValidatorFactory();
		 * Validator validator=factory.getValidator(); Set<ConstraintViolation<Country>>
		 * violations=validator.validate(country); List<String> errors=new
		 * ArrayList<String>(); for(ConstraintViolation<Country> violation : violations)
		 * { errors.add(violation.getMessage()); } if(errors.size()>0) throw new
		 * ResponseStatusException(HttpStatus.BAD_REQUEST,errors.toString());
		 */
		LOGGER.info(country.toString());
		LOGGER.info("End");
		return country;
	}

	/*
	 * http://localhost:8083/countries 
	 * { "code":"UK", "name":"United Kingdom" }
	 * 
	 */

	@PutMapping
	public Country updateCountry(@RequestBody Country country) throws CountryNotFoundException {
		LOGGER.info("Start");
		Country updated = cservice.updateCountry(country);
		LOGGER.info(updated.toString());
		LOGGER.info("End");
		return country;
	}

}
