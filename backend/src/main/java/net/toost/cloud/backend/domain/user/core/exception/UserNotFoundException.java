package net.toost.cloud.backend.domain.user.core.exception;

import net.toost.cloud.backend.common.exception.CloudException;

import javax.ws.rs.core.Response;

/**
 * Copyright (c) Maga, All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Maga
 **/
public class UserNotFoundException extends CloudException  {

    /**
     * Throws if the user in the context does not exists
     */
    public UserNotFoundException() {
        super("User not found", Response.Status.BAD_REQUEST);
    }
}
