package com.koligrum.test;

import com.koligrum.test.api.CreateUserEndpoint;
import com.koligrum.test.api.DeleteUserEndpoint;
import com.koligrum.test.api.UpdateUserEndpoint;
import com.koligrum.test.models.request.create_user.CreateUserRequest;
import com.koligrum.test.models.request.update_user.UpdateserRequest;
import com.koligrum.test.models.response.create_user.CreateUserResponse;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import java.util.Collections;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class UpdateUserTest {

    private UpdateUserEndpoint updateUserEndpoint = new UpdateUserEndpoint();
    private CreateUserEndpoint createUserEndpoint = new CreateUserEndpoint();
    private DeleteUserEndpoint deleteUserEndpoint = new DeleteUserEndpoint();
    CreateUserResponse createUserResponse;

    @BeforeEach
    public void createUsers() {
        CreateUserRequest requestBody2 = new CreateUserRequest();
        requestBody2.setFirstName("Peni");
        requestBody2.setLastName("Kurniawati");
        requestBody2.setAge(23);
        requestBody2.setOccupation("Quality Assurance");
        requestBody2.setNationality("Indonesia");
        requestBody2.setHobbies(Collections.singletonList("Cooking"));
        requestBody2.setGender("FEMALE");
        Response response = createUserEndpoint.createUser(requestBody2);
        createUserResponse = response.as(CreateUserResponse.class);
    }

    @Test
    @Tag("StatusCode200")
    public void UpdateUserSuccess() {
        UpdateserRequest requestBodyUpdate = new UpdateserRequest();
        requestBodyUpdate.setId(createUserResponse.getId());
        requestBodyUpdate.setFirstName("Kurnia");
        requestBodyUpdate.setLastName("Koligrum");
        requestBodyUpdate.setAge(23);
        requestBodyUpdate.setOccupation("QA Engineer");
        requestBodyUpdate.setNationality("Indonesia");
        requestBodyUpdate.setHobbies(Collections.singletonList("Cooking"));
        requestBodyUpdate.setGender("FEMALE");


        Response response = updateUserEndpoint.updateUser(requestBodyUpdate);

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
    @Tag("StatusCode404")
    public void UpdateUserNotFound(){
        UpdateserRequest requestBodyUpdate = new UpdateserRequest();
        requestBodyUpdate.setId("78f4f75b-935e-4b5a-bb1a-e82ba71eb306");
        requestBodyUpdate.setFirstName("Kurnia");
        requestBodyUpdate.setLastName("Koligrum");
        requestBodyUpdate.setAge(23);
        requestBodyUpdate.setOccupation("QA Engineer");
        requestBodyUpdate.setNationality("Indonesia");
        requestBodyUpdate.setHobbies(Collections.singletonList("Cooking"));
        requestBodyUpdate.setGender("FEMALE");

        Response response = updateUserEndpoint.updateUser(requestBodyUpdate);

        int statusCode = response.getStatusCode();
        Assert.assertEquals(404, statusCode);

    }

    @Test
    @Tag("StatusCode400")
    public void UpdateUserFailed(){
        UpdateserRequest requestBodyUpdate = new UpdateserRequest();
        requestBodyUpdate.setId(createUserResponse.getId());
        requestBodyUpdate.setFirstName("Kurnia");
        requestBodyUpdate.setLastName("Koligrum");
        requestBodyUpdate.setAge(23);
        requestBodyUpdate.setOccupation("QA Engineer");
        requestBodyUpdate.setNationality("Indonesia");
        requestBodyUpdate.setHobbies(Collections.singletonList("Cooking"));
        requestBodyUpdate.setGender("-"); //ini yang bikin gagal

        Response response = updateUserEndpoint.updateUser(requestBodyUpdate);

        int statusCode = response.getStatusCode();
        Assert.assertEquals(400, statusCode);

    }

    @AfterEach
    public void deleteUser() {
        deleteUserEndpoint.deleteUser(createUserResponse.getId());

    }
}
