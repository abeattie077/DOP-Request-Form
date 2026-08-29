package com.example.doprequestform;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "employees")
public class Employee {
	
	//attributes
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, unique = true)
	private Long id;
	@Column(nullable = false, unique = true)
	private String username;
	@Column(nullable = false)
	private String encodedPassword;
	@Column(nullable = false)
	private String name;
	private String role;
	
	//constructors
	public Employee() {
	}
	public Employee(String username, String name, String encodedPW, String role) {
		this.username = username;
		this.name = name;
		this.encodedPassword = encodedPW;
		this.role = role;
	}
	
	//getters
	public Long getId() {
		return this.id;
	}
	public String getUsername() {
		return this.username;
	}
	public String getEncodedPassword() {
		return this.encodedPassword;
	}
	public String getName() {
		return this.name;
	}
	public String getRole() {
		return this.role;
	}
	
	//setters
	public void setUsername(String username) {
		this.username = username;
	}
	public void setEncodedPassword(String encodedPassword) {
		this.encodedPassword = encodedPassword;
	}
	public void setName(String name) {
		this.name = name;
	}
}
