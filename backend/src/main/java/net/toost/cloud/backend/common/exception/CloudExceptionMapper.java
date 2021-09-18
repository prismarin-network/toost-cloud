package net.toost.cloud.backend.common.exception;

import net.toost.cloud.backend.common.exception.model.ExceptionResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Copyright (c) Maga, All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Maga
 **/
@Provider
public class CloudExceptionMapper implements ExceptionMapper<CloudException> {

    @Override
    public Response toResponse(CloudException exception) {
        return Response.status(exception.getStatus()).entity(new ExceptionResponse(exception.getMessage())).build();
    }
}
