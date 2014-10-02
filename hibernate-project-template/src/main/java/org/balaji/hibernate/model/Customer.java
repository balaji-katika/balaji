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
 * Model object for Customer
 * @author root
 *
 */
@Entity
@Table(name="customers")
public class Customer extends BaseObject {

	private int id;
	private Product product;
	private String instanceId;
	private String name;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Default constructor for Customer
	 */
	public Customer() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructor with fields as arguments
	 * 
	 * @param id
	 * @param prId
	 * @param instanceId
	 * @param name
	 */
	public Customer(Product prId, String instanceId, String name) {
		super();		
		this.product = prId;
		this.instanceId = instanceId;
		this.name = name;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id", unique=true, nullable=false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "pr_id", nullable=false)
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product prId) {
		this.product = prId;
	}

	@Column(name="instance_id", nullable=false)
	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	@Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
