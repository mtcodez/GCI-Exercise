package com.metacodez.spring.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.metacodez.spring.entity.Contract;
import com.metacodez.spring.entity.Invoice;
import com.metacodez.spring.entity.LineItemContract;
import com.metacodez.spring.entity.ServiceOrder;
import com.metacodez.spring.entity.ServicesAgreement;
import com.metacodez.spring.service.ContractService;
import com.metacodez.spring.service.InvoiceService;
import com.metacodez.spring.service.SiteService;

@Component
@Path("/api/contracts")
public class ContractController {
	@Autowired
	ContractService contractService;
	@Autowired
	InvoiceService invoiceService;
	@Autowired
	SiteService siteService;
	
	@GET
	@Path("/lineitems")
	@Produces(MediaType.APPLICATION_JSON)
	public List<LineItemContract> getAllLineItemContracts() {
		return contractService.getAllLineItemContracts();
	}
	
	@GET
	@Path("/lineitems/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public LineItemContract getLineItemContracts(@PathParam("id") long id) {
		return contractService.getLineItemContract(id);
	}
	
	@POST
	@Path("/lineitems/")
	@Consumes(MediaType.APPLICATION_JSON)
	public void createLineItemContract(LineItemContract lineItemContract) {
		contractService.createLineItemContract(lineItemContract);
	}
	
	@POST
	@Path("/lineitems/list")
	@Consumes(MediaType.APPLICATION_JSON)
	public void createLineItemContract(List<LineItemContract> lineItemContracts) {
		contractService.createLineItemContracts(lineItemContracts);
	}
	
	@PUT
	@Path("/lineitems/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateLineItemContract(@PathParam("id") long id, LineItemContract lineItemContract) {
		if(contractService.updateLineItemContract(id, lineItemContract)) {
			return Response.status(406).entity("Requested contract does not exist.").build();
		} else {
			return Response.ok().build();
		}
	}
	
	@DELETE
	@Path("/lineitems/{id}")
	@Produces({MediaType.TEXT_HTML})
	public Response deleteLineItemContract(@PathParam("id") long id) {
		if (contractService.deleteLineItemContract(id)) {
			return Response.ok().build();
		} else {
			return Response.status(404).build();
		}
	}
	
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllContracts() {
		return Response.ok(contractService.getAllContracts()).build();
	}
	
	// invoice
	@GET
	@Path("/{id}/invoices")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getInvoice(@PathParam("id") long id) {
		return Response.ok(contractService.getAllInvoices(id)).build();
	}
	
	@POST
	@Path("/{id}/invoices")
	@Produces(MediaType.TEXT_HTML)
	public Response createInvoice(@PathParam("id") long id, Invoice invoice) {
		contractService.createInvoice(id, invoice);
		return Response.ok().build();
	}
	
	@PUT
	@Path("/{id}/invoices/{invId}")
	@Produces(MediaType.TEXT_HTML)
	public Response updateInvoice(@PathParam("id") long id, @PathParam("invId") long invId,
			Invoice invoice) {
		if(invoiceService.updateInvoice(id, invoice)) {
			return Response.status(406).entity("Requested contract does not exist.").build();
		} else {
			return Response.ok().build();
		}
	}
	
	@DELETE
	@Path("/{id}/invoices/{invId}")
	@Produces(MediaType.TEXT_HTML)
	public Response deleteInvoice(@PathParam("id") long id, @PathParam("invId") long invId) {
		if(invoiceService.deleteInvoice(invId)) {
			return Response.status(406).entity("Requested contract does not exist.").build();
		} else {
			return Response.ok().build();
		}
	}
	
	// SO
	@GET
	@Path("/serviceorders")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ServiceOrder> getAllServiceOrder() {
		return contractService.getAllServiceOrders();
	}
	
	@GET
	@Path("/serviceorders/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ServiceOrder getServiceOrders(@PathParam("id") long id) {
		return contractService.getServiceOrder(id);
	}
	
	@POST
	@Path("/serviceorders/")
	@Consumes(MediaType.APPLICATION_JSON)
	public void createServiceOrder(ServiceOrder serviceOrder) {
		contractService.createServiceOrder(serviceOrder);
	}
	
