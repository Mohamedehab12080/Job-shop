package com.example.JOBSHOP.JOBSHOP.jobSeeker.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JOBSHOP.JOBSHOP.Application.Application;
import com.example.JOBSHOP.JOBSHOP.Application.DTO.applicationDTO;
import com.example.JOBSHOP.JOBSHOP.Application.DTO.applicationMapper;
import com.example.JOBSHOP.JOBSHOP.Application.qualification.applicationQualification;
import com.example.JOBSHOP.JOBSHOP.Application.qualification.DTO.applicationQualificationDTO;
import com.example.JOBSHOP.JOBSHOP.Application.qualification.DTO.applicationQualificationMapper;
import com.example.JOBSHOP.JOBSHOP.Application.qualification.service.applicaitonQualificationServiceImpl;
import com.example.JOBSHOP.JOBSHOP.Application.qualification.service.applicationQualificationInterface;
import com.example.JOBSHOP.JOBSHOP.Application.service.applicationServiceImpl;
import com.example.JOBSHOP.JOBSHOP.Application.service.applicationServiceInerface;
import com.example.JOBSHOP.JOBSHOP.Application.skill.applicationSkill;
import com.example.JOBSHOP.JOBSHOP.Application.skill.DTO.applicationSkillDTO;
import com.example.JOBSHOP.JOBSHOP.Application.skill.DTO.applicationSkillMapper;
import com.example.JOBSHOP.JOBSHOP.Application.skill.service.applicationSkillServiceInterface;
import com.example.JOBSHOP.JOBSHOP.Post.Post;
import com.example.JOBSHOP.JOBSHOP.Post.DTO.postDTO;
import com.example.JOBSHOP.JOBSHOP.Post.DTO.postMapper;
import com.example.JOBSHOP.JOBSHOP.Post.postField.DTO.postFieldDTO;
import com.example.JOBSHOP.JOBSHOP.Post.service.postRepository;
import com.example.JOBSHOP.JOBSHOP.User.userProfile.follow.service.followService;
import com.example.JOBSHOP.JOBSHOP.degrees.qualification;
import com.example.JOBSHOP.JOBSHOP.degrees.service.qualificationServiceInterface;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.jobSeeker;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.controller.skillsAndQualificationsRequest;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.profile.jobSeekerProfile;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.profile.jobSeekerProfileService;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.qualification.jobSeekerQualification;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.qualification.DTO.jobSeekerQualificationDTO;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.qualification.DTO.jobSeekerQualificationMapper;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.qualification.service.jobSeekerQualificationServiceInterface;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.requests.saveSkillsRequest;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.skill.jobSeekerSkill;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.skill.DTO.jobSeekerSkillDTO;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.skill.DTO.jobSeekerSkillMapper;
import com.example.JOBSHOP.JOBSHOP.jobSeeker.skill.service.jobSeekerSkillServiceInterface;
import com.example.JOBSHOP.JOBSHOP.skills.Skill;
import com.example.JOBSHOP.JOBSHOP.skills.service.skillServiceInterface;

import jakarta.transaction.Transactional;

@Service
public class jobSeekerService implements jobSeekerServiceInterface{

	
	 @Autowired
	 private jobSeekerRepository jobSeekerRepository;
	 
	 @Autowired 
	 private jobSeekerProfileService jobSeekerProfileService;
	 
	 @Autowired
	 private followService followService;
	 
	 @Autowired
	 private applicationServiceImpl applicationService;
	 
	 @Autowired
	 private skillServiceInterface skillServiceI;
	 
	 @Autowired
	 private qualificationServiceInterface qualificationServiceI;
	 
	 @Autowired
	 private applicationSkillServiceInterface applicationSkillServiceI;
	 
	 @Autowired
	 private applicationServiceInerface applicationServiceI;
	 @Autowired
	 private jobSeekerQualificationServiceInterface jobSeekerQualificationServiceI;
	 @Autowired
	 private jobSeekerSkillServiceInterface jobSeekerSkillServiceI;
	 @Autowired
	 private postRepository postService;
	 @Autowired
	 private applicationQualificationInterface applicationQualificationI;
//	 public List<User> getJobSeekerFollowers(jobSeeker jobSeeker)
//	 {
//		 return followSerivice.getFollowersById(jobSeeker);
//	 }
//	 public List<User> getJobSeekerFollowings(jobSeeker jobSeeker)
//	 {
//		 return followSerivice.getFollowingById(jobSeeker);
//	 }
	 
	 
	 		
	    public jobSeeker getReferenceById(Long id)
		{
			return jobSeekerRepository.getReferenceById(id);
		}
	   
		public List<jobSeeker> findAll()
		{
			return jobSeekerRepository.findAll();
		}
		
