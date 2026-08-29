package com.example.doprequestform;

public class AdminPasswordResetRequest {

	//attributes
	private String username;
	private String newPassword;
	
	//constructors
	public AdminPasswordResetRequest() {
	}
	
	//getters
	public String getUsername() {
		return this.username;
	}
	public String getNewPassword() {
		return this.newPassword;
	}
}
