package com.metacodez.spring.entity;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name="SERVICE_ORDER")
public class ServiceOrder extends Contract {
	public ServiceOrder() {}
	public ServiceOrder(LocalDate startDate, int duration, double monthlyCost, double nonRecurringCosts) {
		super(startDate, duration, monthlyCost);
		this.nonRecurringCosts = nonRecurringCosts;
	}
	private double nonRecurringCosts;
}
