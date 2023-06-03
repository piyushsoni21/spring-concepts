/**
 * 
 */
package com.spring.concept.service;

import org.springframework.stereotype.Service;

import com.spring.concept.custonannotation.Authorize;

/**
 * @author Piyush Soni
 *
 */
@Service
public class ExecutionService {

	@Authorize(allowedUsers = { "admin" })
	public boolean checkAuthorization() {
		return true;
	}
}
