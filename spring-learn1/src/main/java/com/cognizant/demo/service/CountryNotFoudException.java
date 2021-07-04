/**
 * 
 */
package com.cognizant.demo.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author whoami
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Country not found")
public class CountryNotFoudException extends Exception{

}
