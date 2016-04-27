package com.metacodez.spring.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Data
@Entity
public class Site {
	@Id
	private long id;
	
	private String name;
	private String service;
	private double monthlyPrice;
	
	@ManyToOne
	@JoinColumn(name="CONTRACT_ID")
	@JsonIgnore
	private LineItemContract lineItemContract;
	
	public Site() {}
	public Site(String name, String service, double monthlyPrice) {
		super();
		this.name = name;
		this.service = service;
		this.monthlyPrice = monthlyPrice;
	}
	public String toString() {
		return "name:" + name + " service:" + service + " monthly:" + monthlyPrice;
	}
}
