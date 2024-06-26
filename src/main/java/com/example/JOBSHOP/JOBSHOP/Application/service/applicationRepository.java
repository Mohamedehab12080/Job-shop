package com.example.JOBSHOP.JOBSHOP.Application.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.JOBSHOP.JOBSHOP.Application.Application;
import com.example.JOBSHOP.JOBSHOP.Base.baseRepo;
@Repository
public interface applicationRepository extends /*baseRepo<Application, Long>*/ JpaRepository<Application,Long>{

//	@Query("SELECT a FROM Application a " +
//				"JOIN Post p ON p.id=a.post.id "+
//	           "WHERE a.Post.id = :postId and a.skills IN :postSkills " +
//	           "ORDER BY SIZE(a.Skills) - SIZE((SELECT p.additionalSkills FROM Post p WHERE p.id = :postId)) ASC")
//	    List<Application> findBestMatchingApplicationsForPost(Long postId,@Param("postSkills") List<String> postSkills);
//	
	List<Application> findByJobSeekerIdOrderByCreatedDateDesc(Long id);
	
	@Query("select a from Application a where a.Post.id = :id")
	List<Application> findByPostId(@Param("id") Long id);
//	
//	@Query("select a.applicationQualifications,a.apllicationSkills from Application a where a.Post.id=:id")
//	List<Object[]> findSkillsAndQualificationsByPostId(Long id);

	 	@Query("SELECT a FROM Application a WHERE a.Post.id = ?1 AND a.jobSeeker.id = ?2")
	    Application findByPostIdAndJobSeekerId(Long postId, Long jobSeekerId);
	
	 	@Modifying
	 	@Query("UPDATE Application a SET a.statusCode = 'Accepted' WHERE a.id = :id")
	 	void acceptApplication(@Param("id") Long id);
	 	
	 	@Modifying
	 	@Query("UPDATE Application a SET a.statusCode = 'Rejected' WHERE a.id = :id")
	 	void rejectApplication(@Param("id") Long id);
}
