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
				.roles(employee.getRole())
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
		Employee employee = new Employee(username, name, encodedPassword, "EMPLOYEE");
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
	
	//change password
	public boolean changePassword(String username, String currentPW, String newPW) {
		boolean result = false;
		Optional<Employee> employee = getEmployee(username);
		if (employee.isPresent()) {
			Employee target = employee.get();
			if (passwordEncoder.matches(currentPW, target.getEncodedPassword())) {
				String encodedNewPassword = passwordEncoder.encode(newPW);
				target.setEncodedPassword(encodedNewPassword);
				employeeRepository.save(target);
				result=true;
			}
		}
		return result;
	}
	public boolean adminChangePassword(String username, String newPW) {
		boolean result = false;
		Optional<Employee> foundEmployee = this.getEmployee(username);
		if (foundEmployee.isPresent()) {
			Employee target = foundEmployee.get();
			String encodedNewPassword = passwordEncoder.encode(newPW);
			target.setEncodedPassword(encodedNewPassword);
			employeeRepository.save(target);
			result = true;
		}
		return result;
	}
	
	//delete employee
	public boolean adminDeleteEmployee(Long id) {
		boolean result = false;
		Optional<Employee> foundEmployee = employeeRepository.findById(id);
		if (foundEmployee.isPresent()) {
			Employee target = foundEmployee.get();
			if ("EMPLOYEE".equals(target.getRole())) {
				employeeRepository.delete(target);
				result = true;
			}
		}
		return result;
	}
}
