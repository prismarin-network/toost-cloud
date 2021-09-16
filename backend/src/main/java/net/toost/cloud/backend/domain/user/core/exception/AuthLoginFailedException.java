package net.toost.cloud.backend.domain.user.core.exception;

import net.toost.cloud.backend.common.exception.CloudException;

import javax.ws.rs.core.Response;

/**
 * Copyright (c) Maga, All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Maga
 **/
public class AuthLoginFailedException extends CloudException  {

    /**
     * Throws if a user/password auth failed
     *
     * @param message
     * @param status
     */
    public AuthLoginFailedException(String message, Response.Status status) {
        super(message, status);
    }
}
