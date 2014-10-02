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
 * Model POJO for System Details
 * @author root
 *
 */
@Entity
@Table(name = "system_details")
public class SystemDetails extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String key;
	private String value;
	private Customer customer;
	private int id;

	/**
	 * Default constructor
	 */
	public SystemDetails() {

	}

	@Id
	@Column(name = "id", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter for key
	 * 
	 * @return
	 */
	@Column(name = "key1", nullable = false)
	public String getKey() {
		return key;
	}

	/**
	 * Setter for key
	 * 
	 * @param key
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * Getter for value
	 * 
	 * @return
	 */
	@Column(name = "val", nullable = false)
	public String getValue() {
		return value;
	}

	/**
	 * Setter for value
	 * 
	 * @param value
	 */
	public void setValue(String value) {
		this.value = value;
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

}