		public jobSeeker findById(Long id)
		{
			Optional<jobSeeker> finded=jobSeekerRepository.findById(id);
			if(finded.isPresent())
			{
				return finded.get();
			}else 
			{
				return null;
			}
			
		}
		
//		public void updateEntityStatus(Application t)
//		{
//			jobSeekerRepository.updateEntity(t.getId(),t.getStatusCode()); 
//		}
		@Transactional
		@Override
		public jobSeeker update(Long jobSeekerId,jobSeeker user)
		{
			jobSeeker oldJobSeeker=findById(jobSeekerId);
			if(user.getUserName()!=null)
			{
				oldJobSeeker.setUserName(user.getUserName());
			}
			
			if(user.getAddress() !=null)
			{
				oldJobSeeker.setAddress(user.getAddress());
			}
			
			if(!user.getContacts().isEmpty())
			{
				oldJobSeeker.setContacts(user.getContacts());
			}
			
			if(user.getPicture()!=null)
			{
				oldJobSeeker.setPicture(user.getPicture());
			}
			
			if(user.getEducation()!=null)
			{
				oldJobSeeker.setEducation(user.getEducation());
			}
			
			if(user.getBirthDate()!=null)
			{
				oldJobSeeker.setBirthDate(user.getBirthDate());
			}
			
			if(user.getEmploymentState()!=null)
			{
				oldJobSeeker.setEmploymentState(user.getEmploymentState());
			}
			
			if(user.getExperience()!=null)
			{
				oldJobSeeker.setExperience(user.getExperience());
			}
			
			return insert(oldJobSeeker);
			
		}
		@Transactional
		public List<jobSeeker> insertAll(List<jobSeeker> entity)
		{
			return jobSeekerRepository.saveAll(entity);
		}
		
		public void deleteById(Long id)
		{
			jobSeeker t=getReferenceById(id);
			if(t!=null)
			{
				jobSeekerRepository.deleteById(id);
			}
		}
	 /**
	 * 
	 * @author BOB
	 * @Function Insert jobSeeker With his Profile.
	 */
	 public jobSeeker insert(jobSeeker jobSeeker)
	 {
		if(jobSeeker!=null)
		{
			 jobSeekerProfile jobSeekerProfile=new jobSeekerProfile();
			 jobSeekerProfile.setJobSeeker(jobSeeker); 
			 jobSeeker jobSeekersaved=jobSeekerRepository.save(jobSeeker);
			if(jobSeekersaved!=null)
			{
				 jobSeekerProfileService.insert(jobSeekerProfile);
				 return jobSeekersaved;
			}else 
			{
				return null;
			}
		}else 
		{
			return null;
		}
	 }
	
	 /**
	 * 
	 * @author BOB
	 * @Function find all jobSeeker's submitted applications by jobSeeker_id Order by LIFO.
	 */
	 public List<Application> findAllApplicationsForJobSeeker(Long id)
	 {
		return applicationService.findByJobSeekerIdOrderByCreatedDate(id);
	 }
	 
	 
	 private postDTO convertPostToDto(Post post)
	 {
		 return postMapper.mapPostTODTO(post);
	 }
	 
	 private boolean checkForApply(Long jobSekerId,Post post)
	 {
		 Application app=applicationServiceI.findByJobSeekerIdAndPostId(jobSekerId,post.getId());
		 if(app!=null)
		 {
			 return false;
		 }else 
		 {
			 return true;
		 }
	 }
	 
	 
	 /**
	 * 
	 * @author BOB
	 * @Function  jobSeeker will select skills he want to apply with them 
	 * and if he don't have the skill it will automatically 
	 * added to his skills in jobSeekerSkill table
	 */
	 @Override
	 public applicationReturnedSkillsAndQualifications applyForPost(applicationDTO app)
	 {
		 Application apps=applicationMapper.mapDTOToApplicationForInsertApplicaiton(app);
		 Optional<Post> post=postService.findById(apps.getPost().getId()); 
		 List<applicationQualification> appQualListForInsert=new ArrayList<applicationQualification>();
		 List<applicationSkill>appSkillListForInsert=new ArrayList<applicationSkill>();
		List<String> skillsToSend=new ArrayList<String>();
		List<String> qualificationsToSend=new ArrayList<String>();
		 
		 for(applicationSkillDTO appSkill:app.getApplicationSkills())
		 {
			 jobSeekerSkill jobSeekerSkill=jobSeekerSkillServiceI.findById(appSkill.getJobSeekerSkillId()).get();
			 skillsToSend.add(jobSeekerSkill.getSkill().getSkillName());
		 }

		 for(applicationQualificationDTO appQual:app.getApplicationQualifications())
		 {
			 jobSeekerQualification qual=jobSeekerQualificationServiceI.findById(appQual.getJobSeekerQualificationId()).get();
			 qualificationsToSend.add(qual.getQualification().getQualificationName());
		 }
		 
		 if(post.isPresent())
		 {
			 postDTO postDto=convertPostToDto(post.get());
			 List<String> postSkills=new ArrayList<String>();
			 List<String> postQualifications=new ArrayList<String>();
				
					for(String skill:postDto.getPostField().getSkills())
					{
						postSkills.add(skill);
					}
					
					for(String qual:postDto.getPostField().getQualifications())
					{
						postQualifications.add(qual);
					}
					
			 if(applicationService
					 .returningTheRemainedSkills(postSkills,skillsToSend)
					 .equals("No")
					 && applicationService
					 .returningRemainedQualifications(postQualifications, qualificationsToSend).equals("No"))
			 {
				 return applicationService.getApplicationReturnedSkillsAndQualifications(); // return the remained skills without apply 
			 }else if(applicationService
					 .returningRemainedQualifications
					 (postQualifications, qualificationsToSend)
					 .equals("Yes"))
			 {
				 Application insertedApp=applicationService.insert(apps);// apply
				 for(applicationQualification appQual:
					 	 app.getApplicationQualifications()
						 .stream()
						 .map(this::convertDTOToApplicationQualification)
						 .collect(Collectors.toList()))
				 {
					 appQual.setApplication(insertedApp);
					 appQualListForInsert.add(appQual);
				 }
				 System.out.println("Application Qualification For insert List Size : "+appQualListForInsert.size());
				 applicationQualificationI.insertAll(appQualListForInsert);
				 
				for(applicationSkill appSkill:
					app.getApplicationSkills()
					.stream()
					.map(this::convertDTOToApplicationSkill)
					.collect(Collectors.toList()))
				{
					appSkill.setApplication(apps);
					appSkillListForInsert.add(appSkill);
				}
				applicationSkillServiceI.insertAll(appSkillListForInsert);
				 System.out.println("Application Skills For insert List Size : "+appSkillListForInsert.size());

				 return applicationService.getApplicationReturnedSkillsAndQualifications(); // return the remained skills if it contains skills
				 
			 }else 
			 {
				 return null;
			 }
		 }else 
		 {
			 return null;
		 }
		 
	 }
	 
