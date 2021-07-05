/**
 * 
 */
package com.cognizant.springlearn2.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author whoami
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Country not found")
public class CountryNotFoundException extends Exception{

}
