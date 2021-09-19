package net.toost.cloud.backend.domain.user.application;

import net.toost.cloud.backend.domain.user.application.request.AuthLoginRequest;
import net.toost.cloud.backend.domain.user.application.request.AuthLoginTokenRequest;
import net.toost.cloud.backend.domain.user.application.response.AuthLoginResponse;
import net.toost.cloud.backend.domain.user.core.exception.AuthLoginFailedException;
import net.toost.cloud.backend.domain.user.core.model.User;
import net.toost.cloud.backend.domain.user.core.ports.incoming.PasswordEncoder;
import net.toost.cloud.backend.domain.user.core.ports.incoming.TokenGenerator;
import net.toost.cloud.backend.domain.user.core.ports.outgoing.UserRepository;

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


    @PermitAll
    @Path("login/basic")
    @POST
    public Response basic(AuthLoginRequest request) throws AuthLoginFailedException {
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
            return Response.ok(new AuthLoginResponse(user.getToken(), user)).build();
        }
        throw new AuthLoginFailedException("User not found", Response.Status.BAD_REQUEST);
    }

    @PermitAll
    @Path("login/token")
    @POST
    public Response token(AuthLoginTokenRequest request) throws AuthLoginFailedException {
        Optional<User> userOptional = repository.findByToken(request.getToken());
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            return Response.ok(new AuthLoginResponse(user.getToken(), user)).build();
        }
        throw new AuthLoginFailedException("User not found", Response.Status.BAD_REQUEST);
    }


}
