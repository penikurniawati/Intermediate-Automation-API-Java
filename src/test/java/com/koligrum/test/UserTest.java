package com.koligrum.test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

//import com.koligrum.test.models.response.get_user.GetUserResponse;
import com.koligrum.test.models.request.create_user.CreateUserRequest;
import com.koligrum.test.models.request.update_user.UpdateserRequest;
import com.koligrum.test.models.response.create_user.CreateUserResponse;
import com.koligrum.test.models.response.get_user.GetUserResponse;
import com.koligrum.test.models.response.get_user_by_id.GetUserByIdResponse;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class UserTest {
  final static String url = "http://localhost:1234";

  @BeforeEach
  public void createUser(){
    System.out.println("BEFORE EACH");
  }

  @AfterEach
  public void deleteUser(){
    System.out.println("AFTER EACH");
  }

  @Test
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

    Response response = given().baseUri(url)
        .basePath("/v1")
        .header("accept", "application/json")
        .header("content-type", "application/json")
        .body(requestBody2)
        .log().all()
        .when().post("/users").then().log().all().extract().response();

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
  public void getUsers(){
    Response response = given().baseUri(url)
        .basePath("/v1")
        .header("accept", "application/json")
        .header("content-type", "application/json")
        .queryParam("name", "Peni")
        .when().get("/users").then().log().all().extract().response();

    int statusCode = response.getStatusCode();
    Assert.assertEquals(200, statusCode);

//    Validation JSON Schema
    MatcherAssert.assertThat(response.getBody().asString(),
            JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get-user-schema.json"));

//    Validastion response body
    GetUserResponse getUserResponse = response.as(GetUserResponse.class);
    Assert.assertEquals("Peni", getUserResponse.getFirstName());
//    Assert.assertEquals("Quality Assurance", getUserResponse.getOccupation());
//    int age = getUserResponse.getAge();
//    Assert.assertEquals(23, getUserResponse.getAge());
  }

  @Test
  public void updateUsers(){

    //ganti data id dari response post terlebih dahulu
    UpdateserRequest requestBodyUpdate = new UpdateserRequest();
    requestBodyUpdate.setId("78f4f75b-935e-4b5a-bb1a-e82ba71eb306");
    requestBodyUpdate.setFirstName("Kurnia");
    requestBodyUpdate.setLastName("Koligrum");
    requestBodyUpdate.setAge(23);
    requestBodyUpdate.setOccupation("QA Engineer");
    requestBodyUpdate.setNationality("Indonesia");
    requestBodyUpdate.setHobbies(Collections.singletonList("Cooking"));
    requestBodyUpdate.setGender("FEMALE");


    Response response = given().baseUri(url)
        .basePath("/v1")
        .header("accept", "application/json")
        .header("content-type", "application/json")
        .body(requestBodyUpdate)
        .log().all()
        .when().put("/users").then().log().all().extract().response();

    //ini untuk ambil id user
//    String idUser = response.path("id");

    int statusCode = response.getStatusCode();
    Assert.assertEquals(200, statusCode);

    String occupation = response.path("occupation");
    Assert.assertEquals("QA Engineer", occupation);

    //Deserialization
    CreateUserResponse createUserResponse = response.as(CreateUserResponse.class);
    String firstName = createUserResponse.getFirstName();
    Assert.assertEquals("Kurnia", firstName);
    Assert.assertEquals("Koligrum", createUserResponse.getLastName());
    Assert.assertEquals(requestBodyUpdate.getHobbies(), createUserResponse.getHobbies());
    int age = createUserResponse.getAge();
    Assert.assertEquals(23, createUserResponse.getAge());
    Assert.assertEquals(requestBodyUpdate.getGender(), createUserResponse.getGender());
    Assert.assertEquals(requestBodyUpdate.getOccupation(), createUserResponse.getOccupation());
    Assert.assertEquals(requestBodyUpdate.getNationality(), createUserResponse.getNationality());

    //Validation JSON Schema
    MatcherAssert.assertThat(response.getBody().asString(),
            JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/update-user-schema.json"));
  }

  @Test
  public void getUserById(){

    Response response = given().baseUri(url)
        .basePath("/v1")
        .header("accept", "application/json")
        .header("content-type", "application/json")
        .pathParam("id", "78f4f75b-935e-4b5a-bb1a-e82ba71eb306")
        .log().all()
        .when().get("/users/{id}").then().log().all().extract().response();

    int statusCode = response.getStatusCode();
    Assert.assertEquals(200, statusCode);

//    Validation JSON Schema
    MatcherAssert.assertThat(response.getBody().asString(),
            JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get-user-by-id-schema.json"));

    //    Validastion response body
    GetUserByIdResponse getUserByIdResponse = response.as(GetUserByIdResponse.class);
    Assert.assertEquals("Kurnia", getUserByIdResponse.getFirstName());
    Assert.assertEquals("QA Engineer", getUserByIdResponse.getOccupation());
  }

  @Test
  public void deleteUserById(){

    Response response = given().baseUri(url)
        .basePath("/v1")
        .header("accept", "application/json")
        .header("content-type", "application/json")
        .pathParam("id", "96807379-b6d3-4bfb-810c-915a9c4a4eed")
        .log().all()
        .when().delete("/users/{id}").then().log().all().extract().response();

    int statusCode = response.getStatusCode();
    Assert.assertEquals(200, statusCode);

    response.getBody().prettyPrint();
//    Assert.assertEquals("user not found", response.path("message"));

  }



}
