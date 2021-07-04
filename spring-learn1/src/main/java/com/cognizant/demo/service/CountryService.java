/**
 * 
 */
package com.cognizant.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.cognizant.demo.Country;

/**
 * @author whoami
 *
 */
@Service
public class CountryService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);
	
	public Country getCountry(String code) throws CountryNotFoudException {
		LOGGER.info("START");
		ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
		List<Country> list = context.getBean("countryList", ArrayList.class);
		for(Country obj:list) {
			System.out.println(obj.getCode());
			if(code.equalsIgnoreCase(obj.getCode())) {
				return obj;
			}
		}
		throw new CountryNotFoudException();
	}
}
