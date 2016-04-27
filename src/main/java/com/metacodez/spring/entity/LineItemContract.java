package com.metacodez.spring.entity;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name="LINEITEM_CONTRACT")
//@AttributeOverride(name="id", column=@Column(name="LINEITEM_CONTRACT_ID"))
public class LineItemContract extends Contract {
	
	@OneToMany(mappedBy="lineItemContract", cascade=CascadeType.ALL,
	fetch=FetchType.EAGER,
	orphanRemoval=true)
	private List<Site> sites = new LinkedList<>();
	
	public LineItemContract(){}
	public LineItemContract(LocalDate startDate, int duration) {
		super(startDate, duration, 0);
	}
	
	public void addSite(Site site) {
		site.setLineItemContract(this);
		this.sites.add(site);		
		monthlyCost = monthlyCost + site.getMonthlyPrice();
	}
	
}
