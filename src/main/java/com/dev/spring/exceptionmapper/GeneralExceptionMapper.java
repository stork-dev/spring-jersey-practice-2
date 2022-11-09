package com.dev.spring.exceptionmapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
public class GeneralExceptionMapper extends AbstractExceptionMapper<Throwable> {

    @Context
    protected HttpServletRequest request;

    @Context
    protected HttpServletResponse response;

    @Override
    public Response toResponse(Throwable e) {
        return toResponse(e, 500);
    }
}