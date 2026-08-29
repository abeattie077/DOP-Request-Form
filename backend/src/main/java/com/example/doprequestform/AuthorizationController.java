package com.example.doprequestform;

import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/authorization")
public class AuthorizationController {

	//attributes
	private final EmployeeService employeeService;
	private final AuthenticationManager authenticationManager;
	
	//constructor
	public AuthorizationController(EmployeeService employeeService, AuthenticationManager authenticationManager) {
		this.employeeService = employeeService;
		this.authenticationManager = authenticationManager;
	}
	
	//login
	@PostMapping
	public boolean login(@RequestParam String username, @RequestParam String password, HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		SecurityContext context = SecurityContextHolder.createEmptyContext();
		context.setAuthentication(authentication);
		SecurityContextHolder.setContext(context);
		HttpSessionSecurityContextRepository repository = new HttpSessionSecurityContextRepository();
		repository.saveContext(context, request, response);
		return authentication.isAuthenticated();
		
	}
	
	//create new Employee
	@PostMapping("/create")
	public Employee createEmployee(@RequestParam String username, @RequestParam String name, @RequestParam String password) {
		return employeeService.createEmployee(username, name, password);
	}
}
