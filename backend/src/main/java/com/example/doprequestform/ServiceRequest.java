package com.example.doprequestform;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "service_requests")
public class ServiceRequest {
	
	//required attributes
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "request_id", nullable = false, unique = true)
	private Long requestID;
	@Column(name = "company_name", nullable = false, unique = false)
	private String companyName;
	@Column(name = "requester_first_name", nullable = false, unique = false)
	private String requesterFirstName;
	@Column(name = "requester_last_name", nullable = false, unique = false)
	private String requesterLastName;
	@Column(name = "service_address", nullable = false, unique = false)
	private String serviceAddress;
	@Column(name = "desired_service_date", nullable = false, unique = false)
	private LocalDate desiredServiceDate;
	@Column(name = "number_of_units", nullable = false, unique = false)
	private int numberOfUnits;
	@Column (name = "customer_phone", nullable = false)
	private String customerPhone;
	@Column (name = "customer_email", nullable = false)
	private String customerEmail;
	
	//required attributes service selections
	private boolean dopTesting;
	private boolean filterChange;
	private boolean otherServiceRequest;
	
	//optional attribute fields
	private String unitDetails;
	private String additionalInformation;
	
	//backend items
	private LocalDateTime submittedAt = LocalDateTime.now();
	private boolean customerHasBeenContacted = false;
	private String contactedBy;
	
	//constructors
	@JsonCreator
	public ServiceRequest() {
	}
	public ServiceRequest(String companyName, String firstName, String lastName, String address, LocalDate desiredDate, int units, boolean dopTesting, boolean filterChange, boolean other, String unitDetails, String additionalInfo, String customerPhone, String customerEmail) {
		this.companyName = companyName;
		this.requesterFirstName = firstName;
		this.requesterLastName = lastName;
		this.serviceAddress = address;
		this.desiredServiceDate = desiredDate;
		this.numberOfUnits = units;
		this.dopTesting = dopTesting;
		this.filterChange = filterChange;
		this.otherServiceRequest = other;
		this.unitDetails = unitDetails;
		this.additionalInformation = additionalInfo;
		this.customerPhone = customerPhone;
		this.customerEmail = customerEmail;
	}
	
	//getters
	public Long getRequestID() {
		return this.requestID;
	}
	public String getCompanyName() {
		return this.companyName;
	}
	public String getRequesterFirstName() {
		return this.requesterFirstName;
	}
	public String getRequesterLastName() {
		return this.requesterLastName;
	}
	public String getServiceAddress() {
		return this.serviceAddress;
	}
	public LocalDate getDesiredServiceDate() {
		return this.desiredServiceDate;
	}
	public int getNumberOfUnits() {
		return this.numberOfUnits;
	}
	public boolean isDopTesting() {
		return this.dopTesting;
	}
	public boolean isFilterChange() {
		return this.filterChange;
	}
	public boolean isOtherServiceRequest() {
		return this.otherServiceRequest;
	}
	public String getUnitDetails() {
		return this.unitDetails;
	}
	public String getAdditionalInformation() {
		return this.additionalInformation;
	}
	public LocalDateTime getSubmittedAt() {
		return this.submittedAt;
	}
	public boolean isCustomerHasBeenContacted() {
		return this.customerHasBeenContacted;
	}
	public String getContactedBy() {
		return this.contactedBy;
	}
	public String getCustomerPhone() {
		return this.customerPhone;
	}
	public String getCustomerEmail() {
		return this.customerEmail;
	}
	
	//setters
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public void setRequesterFirstName(String requesterFirstName) {
		this.requesterFirstName = requesterFirstName;
	}
	public void setRequesterLastName(String requesterLastName) {
		this.requesterLastName = requesterLastName;
	}
	public void setServiceAddress(String serviceAddress) {
		this.serviceAddress = serviceAddress;
	}
	public void setDesiredServiceDate(LocalDate desiredServiceDate) {
		this.desiredServiceDate = desiredServiceDate;
	}
	public void setNumberOfUnits(int numberOfUnits) {
		this.numberOfUnits = numberOfUnits;
	}
	public void setDopTesting(boolean dopTesting) {
		this.dopTesting = dopTesting;
	}
	public void setFilterChange(boolean filterChange) {
		this.filterChange = filterChange;
	}
	public void setOtherServiceRequest(boolean otherServiceRequest) {
		this.otherServiceRequest = otherServiceRequest;
	}
	public void setUnitDetails(String unitDetails) {
		this.unitDetails = unitDetails;
	}
	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}
	public void markCustomerContacted(String employeeUsername) {
		if (!this.customerHasBeenContacted) {
			this.customerHasBeenContacted = true;
			this.contactedBy = employeeUsername;
		}
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
}
