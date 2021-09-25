package com.koligrum.test.models.response.create_user;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateUserResponse{

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
	private String updatedDate;

	@JsonProperty("age")
	private int age;

	public String getFirstName(){
		return firstName;
	}

	public String getLastName(){
		return lastName;
	}

	public String getOccupation(){
		return occupation;
	}

	public String getCreatedDate(){
		return createdDate;
	}

	public String getNationality(){
		return nationality;
	}

	public String getGender(){
		return gender;
	}

	public List<String> getHobbies(){
		return hobbies;
	}

	public String getId(){
		return id;
	}

	public String getUpdatedDate(){
		return updatedDate;
	}

	public int getAge(){
		return age;
	}
}