	 private applicationQualification convertDTOToApplicationQualification(applicationQualificationDTO dto)
	 {
		 return applicationQualificationMapper.mapDTOToApplicationQualification(dto);
	 }
	 private applicationSkill convertDTOToApplicationSkill(applicationSkillDTO dto)
	 {
		 return applicationSkillMapper.mapDTOToApplicationSkill(dto);
	 }
	 private postDTO mapPostToDTO(Post post)
	 {
		 return postMapper.mapPostTODTO(post);
	 }
	 
//	 public List<String> findSkillsById(Long id)
//	 {
//		 return jobSeekerRepository.findSkillsById(id);
//	 }
	 /**
	 * 
	 * @author BOB
	 * @Function jobSeeker update his picture
	 */
	 public jobSeeker insertPicture(Long id,byte[] picture)
	 {
		 try {
			Optional<jobSeeker> jobSeekerUpdate=jobSeekerRepository.findById(id);
			 if(jobSeekerUpdate.isPresent())
			 {
				 System.out.println("Job Seeker :" +jobSeekerUpdate.get().getEmail());
				 jobSeeker jobSeekerForUpdate=jobSeekerUpdate.get();
				 jobSeekerForUpdate.setPicture(picture);
				 return jobSeekerRepository.save(jobSeekerForUpdate);
			 }else  
			 {
				 return null;
			 }
		} catch (Exception e) {
			return null;
		}
	 }
	 
//	 public jobSeekerProfile insertProfile(jobSeekerProfile jobSeekerProfile)
//	 {
//		 return jobSeekerProfileRepository.save(jobSeekerProfile);
//	 }
	 
	 /**
	 * 
	 * @author BOB
	 * @Function find jobSeeker profile by jobSeeker_Id 
	 */
	 public jobSeekerProfile findByJobSeekerId(Long id)
	 {
		return jobSeekerProfileService.findByJobSeekerId(id).get();
	 } 
	 
	 private jobSeekerSkillDTO convertJobSeekerSkillToDto(jobSeekerSkill jobSeekerSkill)
	 {
		 return jobSeekerSkillMapper.mapJobSeekerSkillToDTO(jobSeekerSkill);
	 }
	 private jobSeekerQualificationDTO convertJobSeekerQualificationToDto(jobSeekerQualification jobSeekerQualification)
	 {
		 return jobSeekerQualificationMapper.mapJobSeekerQualificationToDTO(jobSeekerQualification);
	 }
	 public jobSeekerProfile findJobSeekerProfileWithjobSeekerID(Long jobSeekerId)
	 {
		return jobSeekerProfileService.findByJobSeeker_id(jobSeekerId).get();
	 } 
	 
//	 /**
//		 * 
//		 * @Author BOB
//		 * @return List <Post> that contains job seeker skills order by descending
//		 */
//	 @Override
//	 public List<Post> getPostsWithSkillsOnPublic(Long jobSeekerId) {
//		    // Fetch job seeker and their skills
//		    jobSeeker jobSeeker = findById(jobSeekerId);
//		    List<jobSeekerSkillDTO> jobSeekerSkillDtoList = 
//		    		jobSeekerSkillServiceI
//		    		.findByJobSeekerId(jobSeeker.getId())
//		            .stream()
//		            .map(this::convertJobSeekerSkillToDto)
//		            .collect(Collectors.toList());
//
//		    // Extract skill names
//		    Set<String> jobSeekerSkills = jobSeekerSkillDtoList.stream()
//		            .map(jobSeekerSkillDTO::getSkillName)
//		            .collect(Collectors.toSet());
//
//		    // Fetch posts based on job seeker skills
//		    Set<Post> posts = jobSeekerSkills.stream()
//		            .flatMap(skill -> postService.findByTitle(skill).stream())
//		            .collect(Collectors.toSet());
//
//		    if (posts.isEmpty()) {
//		        return Collections.emptyList();
//		    }
//
//		    // Calculate and sort post scores
//		    List<Post> sortedPosts = posts.stream()
//		            .map(post -> {
//		                postDTO postDto = convertPostToDto(post);
//		                Set<String> postSkills = postDto.getPostFieldsDto().stream()
//		                        .flatMap(pf -> pf.getSkills().stream())
//		                        .collect(Collectors.toSet());
//
//		                int score = calculateScore(new ArrayList<String>(postSkills),new ArrayList<String>( jobSeekerSkills));
//		                return new postScore(post, score);
//		            })
//		            .sorted(Comparator.comparingInt(postScore::getScore).reversed())
//		            .map(postScore::getPost)
//		            .collect(Collectors.toList());
//
//		    return sortedPosts;
//		}
	 
