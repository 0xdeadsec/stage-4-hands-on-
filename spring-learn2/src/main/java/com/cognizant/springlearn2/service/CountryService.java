/**
 * 
 */
package com.cognizant.springlearn2.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.cognizant.springlearn2.model.Country;


/**
 * @author whoami
 *
 */
@Service
public class CountryService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);
	
	public Country getCountry(String code) throws CountryNotFoundException {
		LOGGER.info("START");
		ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
		List<Country> list = context.getBean("countryList", ArrayList.class);
		for(Country obj:list) {
			System.out.println(obj.getCode());
			if(code.equalsIgnoreCase(obj.getCode())) {
				return obj;
			}
		}
		throw new CountryNotFoundException();
	}
	
	public Country updateCountry(Country country) throws CountryNotFoundException{
		ApplicationContext context=new ClassPathXmlApplicationContext("country.xml");
		Country old=null;
			try{
				old=context.getBean(country.getCode().toLowerCase(),Country.class);
			}catch (Exception e) {
				// TODO: handle exception
				throw new CountryNotFoundException();
			}	
			old.setName(country.getName());
			return old;
			
			}
}
