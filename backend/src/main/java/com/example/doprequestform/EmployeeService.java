package com.example.doprequestform;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService implements UserDetailsService {
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		Optional<Employee> foundEmployee = employeeRepository.findByUsername(username);
		if (foundEmployee.isEmpty()) {
			throw new UsernameNotFoundException("Employee Not Found");
		}
		Employee employee = foundEmployee.get();
		return User.builder()
				.username(employee.getUsername())
				.password(employee.getEncodedPassword())
				.roles("EMPLOYEE")
				.build();
	}
	
	//repository and password encoder
	private final EmployeeRepository employeeRepository;
	private final BCryptPasswordEncoder passwordEncoder;
	
	//constructor
	public EmployeeService (EmployeeRepository employeeRepository, BCryptPasswordEncoder passwordEncoder) {
		this.employeeRepository = employeeRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	//create employee
	public Employee createEmployee(String username, String name, String password) {
		String encodedPassword = passwordEncoder.encode(password);
		Employee employee = new Employee(username, name, encodedPassword);
		return employeeRepository.save(employee);
	}
	
	//find employee
	public Optional<Employee> getEmployee(String username){
		return employeeRepository.findByUsername(username);
	}
	
	//Login
	public boolean login(String username, String password) {
		boolean result = false;
		Optional<Employee> foundEmployee = this.getEmployee(username);
		if (foundEmployee.isPresent()) {
			Employee target = foundEmployee.get();
			result = (passwordEncoder.matches(password, target.getEncodedPassword()));
		}
		return result;
	}
}