		 @Override
	 public List<postDTO> getPostsWithSkillsOnPublic(Long jobSeekerId) {
	     // Fetch job seeker and their skills
	     jobSeeker jobSeeker = findById(jobSeekerId);
	     if(jobSeeker!=null)
	     {
	    	  Set<String> jobSeekerSkills = 
	 	    		 jobSeekerSkillServiceI.findByJobSeekerId(jobSeeker.getId())
	 	             .stream().map(this::convertJobSeekerSkillToDto)
	 	             .map(jobSeekerSkillDTO::getSkillName)
	 	             .collect(Collectors.toSet());
				System.out.println("Job Seeker Skills : "+jobSeekerSkills);
				
				Set<String> jobSeekerQualifications=
						jobSeekerQualificationServiceI.findByJobSeekerId(jobSeeker.getId())
						.stream().map(this::convertJobSeekerQualificationToDto)
						.map(jobSeekerQualificationDTO::getQualificationName)
						.collect(Collectors.toSet());
				System.out.println("Job Seeker Qualifications : "+jobSeekerQualifications);
				
				
				Set<postDTO> posts =jobSeekerSkills.stream()
						.flatMap(skill -> postService.findByTitle(skill).stream())
						.filter(post -> checkForApply(jobSeekerId, post))
						.map(this::convertPostToDto)
						.collect(Collectors.toSet());
				
				if (posts.isEmpty()) {
				return Collections.emptyList();
				}
				
				// Calculate and sort post scores
				List<postDTO> sortedPosts = posts.stream()				       
						.map(post -> {
				            Set<String> postSkills = post.getPostField().getSkills().stream()
				                    .collect(Collectors.toSet());
				            Set<String> postQualifications = post.getPostField().getQualifications().stream()
				                    .collect(Collectors.toSet());

				            int score = calculateScore(
				            		new ArrayList<String>(postQualifications),
				            		new ArrayList<>(postSkills),
				            		new ArrayList<String>(jobSeekerQualifications),
				            		new ArrayList<>(jobSeekerSkills));
				            
				            List<String>remainedSkills=applicationService.returningRemainedSkillsForListOfPosts(new ArrayList<String>(postSkills), new ArrayList<String>(jobSeekerSkills));
				            List<String> remainedQualifications=applicationService.returningRemainedQualificationsForPostList(new ArrayList<String>(postQualifications), new ArrayList<String>(jobSeekerQualifications));
				            List<String> matchedSkills=new ArrayList<String>();
				            for(String matchedSkill:postSkills)
				            {
				            	if(!remainedSkills.contains(matchedSkill))
				            	{
				            		matchedSkills.add(matchedSkill);
				            	}
				            }
				            post.setMatchedSkills(matchedSkills);
				            List<String> matchedQualifications=new ArrayList<String>();
				            for(String matchedQualification:postQualifications)
				            {
				            	if(!remainedQualifications.contains(matchedQualification))
				            	{
				            		matchedQualifications.add(matchedQualification);
				            	}
				            }
				            post.setMatchedQulifications(matchedQualifications);
				            post.setRemainedSkills(remainedSkills);
			                post.setRemainedQualifications(remainedQualifications);
				            if(!matchedSkills.isEmpty())
				            {
				            	if((matchedSkills.size()+matchedQualifications.size())<((postSkills.size()+postQualifications.size())/2))
					            {
				            		  post.setState(0);
							            System.out.println("Post Skills : "+postSkills+" ::: jobSeeker Skills : "+jobSeekerSkills);
						                System.out.println("REMAINED SKILLS FOR POST LIST : "+remainedSkills);
//							            System.out.println("Remained Skills From last Method : "+applicationService.remainedSkills);
					            }else 
					            {
					            	  post.setState(1);   
							            System.out.println("Post Skills : "+postSkills+" ::: jobSeeker Skills : "+jobSeekerSkills);
						                System.out.println("REMAINED SKILLS FOR POST LIST : "+remainedSkills);
//							            System.out.println("Remained Skills From last Method : "+applicationService.remainedSkills);
					            }
				            }
				            System.out.println("Matched Skills : "+matchedSkills);
				            return new postScore(post, score);
				        })
						
						.sorted(
								Comparator
								.comparingInt((postScore) -> {
		                    if (((postScore) postScore)
		                    		.getPost()
		                    		.getRemainedSkills()
		                    		.isEmpty()
		                    		&& 
		                    		((postScore) postScore)
		                    		.getPost()
		                    		.getRemainedQualifications()
		                    		.isEmpty()) {
		                    	
		                        return ((postScore) postScore)
		                        		.getScore();
		                    } else 
		                    {
		                    	return ((postScore) postScore)
		                    			.getPost()
		                    			.getRemainedSkills()
		                    			.size()
		                    			+((postScore) postScore)
		                    			.getPost()
		                    			.getRemainedQualifications()
		                    			.size();
		                    }
		                }).reversed()) // Reverse to get highest score first
		                .map(postScore::getPost) // Accessing the postDTO directly from postScore
		                .collect(Collectors.toList());

				
				// Map to store unique post IDs
				Map<Long, postDTO> uniquePostsMap = new LinkedHashMap<>();
				sortedPosts.forEach(post -> uniquePostsMap.put(post.getId(), post));
				// Convert the map back to a list
				List<postDTO> uniquePostsList = new ArrayList<>(uniquePostsMap.values());
				
				return uniquePostsList;
	     }else 
	     {
	    	 return Collections.emptyList();
	     }
	 }
	 
	 
//	 @Override
//	 public List<postDTO> getPostsWithSkillsOnPublic(Long jobSeekerId) {
//	     jobSeeker jobSeeker = findById(jobSeekerId);
//	     if (jobSeeker == null) {
//	         return Collections.emptyList();
//	     }
//
//	     Set<String> jobSeekerSkills = jobSeekerSkillServiceI.findByJobSeekerId(jobSeekerId)
//	             .stream().map(this::convertJobSeekerSkillToDto) 
//	             .map(jobSeekerSkillDTO::getSkillName)
//	             .collect(Collectors.toSet());
//
//	     Set<String> jobSeekerQualifications = jobSeekerQualificationServiceI.findByJobSeekerId(jobSeekerId)
//	             .stream().map(this::convertJobSeekerQualificationToDto)
//	             .map(jobSeekerQualificationDTO::getQualificationName)
//	             .collect(Collectors.toSet());
//
//	     List<postDTO> posts = jobSeekerSkills.stream()
//	             .flatMap(skill -> postService.findByTitle(skill).stream())
//	             .map(this::convertPostToDto)
//	             .collect(Collectors.toList());
//
//	     Map<Long, postDTO> uniquePostsMap = new LinkedHashMap<>();
//
//	     for (postDTO post : posts) {
//	         Set<String> postSkills = new HashSet<>(post.getPostField().getSkills());
//	         Set<String> postQualifications = new HashSet<>(post.getPostField().getQualifications());
//
//	         List<String> remainedSkills = applicationService.returningRemainedSkillsForListOfPosts(new ArrayList<>(postSkills), new ArrayList<>(jobSeekerSkills));
//	         List<String> remainedQualifications = applicationService.returningRemainedQualificationsForPostList(new ArrayList<>(postQualifications), new ArrayList<>(jobSeekerQualifications));
//
//	         List<String> matchedSkills = new ArrayList<>(postSkills);
//	         matchedSkills.retainAll(jobSeekerSkills);
//
//	         List<String> matchedQualifications = new ArrayList<>(postQualifications);
//	         matchedQualifications.retainAll(jobSeekerQualifications);
//
//	         if (!matchedSkills.isEmpty()) {
//	             if ((matchedSkills.size() + matchedQualifications.size()) < ((postSkills.size() + postQualifications.size()) / 2)) {
//	                 post.setState(0);
//	             } else {
//	                 post.setState(1);
//	             }
//	         }
//
//	         post.setMatchedSkills(new ArrayList<>(matchedSkills));
//	         post.setMatchedQulifications(new ArrayList<>(matchedQualifications));
//	         post.setRemainedSkills(new ArrayList<>(remainedSkills));
//	         post.setRemainedQualifications(new ArrayList<>(remainedQualifications));
//
//	         uniquePostsMap.put(post.getId(), post);
//	     }
//
//	     List<postDTO> uniquePostsList = new ArrayList<>(uniquePostsMap.values());
//
//	     uniquePostsList.sort(Comparator.comparingInt(post -> {
//	         int score = calculateScore(post.getRemainedQualifications(), post.getRemainedSkills(), new ArrayList<>(jobSeekerQualifications), new ArrayList<>(jobSeekerSkills));
//	         return -score;
//	     }));
//
//	     return uniquePostsList;
//	 }
	  
