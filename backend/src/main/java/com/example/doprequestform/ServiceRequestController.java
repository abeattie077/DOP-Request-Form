package com.example.doprequestform;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/requests")
public class ServiceRequestController {
	
	//attributes
	private final ServiceRequestService serviceRequestService;
	
	//constructor
	public ServiceRequestController (ServiceRequestService serviceRequestService) {
		this.serviceRequestService = serviceRequestService;
	}
	
	//POST
	@PostMapping
	public ResponseEntity<ServiceRequest> createRequest(@RequestBody ServiceRequest request) throws ServiceRequestException {
		ServiceRequest savedRequest = serviceRequestService.saveNewRequest(request);
		return ResponseEntity.status(201).body(savedRequest);
	}
	
	//GET
	@GetMapping
	public ResponseEntity<List<ServiceRequest>> getAllRequests(){
		List<ServiceRequest> requests = serviceRequestService.getAllRequests();
		return ResponseEntity.ok(requests);
	}
}
