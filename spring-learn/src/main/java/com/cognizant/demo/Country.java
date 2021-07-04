/**
 * 
 */
package com.cognizant.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author whoami
 *
 */
public class Country {
	
	private String code;
	private String name;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Country.class);
	
	/**
	 * 
	 */
	public Country() {
		super();
		LOGGER.info("Inside Country Constructor");
	}

	public String getCode() {
		LOGGER.debug("Inside code getter");
		return code;
	}

	public void setCode(String code) {
		LOGGER.debug("Inside code setter");
		this.code = code;
	}

	public String getName() {
		LOGGER.debug("Inside name getter");
		return name;
	}

	public void setName(String name) {
		LOGGER.debug("Inside name setter");
		this.name = name;
	}

	@Override
	public String toString() {
		return "Country [code=" + code + ", name=" + name + "]";
	}
	
	
}