	/*
	 *  @Override
	 public List<Post> getPostsWithSkillsOnPublic(Long jobSeekerId) {
	     // Fetch job seeker and their skills
	     jobSeeker jobSeeker = findById(jobSeekerId);
	     if(jobSeeker!=null)
	     {
	    	  List<jobSeekerSkillDTO> jobSeekerSkillDtoList =jobSeekerSkillServiceI
						.findByJobSeekerId(jobSeeker.getId())
						.stream()
						.map(this::convertJobSeekerSkillToDto)
						.collect(Collectors.toList());

				// Extract skill names
				Set<String> jobSeekerSkills = jobSeekerSkillDtoList.stream()
				.map(jobSeekerSkillDTO::getSkillName)
				.collect(Collectors.toSet());
				
				// Fetch posts based on job seeker skills
				Set<Post> posts = jobSeekerSkills.stream()
				.flatMap(skill -> postService.findByTitle(skill).stream())
				.collect(Collectors.toSet());
				
				if (posts.isEmpty()) {
				return Collections.emptyList();
				}
				
				// Calculate and sort post scores
				List<Post> sortedPosts = posts.stream()
				.map(post -> {
				postDTO postDto = convertPostToDto(post);
				Set<String> postSkills = postDto.getPostField().getSkills().stream()
				.collect(Collectors.toSet());
				
				int score = calculateScore(new ArrayList<String>(postSkills), new ArrayList<String>(jobSeekerSkills));
				return new postScore(post, score);
				})
				.sorted(Comparator.comparingInt(postScore::getScore).reversed())
				.map(postScore::getPost)
				.collect(Collectors.toList());
				
				return sortedPosts;

	     }else 
	     {
	    	 return Collections.emptyList();
	     }
	 }

	 */
//		 /**
//			 * 
//			 * @Author BOB
//			 * @return List <Post> that contains job seeker skills order by descending
//			 */
//			public List<Post> getPostsWithSkillsOnPublic(Long jobSeekerId) {
//		        
//				jobSeeker jobSeeker=findById(jobSeekerId);
//				List<User> followings=followService.getFollowingById(jobSeeker); // order by the followings of post employer or company 
//				
//				List<jobSeekerSkill> jobSeekerSkills=jobSeekerSkillServiceI.findByJobSeekerId(jobSeeker.getId());
//				List<jobSeekerSkillDTO>jobSeekerSkillDtoList=jobSeekerSkills.stream().map(this::convertJobSeekerSkillToDto).collect(Collectors.toList());
//				List<String> jobSeekerSkillsInStrings=new ArrayList<String>();
//				for(jobSeekerSkillDTO jobSeekerSkill:jobSeekerSkillDtoList)
//				{
//					jobSeekerSkillsInStrings.add(jobSeekerSkill.getSkillName());
//				} 
//				
//				Set <Post> posts = new HashSet<Post>();
//				List<postScore> postScores=new ArrayList<postScore>();
//				System.out.println("jobSeeker Skills : "+jobSeekerSkills);
//				
//				for (String skill:jobSeekerSkillsInStrings) {
//					posts.addAll(postService.findByTitle(skill));// Find by the title contains any word of jobSeekerSkills
//				} 
//				
//				if(posts!=null)
//				{
//					System.out.println("size : "+posts.size());
//					for (Post post:posts) {
//						postDTO postDto=convertPostToDto(post);
//						 List<String> postSkills=new ArrayList<String>();
//							for(postFieldDTO postF:postDto.getPostFieldsDto())
//							{
//								for(String skill:postF.getSkills())
//								{
//									if(postSkills.contains(skill))
//									{
//										
//									}else 
//									{
//										postSkills.add(skill);
//										System.out.println("skill : "+skill);
//									}
//								}	
//							}
////					    Set<String> postSkillsSet=new HashSet<String>(postSkills);
////					    postSkills=new ArrayList<String>(postSkillsSet);
//						int score =calculateScore(postSkills, jobSeekerSkillsInStrings);
//						System.out.println("calculated Score : "+score);
//						System.out.println("Post skills : "+postSkills);
//						postScores.add(new postScore(post, score));
//					}
//			        
//			        // Sort applications based on score in descending order
//			        Collections.sort(postScores, Comparator.comparingInt(postScore::getScore).reversed());
//			        
//			        // Extract applications from ApplicationScore objects
//			        List<Post> sortedPosts = new ArrayList<>();
//			        for (postScore postScore : postScores) {
//			        	sortedPosts.add(postScore.getPost());
//			        }
//			        
//			        return sortedPosts;
//				}else 
//				{
//					return null;
//				}
//				
//		    }
//			
			/**
			 * 
			 * @param postSkillSet
			 * @param applicationSkills
			 * @return calculate the score of matching between list of skills and another list of skills  
			 */
			private static int calculateScore(List<String> postQualifications,List<String> postSkillSet,List<String>applicationQualifications, List<String> applicationSkills) {   
				Map<String, Integer> postSkillCount = new HashMap<>();
			    Map<String, Integer> applicationSkillCount = new HashMap<>();
			    
			    Map<String, Integer> postQualificationCount = new HashMap<>();
			    Map<String, Integer> applicationsQualificationCount = new HashMap<>();
			    
			    // Count occurrences of each skill in post skills
			    for (String skill : postSkillSet) {
			        postSkillCount.put(skill.toLowerCase(), postSkillCount.getOrDefault(skill.toLowerCase(), 0) + 1);
			    }
			    
			    for (String qual : postQualifications) {
			        postQualificationCount.put(qual.toLowerCase(), postQualificationCount.getOrDefault(qual.toLowerCase(), 0) + 1);
			    }
			    
			    for (String qual : applicationQualifications) {
			    	applicationsQualificationCount.put(qual.toLowerCase(), applicationsQualificationCount.getOrDefault(qual.toLowerCase(), 0) + 1);
			    }
	
			    // Count occurrences of each skill in application skills
			    for (String skill : applicationSkills) {
			        applicationSkillCount.put(skill.toLowerCase(), applicationSkillCount.getOrDefault(skill.toLowerCase(), 0) + 1);
			    }
	
			    int score = 0;
			    // Calculate score based on the minimum occurrences of each skill
			    for (Map.Entry<String, Integer> entry : applicationSkillCount.entrySet()) {
			        String skill = entry.getKey();
			        int applicationCount = entry.getValue();
			        int postCount = postSkillCount.getOrDefault(skill, 0);
			        score += Math.min(applicationCount, postCount);
			    }
			    for (Map.Entry<String, Integer> entry : applicationsQualificationCount.entrySet()) {
			        String skill = entry.getKey();
			        int applicationCount = entry.getValue();
			        int postCount = postQualificationCount.getOrDefault(skill, 0);
			        score += Math.min(applicationCount, postCount);
			    }
			    System.out.println("Score Of Qualifications With Skills : "+score);
			    return score;
			}
	
