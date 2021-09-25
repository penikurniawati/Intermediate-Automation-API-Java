package com.koligrum.test;

import com.koligrum.test.api.GetUserEndpoint;
import com.koligrum.test.models.request.create_user.CreateUserRequest;
import com.koligrum.test.models.response.get_user.GetUserResponse;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class GetUserTest {

    final static String url = "https://localhost:1234";

    private GetUserEndpoint getUserEndpoint = new GetUserEndpoint();

    @Test
    public void getUser(){
        GetUserResponse responseBodyGet = new GetUserResponse();
        Response response = getUserEndpoint.getUser(responseBodyGet);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(200, statusCode);

//    Validation JSON Schema
        MatcherAssert.assertThat(response.getBody().asString(),
                JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get-user-schema.json"));

//    Validastion response body
        GetUserResponse getUserResponse = response.as(GetUserResponse.class);
        Assert.assertEquals("Peni", getUserResponse.getFirstName());
        Assert.assertEquals("Quality Assurance", getUserResponse.getOccupation());
    }

}
