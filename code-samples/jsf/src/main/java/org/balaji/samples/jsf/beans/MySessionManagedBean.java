package org.balaji.samples.jsf.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.annotation.PostConstruct;

@SuppressWarnings("restriction")
@ManagedBean(name="mySessionBean")
@SessionScoped
public class MySessionManagedBean {
	@PostConstruct
	public void init() {
		
	}
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void welcome() {
		System.out.println("I came here");
	}
	
}
