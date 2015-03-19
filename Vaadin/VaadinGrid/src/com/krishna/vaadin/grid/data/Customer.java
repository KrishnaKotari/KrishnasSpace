package com.krishna.vaadin.grid.data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author Krishna
 *
 */
public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7645301805263106080L;

	public enum Gender {
		Male, Female;
	}

	private Integer customerID;

	private String customerName;

	private String city;

	private String pincode;

	private Date dob;

	private Gender gender;

	/**
	 * 
	 * @param customerID
	 * @param customerName
	 * @param dob
	 * @param city
	 * @param gender
	 */
	public Customer(Integer customerID, String customerName, Date dob,
			String city, Gender gender) {
		super();
		this.customerID = customerID;
		this.customerName = customerName;
		this.dob = dob;
		this.gender = gender;
		this.city = city;
	}

	/**
	 * Default Customer
	 */
	public Customer() {

	}

	/**
	 * 
	 * @param customerID
	 * @param customerName
	 * @param dob
	 * @param city
	 */
	public Customer(Integer customerID, String customerName, Date dob,
			String city) {
		this(customerID, customerName, dob, city, Gender.Male);
	}

	/**
	 * 
	 * @param customerName
	 * @param dob
	 * @param city
	 */
	public Customer(String customerName, Date dob, String city) {
		this(null, customerName, dob, city, Gender.Male);
	}

	/**
	 * @return the customerID
	 */
	public Integer getCustomerID() {
		return customerID;
	}

	/**
	 * @param customerID
	 *            the customerID to set
	 */
	public void setCustomerID(Integer customerID) {
		this.customerID = customerID;
	}

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName
	 *            the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @return the dob
	 */
	public Date getDob() {
		return dob;
	}

	/**
	 * @param dob
	 *            the dob to set
	 */
	public void setDob(Date dob) {
		this.dob = dob;
	}

	/**
	 * @return the gender
	 */
	public Gender getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

}
