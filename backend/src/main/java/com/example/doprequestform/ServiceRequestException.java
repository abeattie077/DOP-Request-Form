package com.example.doprequestform;

public class ServiceRequestException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public ServiceRequestException () {
		super("You must select at least one service option from: DOP Testing, Filter Change, or Other");
	}
}
