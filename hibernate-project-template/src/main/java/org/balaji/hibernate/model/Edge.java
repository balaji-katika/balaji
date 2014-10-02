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
 * Model for Edge
 * 
 * @author root
 *
 */
@Entity
@Table(name = "topology")
public class Edge extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Node node_from;
	private Node node_to;
	private int id;

	/**
	 * Default constructor
	 */
	public Edge() {

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
	 * Getter for From
	 * 
	 * @return
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "node_from", nullable = false)
	public Node getFrom() {
		return node_from;
	}

	/**
	 * Setter for From node
	 * 
	 * @param from
	 */
	public void setFrom(Node from) {
		this.node_from = from;
	}

	/**
	 * Getter for To node
	 * 
	 * @return
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "node_to", nullable = false)
	public Node getTo() {
		return node_to;
	}

	/**
	 * Setter for To node
	 * 
	 * @param to
	 */
	public void setTo(Node to) {
		this.node_to = to;
	}

	@Override
	public String toString() {
		return "Edge [from=" + node_from + ", to=" + node_to + "]";
	}

}
