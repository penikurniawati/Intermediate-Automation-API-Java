package com.koligrum.test.api;

import com.koligrum.test.models.response.get_user_by_id.GetUserByIdResponse;
import io.restassured.response.Response;

public class GetUserByIdEndpoint extends BaseRequest {

    public Response getUserById(GetUserByIdResponse responseBody){
        Response response = baseRequest()
                .pathParam("id", "73f075af-59a9-4417-800e-4219fdd5d6f7")
                .when().get("/users/{id}").then().log().all().extract().response();
        return response;
    }
}