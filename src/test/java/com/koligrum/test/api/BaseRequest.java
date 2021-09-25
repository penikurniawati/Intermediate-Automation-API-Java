package com.koligrum.test.api;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseRequest {

    public RequestSpecification baseRequest(){
        RequestSpecification request = given().baseUri("http://localhost:1234")
                .basePath("/v1")
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .log().all();
        return request;
    }
}
