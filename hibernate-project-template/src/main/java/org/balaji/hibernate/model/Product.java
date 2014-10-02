package org.balaji.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "products", /*catalog = "telemetry",*/ uniqueConstraints = { @UniqueConstraint(columnNames = "collector_id") })
public class Product extends BaseObject {	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String collectorId;
	private String name;

	/**
	 * Default constructor for Product
	 */
	public Product() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor that takes collectorId and name as arguments
	 * 
	 * @param collectorId
	 * @param name
	 */
	public Product(String collectorId, String name) {
		this.collectorId = collectorId;
		this.name = name;
	}

	/**
	 * Getter for id
	 * 
	 * @return
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	/**
	 * Setter for id
	 * 
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Getter for collector id
	 * 
	 * @return - collector id associated with the product
	 */
	@Column(name = "collector_id", unique = true, nullable = false, length = 255)
	public String getCollectorId() {
		return collectorId;
	}

	/**
	 * Setter for collector id
	 * 
	 * @param collectorId
	 */
	public void setCollectorId(String collectorId) {
		this.collectorId = collectorId;
	}

	/**
	 * Getter for name
	 * 
	 * @return - name of the product
	 */
	@Column(name = "name", nullable = false, length = 255)
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

	@Override
	public String toString() {
		return "Product [id=" + id + ", collectorId=" + collectorId + ", name="
				+ name + "]";
	}

}
