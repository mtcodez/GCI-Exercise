package com.metacodez.spring.entity;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name="SERVICES_AGREEMENT")
public class ServicesAgreement extends Contract {
	public ServicesAgreement() {}
	public ServicesAgreement(LocalDate startDate, int duration, String projectSummary, String scopeOfWork) {
		super(startDate, duration, 0);
		this.projectSummary = projectSummary;
		this.scopeOfWork = scopeOfWork;
	}
	private String projectSummary;
	private String scopeOfWork;
}
