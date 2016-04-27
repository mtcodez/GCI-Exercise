package com.metacodez.spring.entity;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Invoice {
	@Id
	@Column(name="INVOICE_ID")
	@GeneratedValue
	private long id;
	
	private double total;
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate dueDate; // if paid, then it's paid date.
	private boolean isPaid;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="CONTRACT_ID")
	private Contract contract;
	
	public Invoice(){}
	public Invoice(double total, LocalDate dueDate, boolean isPaid) {
		this.total = total;
		this.dueDate = dueDate;
		this.isPaid = isPaid;
	}
	public String toString() {
		return "[total:" + total + " date:" + dueDate.toString() + " isPaid:" + isPaid + "]";
	}
}
