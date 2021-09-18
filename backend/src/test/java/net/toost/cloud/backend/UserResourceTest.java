package net.toost.cloud.backend;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import net.toost.cloud.backend.domain.user.application.UserResource;
import net.toost.cloud.backend.domain.user.core.model.User;
import net.toost.cloud.backend.domain.user.core.ports.incoming.TokenGenerator;
import net.toost.cloud.backend.domain.user.core.ports.outgoing.UserRepository;
import net.toost.cloud.backend.util.TokenUtils;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

/**
 * Copyright (c) Maga, All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Maga
 **/
@QuarkusTest
@TestHTTPEndpoint(UserResource.class)
public class UserResourceTest {

    @Inject
    TokenGenerator generator;

    String jwtToken;

    @Inject
    UserRepository repository;


    @BeforeEach
    public void setup() {
        User user = repository.findById("admin");
        try {
            jwtToken = generator.generateToken(user, 1000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void testGetEndpoint() {
        given().header("Authorization", "Bearer " + jwtToken)
                .when().get("admin")
                .then()
                .statusCode(200)
                .body("displayName", is("Admin"));
    }

    @Test
    public void testPutEndpoint() throws Exception {
        User user = repository.create("Test", "Test123", "User");
        given().header("Authorization", "Bearer " + jwtToken)
                .contentType(ContentType.JSON)
                .body(user)
                .when().put()
                .then()
                .statusCode(200)
                .body("displayName", is("Test"));
    }

    @Test
    public void testDeleteEndpoint() throws Exception {
        User user = repository.create("Test", "Test123", "User");
        repository.persist(user);

        given().header("Authorization", "Bearer " + jwtToken)
                .when().delete("test")
                .then()
                .statusCode(200);
    }

    @Test
    public void testPatchEndpoint() throws Exception {
        User user = repository.create("PatchUser", "Test123", "User");
        repository.persist(user);

        User patchUser = new User();
        patchUser.setDisplayName("Markus");

        given().header("Authorization", "Bearer " + jwtToken)
                .contentType(ContentType.JSON)
                .body(patchUser)
                .when().patch("PatchUser")
                .then()
                .statusCode(200)
                .body("displayName", is("Markus"));
    }

}
