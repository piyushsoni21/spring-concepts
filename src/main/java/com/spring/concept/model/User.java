/**
 * 
 */
package com.spring.concept.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Piyush Soni
 *
 */
@Setter
@Getter
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6034336521484595682L;

	/**
	 * @param username
	 * @param password
	 */
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	private String username;

	private String password;
}
