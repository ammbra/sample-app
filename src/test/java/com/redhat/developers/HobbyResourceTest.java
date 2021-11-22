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
                .when().get("/action")
                .then()
                .statusCode(200)
                .body(notNullValue());
    }

    @Test
    void getHobbyByType() {
        given()
                .when().get("/action/recreational")
                .then()
                .statusCode(200)
                .body(notNullValue());
    }

    @Test
    void getHobbyUsingTypeDrawing() {
        given()
                .when().get("/action/drawing")
                .then()
                .statusCode(204)
                .body(notNullValue());
    }

    @Test
    void getHobbyUsingUnsupportedType() {
        given()
                .when().get("/action/unsupported")
                .then()
                .statusCode(503)
                .body(notNullValue());
    }
}