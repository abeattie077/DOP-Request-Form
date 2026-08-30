package com.example.doprequestform;

import java.util.List;
import java.util.Optional;

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
		if (serviceRequest.getCompanyName()==null || serviceRequest.getCompanyName().isBlank() || serviceRequest.getRequesterFirstName() == null 
				|| serviceRequest.getRequesterFirstName().isBlank() || serviceRequest.getRequesterLastName() == null ||
				serviceRequest.getRequesterLastName().isBlank() || serviceRequest.getServiceAddress() == null ||
				serviceRequest.getServiceAddress().isBlank() || serviceRequest.getDesiredServiceDate() == null ||
				serviceRequest.getNumberOfUnits()<=0 || serviceRequest.getCustomerPhone() == null || serviceRequest.getCustomerPhone().isBlank() ||
				serviceRequest.getCustomerEmail() == null || serviceRequest.getCustomerEmail().isBlank()) {
			throw new ServiceRequestException();
		}
		if (serviceRequest.isDopTesting() || serviceRequest.isFilterChange() || serviceRequest.isOtherServiceRequest()) {
			return serviceRequestRepository.save(serviceRequest);
		}
		throw new ServiceRequestException();
	}
	
	//get all requests
	public List<ServiceRequest> getAllRequests(){
		return serviceRequestRepository.findAllByOrderBySubmittedAtDesc();
	}
	
	//mark customer as contacted
	public boolean markCustomerContacted(Long requestID, String username) {
		boolean result = false;
		Optional<ServiceRequest> request = serviceRequestRepository.findById(requestID);
		if (request.isPresent()) {
			ServiceRequest target = request.get();
			if (!target.isCustomerHasBeenContacted()) {
				target.markCustomerContacted(username);
				serviceRequestRepository.save(target);
				result = true;
			}
		}
		return result;
	}
	
}
