package com.metacodez.spring;

import java.sql.Date;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.metacodez.spring.entity.Invoice;
import com.metacodez.spring.entity.LineItemContract;
import com.metacodez.spring.entity.ServiceOrder;
import com.metacodez.spring.entity.Site;
import com.metacodez.spring.service.ContractService;
import com.metacodez.spring.service.InvoiceService;
import com.metacodez.spring.service.SiteService;

@SpringBootApplication
public class InheritancePracticeApplication {

	private static final Logger log = LoggerFactory.getLogger(InheritancePracticeApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(InheritancePracticeApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(ContractService contractService
			, InvoiceService invoiceService
			, SiteService siteService) {
		return (args) -> {
			LineItemContract contract = new LineItemContract(LocalDate.of(2015,4,20), 3);
			contractService.createLineItemContract(contract);
			Site site = new Site("site 1", "100 M", 245.0);
			Site site2 = new Site("site 2", "100 M", 300.0);
			long id = contractService.getAllLineItemContracts().iterator().next().getId();
			contractService.createSite(id, site);
			contractService.createSite(id, site2);
			for(Site s : siteService.getAll()) {
				log.info("" + s);
			}
			log.info("------");
			
			ServiceOrder so1 = new ServiceOrder(LocalDate.of(2016, 4, 1), 4, 30.5, 500);
			contractService.createServiceOrder(so1);
			long so1Id = contractService.getAllServiceOrders().iterator().next().getId();
			
			log.info("Size of contracts:" + contractService.getAllContracts().size());
			
			Invoice inv1 = new Invoice(495.2, LocalDate.of(2016,4,28), false);
			contractService.createInvoice(id, inv1);
			
			Invoice inv2 = new Invoice(900.34, LocalDate.of(2016,6,30), false);
			contractService.createInvoice(so1Id, inv2);
			
			log.info("num of invoices:" + invoiceService.getAll().size());
			
			log.info("lineitem: " + contractService.getAllInvoices(id));
			log.info("service order:" + contractService.getAllInvoices(so1Id));
			
			log.info("--------------------------");
			if (contractService.isExpiring(so1Id)) {
				log.info(so1Id + " is expiring..");
			} else {
				log.info(so1Id + " is NOT expiring..");
			}
			if (contractService.isExpiring(id)) {
				log.info(id + " is expiring..");
			} else {
				log.info(id + " is NOT expiring..");
			}
			
			log.info("-----------------------------");
			log.info("Montly: " + contractService.getMonthly());
			contractService.getAllContracts();
		};
	}
}
