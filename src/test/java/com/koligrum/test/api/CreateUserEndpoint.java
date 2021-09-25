package com.koligrum.test.api;

import com.koligrum.test.models.request.create_user.CreateUserRequest;
import io.restassured.response.Response;

public class CreateUserEndpoint extends BaseRequest {

    public io.restassured.response.Response createUser(CreateUserRequest requestBody){
        Response response = baseRequest()
                .body(requestBody)
                .log().all()
                .when().post("/users").then().log().all().extract().response();
        return response;
    }
}