		 /**
			 * 
			 * @Author BOB
			 * @Function Helper Class for store the score of each Application
			 */
			private static class postScore {
		        private postDTO post;
		        private int score;
		        
		        public postScore(postDTO post, int score) {
		            this.post = post;
		            this.score = score;
		        }
		        
		        public postDTO getPost() {
		            return post;
		        }
		        
		        public int getScore() {
		            return score;
		        }
		    }

		@Override
		public Optional<jobSeeker> findByEmail(String email) {
			
			return jobSeekerRepository.findByEmail(email);
		}

		@Transactional
		@Override
		public String insertJobSeekerSkillsAndQualificationsOptimized(saveSkillsRequest skillsRequest) {
		    try {
		        // Process skills
		        processSkills(skillsRequest.getUserId(), new HashSet<String>(skillsRequest.getSkills()));

		        //Process qualifications
		        processQualifications(skillsRequest.getUserId(),new HashSet<String>(skillsRequest.getQualifications()));

		        return "inserted";
		    } catch (Exception e) {
		        return "Failed to insert JobSeeker skills and qualifications.";
		    }
		}

		
		/**
		 * 
		 * @param userId
		 * @param skillDTOs
		 * @return void processing of skills and inserting skills within the db
		 */
		private void processSkills(Long userId, Set<String> skillDTOs) {
		 try {
			    List<jobSeekerSkillDTO>jobSeekerSkillsToInsert=new ArrayList<jobSeekerSkillDTO>();
		 
			    for (String skillDTO : skillDTOs) {
			        jobSeekerSkillDTO jobSeekerSkillToInsert = new jobSeekerSkillDTO();
			    	System.out.println("Skill before subString : "+skillDTO);
			    	jobSeekerSkillToInsert.setSkillName(skillDTO);
			    	System.out.println("Skill after subString : "+jobSeekerSkillToInsert.getSkillName());
			    	System.out.println("skillDegree after subString : "+jobSeekerSkillToInsert.getSkillDegree());
			    	Skill skill = skillServiceI.findByName(jobSeekerSkillToInsert.getSkillName());
			        Skill insertedSkill=skill;
			        if (skill == null) {
			            skill = new Skill();
			            skill.setSkillName(jobSeekerSkillToInsert.getSkillName());
			           insertedSkill=skillServiceI.insertForJobSeekerOperation(skill);
			          
			        }
//			        Skill skillObject=new Skill();
//			    	Long skillID = skillServiceI.findIdByName(jobSeekerSkillToInsert.getSkillName());
//			        System.out.println("The ID of The skill : "+jobSeekerSkillToInsert.getSkillName()+" "+skillID);		    	
//			    	skillObject.setId(skillID);
//			    	jobSeekerSkillToInsert.setSkill(skillObject);
			        if(insertedSkill!=null)
			        {
//					    skillObject.setId(insertedSkill.getId());
				        jobSeekerSkillToInsert.setSkill(insertedSkill); // Also here.
			    	}
			        
//			        jobSeeker jobSeekerObject=new jobSeeker();
//			        jobSeekerObject.setId(userId);
			        jobSeekerSkillToInsert.setJobSeekerId(userId); // initialize jobSeeker object with jobSeeker id that at the dto
//			        jobSeekerSkillToInsert.setSkillDegree(skillDTO.getSkillDegree());
			        jobSeekerSkillsToInsert.add(jobSeekerSkillToInsert);
			    }
//			    if(!skillsToInsert.isEmpty())
//			    {
//				    skillServiceI.insertAll(skillsToInsert);
//			    }
			    
			    List <jobSeekerSkill> jobSeekerSkillsList=jobSeekerSkillsToInsert.stream().map(this::convertFromDtoToJobSeekerSkill).collect(Collectors.toList());
			    if(!jobSeekerSkillsList.isEmpty())
			    {
			        jobSeekerSkillServiceI.insertAll(jobSeekerSkillsList);
			    }
		} catch (Exception e) {
			System.out.println("Errorrrrrrrr : "+e);
		}
		}

		
		private void processQualifications(Long userId,Set<String> qualificationDTOs) {
			try {
			List<qualification> qualificationsToInsert = new ArrayList<>();
		    List<jobSeekerQualificationDTO>jobSeekerQualificationsToInsert=new ArrayList<jobSeekerQualificationDTO>();
	 
		    for (String qualificationDto : qualificationDTOs) {
		        jobSeekerQualificationDTO jobSeekerQualificationToInsert = new jobSeekerQualificationDTO();
		    	System.out.println("Skill before subString : "+qualificationDto);
		    	jobSeekerQualificationToInsert.setQualificationName(qualificationDto);
		    	System.out.println("Skill after subString : "+jobSeekerQualificationToInsert.getQualificationName());
		    	System.out.println("skillDegree after subString : "+jobSeekerQualificationToInsert.getQualificationDegree());
		    	qualification qualification = qualificationServiceI.findByName(jobSeekerQualificationToInsert.getQualificationName());
		    	qualification insertedQualification=qualification; //initialized if qualification exists for insert
		        if (qualification == null) {
		        	qualification = new qualification();
		        	qualification.setQualificationName(jobSeekerQualificationToInsert.getQualificationName());
		        	insertedQualification=qualificationServiceI.insert(qualification);
		        }
		        
		        if(insertedQualification!=null)
		        {
//				    skillObject.setId(insertedSkill.getId());
		        	jobSeekerQualificationToInsert.setQualification(insertedQualification); // Also here.
		    	}
		        
		        jobSeekerQualificationToInsert.setJobSeekerId(userId); // initialize jobSeeker object with jobSeeker id that at the dto
//		        jobSeekerSkillToInsert.setSkillDegree(skillDTO.getSkillDegree());
		        jobSeekerQualificationsToInsert.add(jobSeekerQualificationToInsert);
		    }
//		    if(!skillsToInsert.isEmpty())
//		    {
//			    skillServiceI.insertAll(skillsToInsert);
//		    }
		    
		    List <jobSeekerQualification> jobSeekerQualificationList=jobSeekerQualificationsToInsert.stream().map(this::convertFromDtoToJobSeekerQualification).collect(Collectors.toList());
		    if(!jobSeekerQualificationList.isEmpty())
		    {
		        jobSeekerQualificationServiceI.insertAll(jobSeekerQualificationList);
		    }
		} catch (Exception e) {
			System.out.println("Errorrrrrrrr : "+e);
		}
		}
		

