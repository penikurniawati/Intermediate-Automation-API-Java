package com.koligrum.test.api;

import io.restassured.response.Response;

public class DeleteUserEndpoint extends BaseRequest {

  public Response deleteUser(String id) {
    Response response = baseRequest()
        .pathParam("id", id)
        .when().delete("/users/{id}").then().log().all().extract().response();
    return response;
  }

}
