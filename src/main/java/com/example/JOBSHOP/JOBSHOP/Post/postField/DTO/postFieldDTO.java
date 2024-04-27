package com.example.JOBSHOP.JOBSHOP.Post.postField.DTO;
import java.util.List;
import java.util.Map;

import com.example.JOBSHOP.JOBSHOP.Base.baseEntityDTO;
import com.example.JOBSHOP.JOBSHOP.Employer.employerField.employerField;
import com.example.JOBSHOP.JOBSHOP.Post.Post;
public class postFieldDTO extends baseEntityDTO<Long>{

	private  employerField employerField;
	private Post post;
	private List<String> skills;
	private List<String> qualifications;
	private String fieldName;
	
	private Map<Long,String> skillsMap;
	private Map<Long,String> qualificationMap;
	
	public Map<Long, String> getSkillsMap() {
		return skillsMap;
	}
	public void setSkillsMap(Map<Long, String> skillsMap) {
		this.skillsMap = skillsMap;
	}
	public Map<Long, String> getQualificationMap() {
		return qualificationMap;
	}
	public void setQualificationMap(Map<Long, String> qualificationMap) {
		this.qualificationMap = qualificationMap;
	}
	public employerField getEmployerField() {
		return employerField;
	}
	public void setEmployerField(employerField employerField) {
		this.employerField = employerField;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public List<String> getSkills() {
		return skills;
	}
	public void setSkills(List<String> skills) {
		this.skills = skills;
	}
	public List<String> getQualifications() {
		return qualifications;
	}
	public void setQualifications(List<String> qualifications) {
		this.qualifications = qualifications;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
}
