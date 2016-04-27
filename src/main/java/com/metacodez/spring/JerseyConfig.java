package com.metacodez.spring;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.metacodez.spring.controller.ContractController;

@Configuration
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(ContractController.class);
	}
	
}
