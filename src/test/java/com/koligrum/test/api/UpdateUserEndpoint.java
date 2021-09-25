package com.koligrum.test.api;


import com.koligrum.test.models.request.create_user.CreateUserRequest;
import com.koligrum.test.models.request.update_user.UpdateserRequest;

import javax.xml.ws.Response;

public class UpdateUserEndpoint extends BaseRequest{

    public io.restassured.response.Response updateUser(UpdateserRequest requestBodyUpdate){
        io.restassured.response.Response response = baseRequest()
                .pathParam("id", "201e96ba-d197-4d33-b74b-9f545f1e4cfc")
                .log().all()
                .when().get("/users/{id}").then().log().all().extract().response();
        return response;
    }
}
