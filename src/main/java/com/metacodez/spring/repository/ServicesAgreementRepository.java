package com.metacodez.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metacodez.spring.entity.ServicesAgreement;

@Repository
public interface ServicesAgreementRepository extends JpaRepository<ServicesAgreement, Long>{

}
