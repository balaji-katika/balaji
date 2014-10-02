package org.balaji.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Model object for license
 * @author root
 *
 */
@Entity
@Table(name = "license")
public class License extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String licenseKey;
	private String name;
	private String type;
	private String details;
	private Customer customer;
	private String edition;

	/**
	 * Getter for id
	 * 
	 * @return
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	public int getId() {
		return id;
	}

	/**
	 * Setter for id
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter for license key
	 * 
	 * @return
	 */
	@Column(name = "lic_key", nullable = false)
	public String getLicenseKey() {
		return licenseKey;
	}

	/**
	 * Setter for license key
	 * 
	 * @param licenseKey
	 */
	public void setLicenseKey(String licenseKey) {
		this.licenseKey = licenseKey;
	}

	/**
	 * Getter for name
	 * 
	 * @return
	 */
	@Column(name = "name")
	public String getName() {
		return name;
	}

	/**
	 * Setter for name
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for type
	 * 
	 * @return
	 */
	@Column(name = "type")
	public String getType() {
		return type;
	}

	/**
	 * Setter for type
	 * 
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Getter for details
	 * 
	 * @return
	 */
	@Column(name = "details")
	public String getDetails() {
		return details;
	}

	/**
	 * Setter for details
	 * 
	 * @param details
	 */
	public void setDetails(String details) {
		this.details = details;
	}

	/**
	 * Getter for customer
	 * 
	 * @return
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cust_id", nullable = false)
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * Setter for customer
	 * 
	 * @param customer
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * Getter for edition
	 */
	@Column(name="edition")
	public String getEdition() {
		// TODO Auto-generated method stub
		return edition;
	}
	
	/**
	 * Setter for edition
	 * @param edition
	 */
	public void setEdition(String edition) {
		this.edition = edition;
	}
}
