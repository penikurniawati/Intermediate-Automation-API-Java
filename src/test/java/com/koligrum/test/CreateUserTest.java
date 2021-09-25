package com.koligrum.test;

import com.koligrum.test.api.CreateUserEndpoint;
import com.koligrum.test.models.request.create_user.CreateUserRequest;
import com.koligrum.test.models.response.create_user.CreateUserResponse;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static io.restassured.RestAssured.given;

public class CreateUserTest {

    final static String url = "http://localhost:1234";

    private CreateUserEndpoint createUserEndpoint = new CreateUserEndpoint();

    @Test
    @Tag("StatusCode200")
    public void createUsers(){

        //Serialization for POST
        CreateUserRequest requestBody2 = new CreateUserRequest();
        requestBody2.setFirstName("Peni");
        requestBody2.setLastName("Kurniawati");
        requestBody2.setAge(23);
        requestBody2.setOccupation("Quality Assurance");
        requestBody2.setNationality("Indonesia");
        requestBody2.setHobbies(Collections.singletonList("Cooking"));
        requestBody2.setGender("FEMALE");

        Response response = createUserEndpoint.createUser(requestBody2);

        //assert status code
        int statusCode = response.getStatusCode();
        Assert.assertEquals(200, statusCode);

        //Deserialization
        CreateUserResponse createUserResponse = response.as(CreateUserResponse.class);
        String firstName = createUserResponse.getFirstName();
        Assert.assertEquals("Peni", firstName);
        Assert.assertEquals("Kurniawati", createUserResponse.getLastName());
        Assert.assertEquals(requestBody2.getHobbies(), createUserResponse.getHobbies());
        int age = createUserResponse.getAge();
        Assert.assertEquals(23, createUserResponse.getAge());
        Assert.assertEquals(requestBody2.getGender(), createUserResponse.getGender());
        Assert.assertEquals(requestBody2.getOccupation(), createUserResponse.getOccupation());
        Assert.assertEquals(requestBody2.getNationality(), createUserResponse.getNationality());

        //Validation JSON Schema
        MatcherAssert.assertThat(response.getBody().asString(),
                JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/create-user-schema.json"));

    }

    @Test
    @Tag("StatusCode400")
    public void createUserInvalid(){

        //Serialization for POST
        CreateUserRequest requestBody2 = new CreateUserRequest();
        requestBody2.setFirstName("Peni");
        requestBody2.setLastName("Kurniawati");
        requestBody2.setAge(23);
        requestBody2.setOccupation("Quality Assurance");
        requestBody2.setNationality("Indonesia");
        requestBody2.setHobbies(Collections.singletonList("Cooking"));
        requestBody2.setGender("female"); // ini harusnya FEMALE

        Response response = createUserEndpoint.createUser(requestBody2);

        //assert status code
        int statusCode = response.getStatusCode();
        Assert.assertEquals(400, statusCode);
    }
}
