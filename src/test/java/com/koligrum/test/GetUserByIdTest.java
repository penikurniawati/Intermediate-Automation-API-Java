package com.koligrum.test;

import com.koligrum.test.api.GetUserByIdEndpoint;
import com.koligrum.test.models.response.get_user.GetUserResponse;
import com.koligrum.test.models.response.get_user_by_id.GetUserByIdResponse;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import io.restassured.response.Response;

public class GetUserByIdTest {
    final static String url = "http://localhost:1234";
    private GetUserByIdEndpoint getUserByIdEndpoint = new GetUserByIdEndpoint();

    @Test
    public void getUserById(){
        GetUserByIdResponse reponseBodyById = new GetUserByIdResponse();
        Response response = getUserByIdEndpoint.getUserById(reponseBodyById);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(200, statusCode);

//    Validation JSON Schema
        MatcherAssert.assertThat(response.getBody().asString(),
                JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get-user-by-id-schema.json"));

////    Validastion response body
        GetUserByIdResponse getUserByIdResponse = response.as(GetUserByIdResponse.class);
        Assert.assertEquals("Kurnia", getUserByIdResponse.getFirstName());
        Assert.assertEquals("QA Engineer", getUserByIdResponse.getOccupation());
    }
}
