package com.metacodez.spring.entity;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Contract {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name="CONTRACT_ID")
	protected long id;
	
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	protected LocalDate startDate;
	protected int duration;
	
	@OneToMany(mappedBy="contract",cascade=CascadeType.ALL,
			fetch=FetchType.LAZY,
			orphanRemoval=true)
	@JsonIgnore
	protected List<Invoice> invoices = new LinkedList<>();
	
	public void addInvoice(Invoice invoice) {
		invoice.setContract(this);
		this.invoices.add(invoice);
	}
	
	protected double monthlyCost;
	public Contract() {}
	public Contract(LocalDate startDate, int duration, double monthlyCost) {
		this.startDate = startDate;
		this.duration = duration;
		this.monthlyCost = monthlyCost;
	}
	
}
