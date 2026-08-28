package com.example.doprequestform;

import org.springframework.stereotype.Service;

@Service
public class ServiceRequestService {
	//Repository
	private final ServiceRequestRepository serviceRequestRepository;
	
	//constructor
	public ServiceRequestService(ServiceRequestRepository serviceRequestRepository) {
		this.serviceRequestRepository = serviceRequestRepository;
	}
	
	//save a new request
	public ServiceRequest saveNewRequest(ServiceRequest serviceRequest) throws ServiceRequestException {
		if (serviceRequest.isDopTesting() || serviceRequest.isFilterChange() || serviceRequest.isOtherServiceRequest()) {
			return serviceRequestRepository.save(serviceRequest);
		}
		throw new ServiceRequestException();
	}
	
}
