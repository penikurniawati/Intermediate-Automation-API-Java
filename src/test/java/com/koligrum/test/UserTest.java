package com.koligrum.test;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;

public class UserTest {
  final static String url = "http://localhost:8086";

  @Test
  public void test(){
    Response response = given().baseUri(url)
        .basePath("v1")
        .queryParam("name", "")
        .header("accept", "application/json")
        .contentType(ContentType.JSON)
        .log().all()
        .when().get("users");

    //assert status code with jUnit Assert
    int statusCode = response.getStatusCode();
    Assert.assertEquals(200, statusCode);

    //assert data using json path
    String name = response.path("data[0].firstName");
    Assert.assertEquals("Koligrum", name);

//    Response response = given().baseUri(url)
//        .basePath("v1")
//        .pathParam("id", "4f18da29-3feb-475b-b1fa-2780e8675b47")
//        .contentType(ContentType.JSON)
//        .log().all()
//        .when().get("users/{id}");
//    response.getBody().prettyPrint();
//
//    int statusCode = response.statusCode();
//    Assert.assertEquals(200, statusCode);
//    System.out.println(response.jsonPath().getString("id"));

  }

}
