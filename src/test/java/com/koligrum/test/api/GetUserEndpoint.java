package com.koligrum.test.api;

import com.koligrum.test.models.response.get_user.GetUserResponse;
import io.restassured.response.Response;

public class GetUserEndpoint extends BaseRequest{

    public Response getUser(GetUserResponse responseBody){
        Response response = baseRequest()
                .queryParam("name", "Peni")
                .when().get("/users").then().log().all().extract().response();
        return response;
    }
}
