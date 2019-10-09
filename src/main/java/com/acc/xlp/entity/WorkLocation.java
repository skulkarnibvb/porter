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
@Table(name = "Work_Location", catalog = "postgres")
public class WorkLocation {

	private int id;
	private String locationDetails;
	private String createdOn;
	private String modifiedOn;
	private Employee employee; 
	
	@Id
	@SequenceGenerator(name="sequence_location_id", sequenceName="sequence_location_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_location_id")
	@Column(name = "Id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "Location_Details", unique = true, nullable = false, length = 10)
	public String getLocationDetails() {
		return locationDetails;
	}
	
	public void setLocationDetails(String locationDetails) {
		this.locationDetails = locationDetails;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public String getModifiedOn() {
		return modifiedOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public void setModifiedOn(String modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	@OneToOne(mappedBy = "locationId")
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
