package com.example.JOBSHOP.JOBSHOP.Application.qualification;

import com.example.JOBSHOP.JOBSHOP.Application.Application;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.qualification.jobSeekerQualification;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class applicationQualification {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="jobSeekerQualification_id")
	private jobSeekerQualification jobSeekerQualification;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonBackReference
	@JoinColumn(name="Application_id")
	private Application application;

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public jobSeekerQualification getJobSeekerQualification() {
		return jobSeekerQualification;
	}

	public void setJobSeekerQualification(jobSeekerQualification jobSeekerQualification) {
		this.jobSeekerQualification = jobSeekerQualification;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}
	
	
	
}
