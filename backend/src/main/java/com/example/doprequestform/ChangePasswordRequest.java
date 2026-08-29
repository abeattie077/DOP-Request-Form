package com.example.doprequestform;

public class ChangePasswordRequest {

	//attributes
	private String currentPassword;
	private String newPassword;
	
	//constructor
	public ChangePasswordRequest() {
	}
	
	//getters
	public String getCurrentPassword() {
		return this.currentPassword;
	}
	public String getNewPassword() {
		return this.newPassword;
	}
}
