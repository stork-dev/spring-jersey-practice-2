package com.dev.spring.exceptionmapper;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.time.LocalDateTime;
import java.util.List;

public abstract class AbstractExceptionMapper<E extends Throwable> implements ExceptionMapper<E> {

    @Context
    protected HttpServletRequest request;

    public Response toResponse(E e, int httpStatusCode) {
        return Response.status(httpStatusCode).
                entity(ErrorResponse.builder().error(e.getMessage()).path(request.getRequestURI())
                        .timestamp(LocalDateTime.now().toString())
                        .status(httpStatusCode).build())
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }

    public Response toResponse(List<String> errors, int httpStatusCode) {
        return Response.status(httpStatusCode).
                entity(ErrorResponse.builder().error(errors.toString()).path(request.getRequestURI())
                        .timestamp(LocalDateTime.now().toString())
                        .status(httpStatusCode).build())
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }
}
