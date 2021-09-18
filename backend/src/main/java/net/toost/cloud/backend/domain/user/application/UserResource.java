package net.toost.cloud.backend.domain.user.application;

import net.toost.cloud.backend.domain.user.core.exception.UserNotFoundException;
import net.toost.cloud.backend.domain.user.core.model.User;
import net.toost.cloud.backend.domain.user.core.ports.outgoing.UserRepository;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
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
    public User get(@PathParam("id") String id) throws UserNotFoundException {
        Optional<User> userOptional = repository.findByIdOptional(id.toLowerCase());
        if(!userOptional.isPresent()) {
            throw new UserNotFoundException();
        }
        return userOptional.get();
    }

    @PUT
    @RolesAllowed("Admin")
    public User put(User user) {
        repository.persistOrUpdate(user);
        return user;
    }

    @Path("{id}")
    @DELETE
    @RolesAllowed("User")
    public Response delete(@PathParam("id") String id) throws UserNotFoundException {
        if (!repository.deleteById(id)) {
            throw new UserNotFoundException();
        }
        return Response.ok().build();
    }

    @Path("{id}")
    @PATCH
    @RolesAllowed("User")
    public User patch(@PathParam("id") String id, User patchedUser) throws UserNotFoundException {
        Optional<User> userOptional = repository.findByIdOptional(id.toLowerCase());
        if(!userOptional.isPresent()) {
            throw new UserNotFoundException();
        }
        User user = userOptional.get();
        if(patchedUser.getDisplayName() != null) {
            user.setDisplayName(patchedUser.getDisplayName());
        }
        if(patchedUser.getGroups() != null) {
            for(String group : patchedUser.getGroups()) {
                user.getGroups().add(group);
            }
        }
        if(patchedUser.getMail() != null) {
            user.setMail(patchedUser.getMail());
        }
        repository.update(user);
        return user;
    }

}
