package com.example.doprequestform;

import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
	public boolean login(@RequestBody LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContext context = SecurityContextHolder.createEmptyContext();
		context.setAuthentication(authentication);
		SecurityContextHolder.setContext(context);
		HttpSessionSecurityContextRepository repository = new HttpSessionSecurityContextRepository();
		repository.saveContext(context, request, response);
		return authentication.isAuthenticated();
		
	}
	
	//create new Employee
	@PostMapping("/create")
	public boolean createEmployee(@RequestBody CreateEmployeeRequest createRequest) {
		employeeService.createEmployee(createRequest.getUsername(), createRequest.getName(), createRequest.getPassword());
		return true;
	}
	
	//change password
	@PutMapping("/password")
	public boolean changePassword(@RequestBody ChangePasswordRequest request, Authentication authentication) {
		String username = authentication.getName();
		return employeeService.changePassword(username, request.getCurrentPassword(), request.getNewPassword());
	}
	
	@PutMapping("/admin/password")
	public boolean adminChangePassword(@RequestBody AdminPasswordResetRequest request) {
		return employeeService.adminChangePassword(request.getUsername(), request.getNewPassword());
	}
	
	//delete employee
	@DeleteMapping("/employee/{id}")
	public boolean deleteEmployee(@PathVariable Long id) {
		return employeeService.adminDeleteEmployee(id);
	}
	
	//logout
	@PostMapping("/logout")
	public boolean logout(HttpServletRequest request) {
		HttpSession target = request.getSession(false);
		if (target != null) {
			target.invalidate();
		}
		SecurityContextHolder.clearContext();
		return true;
	}
	
	//get role
	@GetMapping("/me")
	public CurrentUserResponse getCurrentUser(Authentication authentication) {
		Employee employee = employeeService.getEmployee(authentication.getName()).orElseThrow();
		return new CurrentUserResponse(employee.getUsername(), employee.getName(), employee.getRole());
	}
}
