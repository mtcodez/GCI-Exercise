package com.metacodez.spring.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metacodez.spring.entity.Invoice;
import com.metacodez.spring.entity.Site;
import com.metacodez.spring.repository.SiteRepository;

@Service
public class SiteService {
	@Autowired
	SiteRepository siteRepository;
	
	@Transactional
	public List<Site> getAll() {
		return siteRepository.findAll();
	}
	
	@Transactional
	public boolean updateSite(long id, Site site) {
		if(siteRepository.exists(id)) {
			siteRepository.delete(id);
			site.setId(id);
			siteRepository.save(site);
			return true;
		} else {
			return false;
		}
	}
	
	@Transactional
	public boolean deleteSite(Long siteId) {
		if (siteRepository.exists(siteId)) {
			siteRepository.delete(siteId);
			return true;
		} else {
			return false;
		}
	}
}
