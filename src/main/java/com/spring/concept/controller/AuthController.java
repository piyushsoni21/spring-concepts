package com.spring.concept.controller;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.concept.context.AppContext;
import com.spring.concept.model.User;
import com.spring.concept.service.ExecutionService;

/**
 * @author Piyush Soni
 *
 */
@RestController
@RequestMapping("/api/v1")
@CrossOrigin("http://localhost:4200")
public class AuthController {

	@Autowired
	private ExecutionService executionService;

	@Autowired
	private AppContext appCtx;


	@GetMapping(value = "/secured")
	public ResponseEntity<String> securetResource() {

		return new ResponseEntity<String>("{\"Data\": \"This is Secured Data\"}", HttpStatus.OK);
	}

	@GetMapping(value = "/test")
	public ResponseEntity<String> launchApp() {

		return new ResponseEntity<String>("{\"Name\": \"Piyush\"}", HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(HttpServletRequest request, @RequestBody User reqUser) {
		try {

			Authentication authentication = null;
			StringBuilder sb = new StringBuilder();
			JSONObject jo = new JSONObject();
			jo.put("success", sb.toString());

			User user = appCtx.getUserDetail(reqUser.getUsername());
			if (user == null) {
				String message = "No such user exists" + reqUser.getUsername();
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
			}
			if (reqUser.getUsername().equalsIgnoreCase(user.getUsername())
					&& reqUser.getPassword().equals(user.getPassword())) {
				/*
				 * authentication = authenticationManager.authenticate( new
				 * UsernamePasswordAuthenticationToken(reqUser.getUsername(),
				 * reqUser.getPassword())); authentication.setAuthenticated(true);
				 * SecurityContextHolder.getContext().setAuthentication(authentication);
				 */
				jo.put("isValidLogin", true);
				appCtx.setUser(user);
				return new ResponseEntity<String>(jo.toString(), HttpStatus.OK);
			} else {
				String message = "Invalid credentials. Please check your username and password.";
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);

			}
		} catch (AuthenticationException e) {
			// Handle login failure
			// return "redirect:/login?error";
			return new ResponseEntity<String>("error", HttpStatus.EXPECTATION_FAILED);
		}
	}

	@GetMapping("/logout")
	public ResponseEntity<String> logout(HttpServletRequest request) {
		try {
			/*
			 * Authentication authnetication =
			 * SecurityContextHolder.getContext().getAuthentication();
			 * authnetication.setAuthenticated(false);
			 * SecurityContextHolder.getContext().setAuthentication(null);
			 * appCtx.setUser(null);
			 */
			StringBuilder sb = new StringBuilder("success");
			JSONObject jo = new JSONObject();
			jo.put("success", sb.toString());
			return new ResponseEntity<String>(jo.toString(), HttpStatus.OK);
		} catch (AuthenticationException e) {
			// Handle login failure
			// return "redirect:/login?error";
			return new ResponseEntity<String>("error", HttpStatus.EXPECTATION_FAILED);
		}
	}

	@GetMapping("/checkauthorize")
	public ResponseEntity<String> checkAuthorization(HttpServletRequest request) {
		try {

			executionService.checkAuthorization();
			JSONObject jo = new JSONObject();
			jo.put("isAuthorized", true);
			return new ResponseEntity<String>(jo.toString(), HttpStatus.OK);
		} catch (AuthenticationException e) {
			// Handle login failure
			// return "redirect:/login?error";
			return new ResponseEntity<String>("error", HttpStatus.EXPECTATION_FAILED);
		}
	}

}
