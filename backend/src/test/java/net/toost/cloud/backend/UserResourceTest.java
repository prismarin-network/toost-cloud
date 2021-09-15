package net.toost.cloud.backend;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import net.toost.cloud.backend.domain.user.application.AuthResource;
import net.toost.cloud.backend.domain.user.application.UserResource;
import net.toost.cloud.backend.domain.user.application.request.AuthLoginRequest;
import net.toost.cloud.backend.domain.user.core.model.User;
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

    @ConfigProperty(name = "toost.jwt.duration") long tokenDuration;
    @ConfigProperty(name = "mp.jwt.verify.issuer") String issuer;
    @ConfigProperty(name = "toost.jwt.generation.keys.directory") String keysDirectory;

    String jwtToken;

    @Inject
    UserRepository repository;

    @BeforeEach
    public void setup() {
        User user = repository.findById("admin");
        try {
            jwtToken = TokenUtils.generateToken(user.getId(), user.getGroups(), tokenDuration, issuer, keysDirectory + "privatekey.pem");
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

}
