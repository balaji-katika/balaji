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
 * Model for feature usage
 * 
 * @author root
 *
 */
@Entity
@Table(name = "feature_usage")
public class FeatureUsage extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String featureName;
	private int count;
	private String url;
	private int retCode;
	private Customer customer;
	private int id;

	/**
	 * Default constructor
	 */
	public FeatureUsage() {

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
	 * Getter for feature name
	 * 
	 * @return
	 */
	@Column(name = "feature_name", nullable = false)
	public String getFeatureName() {
		return featureName;
	}

	/**
	 * Setter for feature name
	 * 
	 * @param featureName
	 */
	public void setFeatureName(String featureName) {
		this.featureName = featureName;
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

	/**
	 * Getter for URL
	 * 
	 * @return
	 */
	@Column(name = "url", nullable = false)
	public String getUrl() {
		return url;
	}

	/**
	 * Setter for url
	 * 
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Getter for return code
	 * 
	 * @return
	 */
	@Column(name = "ret_code")
	public int getRetCode() {
		return retCode;
	}

	/**
	 * Setter for return code
	 * 
	 * @param retCode
	 */
	public void setRetCode(int retCode) {
		this.retCode = retCode;
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
