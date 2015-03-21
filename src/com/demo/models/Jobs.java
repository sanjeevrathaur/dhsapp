package com.demo.models;

import java.io.Serializable;

public class Jobs implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String getJobs_title() {
		return jobs_title;
	}
	public void setJobs_title(String jobs_title) {
		this.jobs_title = jobs_title;
	}
	public String getOrganisation_title() {
		return organisation_title;
	}
	public void setOrganisation_title(String organisation_title) {
		this.organisation_title = organisation_title;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String jobs_title;
	public String organisation_title;
	public String experience;
	public String location;
	public String vacancy;
	public String functional_area;
	public String getFunctional_area() {
		return functional_area;
	}
	public void setFunctional_area(String functional_area) {
		this.functional_area = functional_area;
	}
	public String getVacancy() {
		return vacancy;
	}
	public void setVacancy(String vacancy) {
		this.vacancy = vacancy;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getJob_posting_date() {
		return job_posting_date;
	}
	public void setJob_posting_date(String job_posting_date) {
		this.job_posting_date = job_posting_date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getKey_skills() {
		return key_skills;
	}
	public void setKey_skills(String key_skills) {
		this.key_skills = key_skills;
	}
	public String salary;
	public String job_posting_date;
	public String description;
	public String key_skills;
	public String id;
	public String jobs_type;
	public String getJobs_type() {
		return jobs_type;
	}
	public void setJobs_type(String jobs_type) {
		this.jobs_type = jobs_type;
	}
	public String getJobs_link() {
		return jobs_link;
	}
	public void setJobs_link(String jobs_link) {
		this.jobs_link = jobs_link;
	}
	public String getMail_to() {
		return mail_to;
	}
	public void setMail_to(String mail_to) {
		this.mail_to = mail_to;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String jobs_link;
	public String mail_to;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
