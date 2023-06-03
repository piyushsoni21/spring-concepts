package com.spring.concept.aspect;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.concept.context.AppContext;
import com.spring.concept.custonannotation.Authorize;
import com.spring.concept.exception.AuthorizationException;

@Aspect
@Component
public class SpringConceptAspect {

	@Autowired
	private AppContext appCtx;

	@Before("@annotation(authorize)")
	public void checkAuthorization(JoinPoint joinPoint, Authorize authorize) {
		String[] allowedUsers = authorize.allowedUsers();
		String username = appCtx.getUser().getUsername();

		if (!isUserAuthorized(username, allowedUsers)) {
			throw new AuthorizationException(username + " is not authorized to access this resource.");
		}
	}

	private boolean isUserAuthorized(String username, String[] allowedUsers) {
		List<String> users = Arrays.asList(allowedUsers);
		return users.contains(username);
	}

}