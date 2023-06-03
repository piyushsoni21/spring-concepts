/**
 * 
 */
package com.spring.concept.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * @author Piyush Soni
 *
 */
@ControllerAdvice
public class SpringConceptExceptionHandler {

	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<Object> handleAuthorizationException(AuthorizationException ex, WebRequest request) {

		String message = " You don't have sufficient privliedge to access this auth resource " + ex.getMessage();
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);

	}

}
