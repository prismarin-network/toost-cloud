package net.toost.cloud.backend.domain.user.application;

import net.toost.cloud.backend.domain.user.application.request.AuthLoginRequest;
import net.toost.cloud.backend.domain.user.application.response.AuthLoginResponse;
import net.toost.cloud.backend.domain.user.core.exception.AuthLoginFailedException;
import net.toost.cloud.backend.domain.user.core.model.User;
import net.toost.cloud.backend.domain.user.core.ports.incoming.PasswordEncoder;
import net.toost.cloud.backend.domain.user.core.ports.incoming.TokenGenerator;
import net.toost.cloud.backend.domain.user.core.ports.outgoing.UserRepository;
import net.toost.cloud.backend.util.TokenUtils;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.Optional;

/**
 * Copyright (c) Maga, All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Maga
 **/

@Path("/auth/")
public class AuthResource {

    @Inject
    UserRepository repository;

    @Inject
    PasswordEncoder encoder;

    @Inject
    TokenGenerator generator;


    @PermitAll
    @Path("login")
    @POST
    public Response login(AuthLoginRequest request) throws AuthLoginFailedException {
        Optional<User> userOptional = repository.findByIdOptional(request.getUserName().toLowerCase());
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            String encoded = "";
            try {
                encoded = encoder.encodePassword(request.getPassword());
            } catch (Exception exception) {
                throw new AuthLoginFailedException("There was a problem with encoding the password", Response.Status.CONFLICT);
            }
            if(!encoded.equals(user.getPassword())) {
                throw new AuthLoginFailedException("Password wrong", Response.Status.UNAUTHORIZED);
            }
            String token = "";
            try {
                token = generator.generateToken(user, request.getTokenDuration());
            } catch (Exception e) {
                e.printStackTrace();
                throw new AuthLoginFailedException("There was a problem with creating a jwt token", Response.Status.CONFLICT);
            }
            return Response.ok(new AuthLoginResponse(token)).build();
        }
        throw new AuthLoginFailedException("User not found", Response.Status.BAD_REQUEST);
    }

}
