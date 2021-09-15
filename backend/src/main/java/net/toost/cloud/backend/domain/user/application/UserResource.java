package net.toost.cloud.backend.domain.user.application;

import net.toost.cloud.backend.domain.user.core.exception.UserNotFoundException;
import net.toost.cloud.backend.domain.user.core.model.User;
import net.toost.cloud.backend.domain.user.core.ports.outgoing.UserRepository;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.Optional;

/**
 * Copyright (c) Maga, All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Maga
 **/

@Path("/users")
public class UserResource {

    @Inject
    UserRepository repository;

    @Path("{id}")
    @GET
    @RolesAllowed("User")
    public Response get(@PathParam("id") String id) throws UserNotFoundException {
        Optional<User> userOptional = repository.findByIdOptional(id.toLowerCase());
        if(!userOptional.isPresent()) {
            throw new UserNotFoundException();
        }
        return Response.ok(userOptional.get()).build();
    }

}
