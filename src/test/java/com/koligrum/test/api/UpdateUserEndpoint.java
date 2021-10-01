package com.koligrum.test.api;


import com.koligrum.test.models.request.update_user.UpdateserRequest;
import io.restassured.response.Response;

public class UpdateUserEndpoint extends BaseRequest {

  public Response updateUser(UpdateserRequest requestBodyUpdate) {
    Response response = baseRequest()
        .body(requestBodyUpdate)
        .when().put("/users").then().log().all().extract().response();
    return response;
  }
}
