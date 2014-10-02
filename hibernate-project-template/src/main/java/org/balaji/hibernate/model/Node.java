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

@Entity
@Table(name = "nodes")
public class Node extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int internalId;
	private String name;
	private String details;
	private Customer customer;
	private int id;

	/**
	 * Default constructor
	 */
	public Node() {

	}

	@Id
	@Column(name = "id", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}

	/**
	 * Setter for id
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter for name
	 * 
	 * @return
	 */
	@Column(name = "name", nullable = false)
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
	 * Getter for internal_id
	 * 
	 * @return
	 */
	@Column(name = "internal_id", nullable = false)
	public int getInternalId() {
		return internalId;
	}

	/**
	 * Setter for internal_id
	 * 
	 * @param internal_id
	 */
	public void setInternalId(int internal_id) {
		this.internalId = internal_id;
	}
	
	@Override
	public String toString() {
		return "Node [internalId=" + internalId + ", name=" + name
				+ ", details=" + details + ", customer=" + customer + ", id="
				+ id + "]";
	}
}
