package net.toost.cloud.backend;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import net.toost.cloud.backend.domain.user.application.AuthResource;
import net.toost.cloud.backend.domain.user.application.request.AuthLoginRequest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

/**
 * Copyright (c) Maga, All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Maga
 **/
@QuarkusTest
@TestHTTPEndpoint(AuthResource.class)
public class AuthResourceTest {

    @Test
    public void testLoginEndpoint() {
        given().contentType(ContentType.JSON)
                .body(new AuthLoginRequest("admin", "admin123"))
                .when().post("login")
                .then()
                .statusCode(200);
    }

}
