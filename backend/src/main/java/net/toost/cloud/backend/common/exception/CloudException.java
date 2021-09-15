package net.toost.cloud.backend.common.exception;

import lombok.Getter;

import javax.ws.rs.core.Response;

/**
 * Copyright (c) Maga, All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Maga
 **/
@Getter
public class CloudException extends Exception {

    private Response.Status status;

    /**
     * Base exception for future mapping
     *
     * @param message
     * @param status
     */
    public CloudException(String message, Response.Status status) {
        super(message);
        this.status = status;
    }
}