		private jobSeekerSkill convertFromDtoToJobSeekerSkill(jobSeekerSkillDTO dto)
		{
			return jobSeekerSkillMapper.mapDtoToJobSeekerSkillForInsert(dto);
		}
		
		private jobSeekerQualification convertFromDtoToJobSeekerQualification(jobSeekerQualificationDTO dto)
		{
			return jobSeekerQualificationMapper.mapDtoToJobSeekerQualificationForInsert(dto);
		}
		
		@Transactional
		@Override
		public String insertJobSeekerSkillsAndQualifications(saveSkillsRequest skillsRequest) {
			
//			String theReturn = null;
//			try {
//				List<Skill> skillsToInsert =new ArrayList<Skill>();
//				Map<String,Skill> existingSkills=new HashMap<String, Skill>();
//				Set<jobSeekerSkillDTO> newSkills=new HashSet<jobSeekerSkillDTO>(skillsRequest.getJobSeekerSkillDtoList());
//				
//				for(jobSeekerSkillDTO jobSeekerSkill:newSkills)
//				{
//			    	Skill skillSearch= skillServiceI.findByName(jobSeekerSkill.getSkillName());
//			    	if(skillSearch!=null)
//			    	{
//			    		existingSkills.put(skillSearch.getSkillName(), skillSearch); //putting the skill name and skill object for the existing skills hashMap
//			    	}else 
//			    	{
//			    		Skill skillObject=new Skill();
//			    		skillObject.setSkillName(jobSeekerSkill.getSkillName());
//			    		skillsToInsert.add(skillObject);
//			    	}
//				}
//				
//				skillServiceI.insertAll(skillsToInsert);
//				
////				List<Skill> allSkills=new ArrayList<Skill>(existingSkills.values());
////				allSkills.addAll(skillsToInsert);
//				
//				List<jobSeekerSkill>jobSeekerSkills=new ArrayList<jobSeekerSkill>();
//				for(jobSeekerSkillDTO skill:newSkills)
//				{
//					jobSeekerSkill jobSeekerSkillToInsert=new jobSeekerSkill();
//					jobSeekerSkillToInsert.setSkill(skill.getSkill());
//					jobSeekerSkillToInsert.setJobSeeker(skill.getJobSeeker());
//					jobSeekerSkillToInsert.setSkillDegree(skill.getSkillDegree());
//					jobSeekerSkills.add(jobSeekerSkillToInsert);
//				}
//				jobSeekerSkillServiceI.insertAll(jobSeekerSkills);
//				theReturn="jobSeeker skills inserted.";
//			} catch (Exception e) {
//				return theReturn;
//				
//			}
//			try {
//				List<qualification> qualificationsToInsert =new ArrayList<qualification>();
//				Map<String,qualification> existingQualification=new HashMap<String, qualification>();
//				Set<jobSeekerQualificationDTO> newQualification=new HashSet<jobSeekerQualificationDTO>(skillsRequest.getQualificationDtoList());
//				
//				for(jobSeekerQualificationDTO jobSeekerQual:newQualification)
//				{
//			    	qualification qualificationSearch= qualificationServiceI.findByName(jobSeekerQual.getQualificationName());
//			    	if(qualificationSearch!=null)
//			    	{
//			    		existingQualification.put(qualificationSearch.getQualificationName(), qualificationSearch); //putting the skill name and skill object for the existing skills hashMap
//			    	}else 
//			    	{
//			    		qualification qualificationObject=new qualification();
//			    		qualificationObject.setQualificationName(qualificationSearch.getQualificationName());
//			    		qualificationsToInsert.add(qualificationObject);
//			    	}
//				}
//				
//				qualificationServiceI.insertAll(qualificationsToInsert);
//				
////				List<Skill> allSkills=new ArrayList<Skill>(existingSkills.values());
////				allSkills.addAll(skillsToInsert);
//				
//				List<jobSeekerQualification>jobSeekerQualificationsToInsert=new ArrayList<jobSeekerQualification>();
//				for(jobSeekerQualificationDTO qualification:newQualification)
//				{
//					jobSeekerQualification jobSeekerQualificationToInsert=new jobSeekerQualification();
//					jobSeekerQualificationToInsert.setQualification(qualification.getQualification());
//					jobSeekerQualificationToInsert.setJobSeeker(qualification.getJobSeeker());
//					jobSeekerQualificationToInsert.setQualificationDegree(qualification.getQualificationDegree());
//					jobSeekerQualificationsToInsert.add(jobSeekerQualificationToInsert);
//				}
//				jobSeekerQualificationServiceI.insertAll(jobSeekerQualificationsToInsert);
//				theReturn+=" and qualifications inserted.";
//			} catch (Exception e) {
//				return theReturn;
//				
//			}
//		
//			return theReturn;
			return "";
	}

		@Override
		public skillsAndQualificationsRequest getJobSeekerSkillsAndQualificaitonsByJobSeekerId(Long jobSeekerId) {
			skillsAndQualificationsRequest request=new skillsAndQualificationsRequest();
			List <jobSeekerSkillDTO> returnedSkillList= jobSeekerSkillServiceI
															.findByJobSeekerId(jobSeekerId)
															.stream()
															.map(jobSeekerSkillMapper::mapJobSeekerSkillToDTO)
															.collect(Collectors.toList());
			List<jobSeekerQualificationDTO> jobSeekerQualificationsList=jobSeekerQualificationServiceI
																			.findByJobSeekerId(jobSeekerId)
																			.stream()
																			.map(jobSeekerQualificationMapper::mapJobSeekerQualificationToDTO)
																			.collect(Collectors.toList());
			request.setJobSeekerId(jobSeekerId);
			request.setJobSeekerQualificationsList(jobSeekerQualificationsList);
			request.setJobSeekerSkillList(returnedSkillList);
			return request;
		}
		 
		
}