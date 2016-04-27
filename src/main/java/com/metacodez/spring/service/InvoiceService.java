package com.metacodez.spring.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metacodez.spring.entity.Invoice;
import com.metacodez.spring.entity.ServicesAgreement;
import com.metacodez.spring.repository.InvoiceRepository;

@Service
public class InvoiceService {
	@Autowired
	InvoiceRepository invoiceRepository;
	
	@Transactional
	public List<Invoice> getAll() {
		return invoiceRepository.findAll();
	}
	
	@Transactional
	public boolean deleteInvoice(long id) {
		if (invoiceRepository.exists(id)) {
			invoiceRepository.delete(id);
			return true;
		} else {
			return false;
		}
	}
	
	@Transactional
	public boolean updateInvoice(long id, Invoice invoice) {
		if(invoiceRepository.exists(id)) {
			invoiceRepository.delete(id);
			invoice.setId(id);
			invoiceRepository.save(invoice);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isPastDue(long id) {
		Invoice inv = invoiceRepository.findOne(id);
		if (inv.getDueDate().compareTo(LocalDate.now()) < 0) {
			return true;
		} else {
			return false;
		}
		
	}
		
}
