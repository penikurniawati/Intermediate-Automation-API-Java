package com.koligrum.test.models.response.get_user;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GetUserResponse{

	@JsonProperty("firstName")
	private String firstName;

	@JsonProperty("lastName")
	private String lastName;

	@JsonProperty("occupation")
	private String occupation;

	@JsonProperty("createdDate")
	private String createdDate;

	@JsonProperty("nationality")
	private String nationality;

	@JsonProperty("gender")
	private String gender;

	@JsonProperty("hobbies")
	private List<String> hobbies;

	@JsonProperty("id")
	private String id;

	@JsonProperty("updatedDate")
	private Object updatedDate;

	@JsonProperty("age")
	private Integer age;

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	public String getFirstName(){
		return firstName;
	}

	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	public String getLastName(){
		return lastName;
	}

	public void setOccupation(String occupation){
		this.occupation = occupation;
	}

	public String getOccupation(){
		return occupation;
	}

	public void setCreatedDate(String createdDate){
		this.createdDate = createdDate;
	}

	public String getCreatedDate(){
		return createdDate;
	}

	public void setNationality(String nationality){
		this.nationality = nationality;
	}

	public String getNationality(){
		return nationality;
	}

	public void setGender(String gender){
		this.gender = gender;
	}

	public String getGender(){
		return gender;
	}

	public void setHobbies(List<String> hobbies){
		this.hobbies = hobbies;
	}

	public List<String> getHobbies(){
		return hobbies;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setUpdatedDate(Object updatedDate){
		this.updatedDate = updatedDate;
	}

	public Object getUpdatedDate(){
		return updatedDate;
	}

	public void setAge(Integer age){
		this.age = age;
	}

	public Integer getAge(){
		return age;
	}
}