	@POST
	@Path("/serviceorders/list")
	@Consumes(MediaType.APPLICATION_JSON)
	public void createServiceOrder(List<ServiceOrder> serviceOrders) {
		contractService.createServiceOrders(serviceOrders);
	}
	
	@PUT
	@Path("/serviceorders/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateServiceOrder(@PathParam("id") long id, ServiceOrder serviceOrder) {
		if(contractService.updateServiceOrder(id, serviceOrder)) {
			return Response.status(406).entity("Requested contract does not exist.").build();
		} else {
			return Response.ok().build();
		}
	}
	
	@DELETE
	@Path("/serviceorders/{id}")
	@Produces({MediaType.TEXT_HTML})
	public Response deleteServiceOrder(@PathParam("id") long id) {
		if (contractService.deleteServiceOrder(id)) {
			return Response.ok().build();
		} else {
			return Response.status(404).build();
		}
	}
	// SA
	@GET
	@Path("/servicesagreements")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ServicesAgreement> getAllServicesAgreement() {
		return contractService.getAllServicesAgreements();
	}
	
	@GET
	@Path("/servicesagreements/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ServicesAgreement getServicesAgreement(@PathParam("id") long id) {
		return contractService.getServicesAgreement(id);
	}
	
	@POST
	@Path("/servicesagreements/")
	@Consumes(MediaType.APPLICATION_JSON)
	public void createServicesAgreement(ServicesAgreement servicesAgreement) {
		contractService.createServicesAgreement(servicesAgreement);
	}
	
	@POST
	@Path("/servicesagreements/list")
	@Consumes(MediaType.APPLICATION_JSON)
	public void createservicesAgreement(List<ServicesAgreement> servicesAgreements) {
		contractService.createServicesAgreements(servicesAgreements);
	}
	
	@PUT
	@Path("/servicesagreements/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateServicesAgreement(@PathParam("id") long id, ServicesAgreement servicesAgreement) {
		if(contractService.updateServicesAgreement(id, servicesAgreement)) {
			return Response.status(406).entity("Requested contract does not exist.").build();
		} else {
			return Response.ok().build();
		}
	}
	
	@DELETE
	@Path("/servicesagreements/{id}")
	@Produces({MediaType.TEXT_HTML})
	public Response deleteServicesAgreement(@PathParam("id") long id) {
		if (contractService.deleteServicesAgreement(id)) {
			return Response.ok().build();
		} else {
			return Response.status(404).build();
		}
	}
	
	@DELETE
	@Path("/{id}")
	@Produces({MediaType.TEXT_HTML})
	public Response deleteContract(@PathParam("id") long id) {
		if (contractService.deleteContract(id)) {
			return Response.ok().build();
		} else {
			return Response.status(404).build();
		}
	}
	
	@DELETE
	@Path("/sites/{id}")
	@Produces({MediaType.TEXT_HTML})
	public Response deleteSite(@PathParam("id") long id) {
		if (siteService.deleteSite(id)) {
			return Response.ok().build();
		} else {
			return Response.status(404).build();
		}
	}
	
	@DELETE
	@Path("/invoices/{id}")
	@Produces({MediaType.TEXT_HTML})
	public Response deleteInvoice(@PathParam("id") long id) {
		if (invoiceService.deleteInvoice(id)) {
			return Response.ok().build();
		} else {
			return Response.status(404).build();
		}
	}
	
	// All
	@GET
	@Path("/invoices")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllInvoices() {
		return Response.ok(invoiceService.getAll()).build();
	}
	
	@GET
	@Path("/sites")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllSites() {
		return Response.ok(siteService.getAll()).build();
	}
	
	@GET
	@Path("/warnings")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getWarnings() {
		int total = 0;
		for (Contract c : contractService.getAllContracts()) {
			if (contractService.isExpiring(c.getId())) {
				total++;
			}
		}
		return Response.ok(total).build();
	}
	@GET
	@Path("/total")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTotal() {
		return Response.ok(contractService.getMonthly()).build();
	}
	
}
