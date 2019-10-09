package com.acc.xlp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Employee_Contact_Details", catalog = "postgres")
public class ContactDetails {

	private int id;
	private int employeeId;
	private String street;
	private String city;
	private String country;
	private String phoneNumber;
	private String internalEmail;
	private String externalEmail;
	
	@Id
	@SequenceGenerator(name="sequence_contact_id", sequenceName="sequence_contact_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_contact_id")
	@Column(name = "Id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "Employee_ID", unique = true, nullable = false, length = 10)
	public int getEmployeeId() {
		return employeeId;
	}
	
	@Column(name = "Street", nullable = false, length = 10)
	public String getStreet() {
		return street;
	}
	
	@Column(name = "City", nullable = false, length = 10)
	public String getCity() {
		return city;
	}
	
	@Column(name = "Country", nullable = false, length = 10)
	public String getCountry() {
		return country;
	}
	
	@Column(name = "Phone_Number", unique = true, nullable = false, length = 10)
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	@Column(name = "Internal_Email_ID", unique = true, nullable = false, length = 10)
	public String getInternalEmail() {
		return internalEmail;
	}
	
	@Column(name = "External_Email_ID", unique = true, nullable = false, length = 10)
	public String getExternalEmail() {
		return externalEmail;
	}
	
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public void setInternalEmail(String internalEmail) {
		this.internalEmail = internalEmail;
	}
	
	public void setExternalEmail(String externalEmail) {
		this.externalEmail = externalEmail;
	}
	
}
