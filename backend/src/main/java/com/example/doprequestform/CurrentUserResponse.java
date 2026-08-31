package com.example.doprequestform;

public class CurrentUserResponse {
	
	//attributes
	private String username;
	private String name;
	private String role;
	
	//constructors
	public CurrentUserResponse(String username, String name, String role) {
		this.username = username;
		this.name = name;
		this.role = role;
	}
	
	//getters
	public String getUsername() {
		return this.username;
	}
	public String getName() {
		return this.name;
	}
	public String getRole() {
		return this.role;
	}
}
