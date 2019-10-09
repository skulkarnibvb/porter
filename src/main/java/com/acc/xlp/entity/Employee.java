package com.acc.xlp.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Employee", catalog = "postgres", uniqueConstraints = { @UniqueConstraint(columnNames = "Person_ID"),
		@UniqueConstraint(columnNames = "First_Name"), @UniqueConstraint(columnNames = "Last_Name"),
		@UniqueConstraint(columnNames = "Level"), @UniqueConstraint(columnNames = "WBSE") })
public class Employee {

	private int employeeId;
	private String personId;
	private String firstName;
	private String lastName;
	private int level;
	private String wbs;
	private ContactDetails contactDetails;
	private int locationId;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	@Column(name = "Person_ID", unique = true, nullable = false, length = 10)
	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	@Column(name = "First_Name", unique = true, nullable = false, length = 10)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "Last_Name", unique = true, nullable = false, length = 10)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "Level", unique = true, nullable = false, length = 10)
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@Column(name = "WBSE", unique = true, nullable = false, length = 10)
	public String getWbs() {
		return wbs;
	}

	public void setWbs(String wbs) {
		this.wbs = wbs;
	}

	@OneToOne(targetEntity = ContactDetails.class)
	@JoinColumn(name = "employeeId")
	public ContactDetails getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(ContactDetails contactDetails) {
		this.contactDetails = contactDetails;
	}

	@OneToOne(targetEntity = WorkLocation.class)
	@JoinColumn(name = "id")
	public int getLocationId() {
		return locationId;
	}
	
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
}
