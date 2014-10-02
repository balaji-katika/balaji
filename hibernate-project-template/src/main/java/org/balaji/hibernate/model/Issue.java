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
 * Issue
 * 
 * @author root
 *
 */
@Entity
@Table(name = "issues")
public class Issue extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int count;
	private String name;
	private Customer customer;

	/**
	 * Default constructor
	 */
	public Issue() {

	}

	/**
	 * Getter for id
	 * 
	 * @return
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	public int getId() {
		return this.id;
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
	 * Getter for customer
	 * 
	 * @return
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cust_id", nullable = false)
	public Customer getCustomer() {
		return this.customer;
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
	 * Getter for count
	 * 
	 * @return
	 */
	@Column(name = "count", nullable = false)
	public int getCount() {
		return count;
	}

	/**
	 * Setter for count
	 * 
	 * @param count
	 */
	public void setCount(int count) {
		this.count = count;
	}

}
