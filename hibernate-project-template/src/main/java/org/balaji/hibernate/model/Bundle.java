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
@Table(name = "bundle")
public class Bundle extends BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fileName;
	private long processTime;
	private long uploadTime;
	private String uploadId;
	private Customer custId;
	private String logInsightUrl;
	private int id;
	private String supportBundle;

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
	 * Getter for file name
	 * 
	 * @return
	 */
	@Column(name = "file_name", nullable = false)
	public String getFileName() {
		return fileName;
	}

	/**
	 * Setter for file name
	 * 
	 * @param fileName
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Getter for process_time
	 * 
	 * @return
	 */
	@Column(name = "process_time", nullable = false)
	public long getProcessTime() {
		return processTime;
	}

	/**
	 * Setter for process time
	 * 
	 * @param processTime
	 */
	public void setProcessTime(long processTime) {
		this.processTime = processTime;
	}

	/**
	 * Getter for upload time
	 * 
	 * @return
	 */
	@Column(name = "upload_time")
	public long getUploadTime() {
		return uploadTime;
	}

	/**
	 * Setter for upload time
	 * 
	 * @param uploadTime
	 */
	public void setUploadTime(long uploadTime) {
		this.uploadTime = uploadTime;
	}

	/**
	 * Getter for upload id
	 * 
	 * @return
	 */
	@Column(name = "upload_id")
	public String getUploadId() {
		return uploadId;
	}

	/**
	 * Setter for upload id
	 * 
	 * @param uploadId
	 */
	public void setUploadId(String uploadId) {
		this.uploadId = uploadId;
	}

	/**
	 * Getter for Customer
	 * 
	 * @return
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cust_id", nullable = false)
	public Customer getCustId() {
		return custId;
	}

	/**
	 * Setter for Customer
	 * 
	 * @param custId
	 */
	public void setCustId(Customer custId) {
		this.custId = custId;
	}

	/**
	 * Getter for log insight URL
	 * 
	 * @return
	 */
	@Column(name = "loginsight_url")
	public String getLogInsightUrl() {
		return logInsightUrl;
	}

	/**
	 * Setter for log insight url
	 * 
	 * @param logInsightUrl
	 */
	public void setLogInsightUrl(String logInsightUrl) {
		this.logInsightUrl = logInsightUrl;
	}
	
	/**
	 * Getter for support bundle
	 * @return
	 */
	@Column(name="sb_name")
	public String getSupportBundle() {
		return supportBundle;
	}

	/**
	 * Setter for support bundle
	 * @param supportBundle
	 */
	public void setSupportBundle(String supportBundle) {
		this.supportBundle = supportBundle;
	}
}
