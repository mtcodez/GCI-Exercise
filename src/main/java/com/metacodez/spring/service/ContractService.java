package com.metacodez.spring.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metacodez.spring.entity.Contract;
import com.metacodez.spring.entity.Invoice;
import com.metacodez.spring.entity.LineItemContract;
import com.metacodez.spring.entity.ServiceOrder;
import com.metacodez.spring.entity.ServicesAgreement;
import com.metacodez.spring.entity.Site;
import com.metacodez.spring.repository.ContractRepository;
import com.metacodez.spring.repository.LineItemContractRepository;
import com.metacodez.spring.repository.ServiceOrderRepository;
import com.metacodez.spring.repository.ServicesAgreementRepository;
import com.metacodez.spring.repository.SiteRepository;

@Service
public class ContractService {
	@Autowired
	LineItemContractRepository lineItemContractRepository;
	@Autowired
	ServiceOrderRepository serviceOrderRepository;
	@Autowired
	ServicesAgreementRepository servicesAgreementRepository;
	@Autowired
	SiteRepository siteRepository;
	@Autowired
	ContractRepository contractRepository;
	
	// create
	@Transactional
	public void createLineItemContract(LineItemContract lineItemContract) {
		lineItemContractRepository.save(lineItemContract);
	}
	
	@Transactional
	public void createLineItemContracts(List<LineItemContract> lineItemContracts) {
		lineItemContractRepository.save(lineItemContracts);
	}
	
	@Transactional
	public void createServiceOrder(ServiceOrder serviceOrder) {
		serviceOrderRepository.save(serviceOrder);
	}
	
	@Transactional
	public void createServiceOrders(List<ServiceOrder> serviceOrder) {
		serviceOrderRepository.save(serviceOrder);
	}
	
	@Transactional
	public void createServicesAgreement(ServicesAgreement agreement) {
		servicesAgreementRepository.save(agreement);
	}
	
	@Transactional
	public void createServicesAgreements(List<ServicesAgreement> agreements) {
		servicesAgreementRepository.save(agreements);
	}
	
	@Transactional
	public void createInvoice(Long contractId, Invoice invoice) {
		contractRepository.findOne(contractId).addInvoice(invoice);
	}
	
	@Transactional
	public void createSite(Long lineItemId, Site site) {
		lineItemContractRepository.findOne(lineItemId).addSite(site);
	}
	
	
	// get
	@Transactional
	public List<Contract> getAllContracts() {
//		List<Contract> contracts =  contractRepository.findAll();
//		for (Contract c : contracts) {
//			Hibernate.initialize(c.getInvoices());
//		}
//		return contracts;
		return contractRepository.findAll();
	}
	
	@Transactional
	public Contract getContract(long id) {
		return contractRepository.findOne(id);
	}
	
	@Transactional
	public  List<LineItemContract> getAllLineItemContracts() {
		return lineItemContractRepository.findAll();
	}
	
	public LineItemContract getLineItemContract(long id) {
		return lineItemContractRepository.findOne(id);
	}
	
	@Transactional
	public List<ServiceOrder> getAllServiceOrders() {
		return serviceOrderRepository.findAll();
	}
	
	@Transactional
	public ServiceOrder getServiceOrder(long id) {
		return serviceOrderRepository.findOne(id);		
	}
	
	@Transactional
	public List<ServicesAgreement> getAllServicesAgreements() {
		return servicesAgreementRepository.findAll();
	}
	
	@Transactional
	public ServicesAgreement getServicesAgreement(long id) {
		return servicesAgreementRepository.findOne(id);
	}
	
	@Transactional
	public List<Site> getAllSites(long id) {
		List<Site> sites = lineItemContractRepository.findOne(id).getSites();		
		Hibernate.initialize(sites);		
		return sites;
	}
	
	@Transactional
	public List<Invoice> getAllInvoices(Long contractId) {
		List<Invoice> invoices = contractRepository.findOne(contractId).getInvoices();
		Hibernate.initialize(invoices);
		return invoices;
	}
	
	// update
	// LineItem
	@Transactional
	public boolean updateLineItemContract(long id, LineItemContract itemContract) {
		if(lineItemContractRepository.exists(id)) {
			lineItemContractRepository.delete(id);
			itemContract.setId(id);
			lineItemContractRepository.save(itemContract);
			return true;
		} else {
			return false;
		}
	}
	// SO
	public boolean updateServiceOrder(long id, ServiceOrder serviceOrder) {
		if(serviceOrderRepository.exists(id)) {
			serviceOrderRepository.delete(id);
			serviceOrder.setId(id);
			serviceOrderRepository.save(serviceOrder);
			return true;
		} else {
			return false;
		}
	}
	// SA
	public boolean updateServicesAgreement(long id, ServicesAgreement servicesAgreement) {
		if(servicesAgreementRepository.exists(id)) {
			servicesAgreementRepository.delete(id);
			servicesAgreement.setId(id);
			servicesAgreementRepository.save(servicesAgreement);
			return true;
		} else {
			return false;
		}
	}
	
	// delete
	@Transactional
	public boolean deleteContract(long id) {
		if(contractRepository.exists(id)) {
			contractRepository.delete(id);
			return true;
		} else {
			return false;
		}
	}
	
	@Transactional
	public boolean deleteLineItemContract(long id) {
		if(lineItemContractRepository.exists(id)) {
			lineItemContractRepository.delete(id);
			return true;
		} else {
			return false;
		}
	}
	
	@Transactional
	public boolean deleteServiceOrder(long id) {
		if(serviceOrderRepository.exists(id)) {
			serviceOrderRepository.delete(id);
			return true;
		} else {
			return false;
		}
	}
	
	@Transactional
	public boolean deleteServicesAgreement(long id) {
		if(servicesAgreementRepository.exists(id)) {
			servicesAgreementRepository.delete(id);
			return true;
		} else {
			return false;
		}
	}
	
	// utilities
	public boolean isExpiring(Long contractId) {
		LocalDate startDate = contractRepository.findOne(contractId).getStartDate();
		int duration = contractRepository.findOne(contractId).getDuration();
		// start + duration ? now
		if (startDate.plusMonths(duration).compareTo(LocalDate.now()) > 0 ) {
			if (startDate.plusMonths(duration).compareTo(LocalDate.now().plusDays(90)) < 0) {
				// expiring
				return true;
			}
			return false;
		} else {
			// expired
			return true;
		}
	}
	
	public double getMonthly() {
		double total = 0;
		for(Contract c : contractRepository.findAll()) {
			total += c.getMonthlyCost();
		}
		return total;
	}
}
