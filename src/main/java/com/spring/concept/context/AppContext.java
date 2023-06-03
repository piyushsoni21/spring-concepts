/**
 * 
 */
package com.spring.concept.context;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.spring.concept.model.User;

/**
 * @author Piyush Soni
 *
 */
@Component
public class AppContext {

	private User user;

	private Map<String, User> userMap = new HashMap<>();

	@PostConstruct
	public void init() {
		userMap.put("admin", new User("admin", "admin"));
		userMap.put("Alex", new User("Alex", "pass123"));
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUserDetail(String username) {
		return userMap.get(username);
	}

}
