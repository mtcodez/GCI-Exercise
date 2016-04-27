package com.metacodez.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metacodez.spring.entity.LineItemContract;

@Repository
public interface LineItemContractRepository extends JpaRepository<LineItemContract, Long>{
	
}
