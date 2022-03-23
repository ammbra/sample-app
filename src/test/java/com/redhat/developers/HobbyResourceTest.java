package com.redhat.developers;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

@QuarkusTest
public class HobbyResourceTest {

    @Test
    void getRandomPricedHobby() {
        given()
                .when().get("/actions")
                .then()
                .statusCode(200)
                .body(notNullValue());
    }

    @Test
    void getHobbyByType() {
        given()
                .when().get("/actions/recreational")
                .then()
                .statusCode(200)
                .body(notNullValue());
    }

    @Test
    void getHobbyUsingTypeDrawing() {
        given()
                .when().get("/actions/drawing")
                .then()
                .statusCode(204)
                .body(notNullValue());
    }

    @Test
    void getHobbyUsingUnsupportedType() {
        given()
                .when().get("/actions/unsupported")
                .then()
                .statusCode(503)
                .body(notNullValue());
    }

    @Test
    void getHobbyUsingSupportedType() {
        given()
                .when().get("/actions/busywork")
                .then()
                .statusCode(501)
                .body(notNullValue());
    }
}