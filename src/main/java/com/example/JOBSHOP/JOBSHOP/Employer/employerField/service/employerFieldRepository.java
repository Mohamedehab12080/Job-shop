package com.example.JOBSHOP.JOBSHOP.Employer.employerField.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.JOBSHOP.JOBSHOP.Employer.employerField.employerField;

@Repository
public interface employerFieldRepository extends /*baseRepo<employerField,Long>*/ JpaRepository<employerField, Long> { 
   
	
	List<employerField> findByEmployerId(Long id);
	
//	@Query("select f.id from employerField f where f.companyField.id=:id")
//	Long findByCompanyFieldId(@Param("id") Long id);
	
	@Query("SELECT f from employerField f where f.companyField.id=:id")
	List<employerField> findByCompanyFieldId(@Param("id") Long id);
	
	employerField findByEmployerIdAndCompanyFieldFieldId(Long employerId,Long fieldId);
}
